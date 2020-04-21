package com.ctsi.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

/**
 * ClassName TokenUtil
 * Description //TODO
 * Author tongliwei
 * Date 2020/3/31 18:09
 * Version v1.0
 **/
public class TokenUtil {

    private TokenUtil(){
    }

    private static final TokenUtil instance = new TokenUtil();

    /**
     * 返回类的对象
     * @return
     */
    public static TokenUtil getInstance(){
        return instance;
    }

    /**
     * 生成Token
     * Token：Nv6RRuGEVvmGjB+jimI/gw==
     * @return
     */
    public String makeToken(){
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        //数据指纹   128位长   16个字节  md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            String result=Base64.getUrlEncoder().encodeToString(md5);
            return result;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
