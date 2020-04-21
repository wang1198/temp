package com.ctsi.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * ClassName SerialUtil
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/6 11:45
 * Version v1.0
 **/
public class SerialUtil {
    public static byte[] Object2Bytes(Object obj) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException ex) {
            return null;
        }
    }

    // 将字节数组转换成为对象
    public static Object Bytes2Object(byte[] b) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();
            return obj;
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }
    /**
     * convert byte[] to HexString
     *
     * @param byteArray
     * @return
     */
    public static String bytesToHexString(byte[] byteArray)
    {
        StringBuilder hexString = new StringBuilder();
        if (byteArray == null || byteArray.length <= 0)
            return null;
        for (int i = 0; i < byteArray.length; i++) {
            int v = byteArray[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                hexString.append(0);
            }
            hexString.append(hv);
        }
        return hexString.toString().toLowerCase();
    }

    /**
     * convert HexString to byte[]
     *
     * @param hexString
     * @return
     */
    public static byte[] HexStringTobytes(String hexString) {
        if (StringUtils.isEmpty(hexString))
            return null;
        hexString = hexString.toLowerCase();
        byte[] byteArray = new byte[hexString.length() >> 1];
        int index = 0;
        for (int i = 0; i < hexString.length(); i++) {
            if (index  >= hexString.length() - 1)
                return byteArray;
            byte highDit = (byte) (Character.digit(hexString.charAt(index), 16) & 0xFF);
            byte lowDit = (byte) (Character.digit(hexString.charAt(index + 1), 16) & 0xFF);
            byteArray[i] = (byte) (highDit << 4 | lowDit);
            index += 2;
        }
        return byteArray;
    }
}
