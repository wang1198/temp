package com.ctsi.common;

import com.ctsi.dao.CscpConfDao;
import com.ctsi.domain.CscpLicense;
import com.ctsi.ssdc.util.RedisUtil;
import com.ctsi.utils.Base64Util;
import com.ctsi.utils.RSAUtil;
import com.ctsi.utils.SerialUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
/**
 * ClassName LoginResponseBodyAdvice
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/21 10:41
 * Version v1.0
 **/
@ControllerAdvice
public class LoginResponseBodyAdvice implements ResponseBodyAdvice{
    @Autowired
    private CscpConfDao confDao;
    @Autowired
    private RedisUtil redisDao;

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType mediaType, Class clz, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        byte[] info = null;
        String publicKey = redisDao.get("publicKey")==null?"":redisDao.get("publicKey").toString();
        if (StringUtils.isEmpty(publicKey)) {
            publicKey = confDao.getConfig("publicKey");
            redisDao.set("publicKey", publicKey);
        }
        String license = redisDao.get("license")==null?"":redisDao.get("license").toString();
        if (StringUtils.isEmpty(license)) {
            license = confDao.getConfig("license");
            redisDao.set("license", license);
        }
        try {
            byte[] bytes = Base64Util.decode(license);
            info = RSAUtil.decryptByPublicKey(bytes, publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        CscpLicense cscpLicense = (CscpLicense) SerialUtil.Bytes2Object(info);
        if (cscpLicense != null) {
            if(cscpLicense.isExpired())
                return ResultBack.build(-1,"license已过期");
            if(!cscpLicense.isValid())
                return ResultBack.build(-1,"license无效");
        }
        return body;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class clz) {
        String methodName=methodParameter.getMethod().getName();
        String method= "authorize";
        return method.equals(methodName);
    }
}
