package com.ctsi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ctsi.common.MyException;
import com.ctsi.dao.CscpConfDao;
import com.ctsi.dao.CscpFuncDao;
import com.ctsi.domain.CscpLicense;
import com.ctsi.service.CscpLicenseService;
import com.ctsi.ssdc.admin.service.CscpUserDetailService;
import com.ctsi.ssdc.security.SecurityUtils;
import com.ctsi.utils.Base64Util;
import com.ctsi.utils.RSAUtil;
import com.ctsi.utils.SerialUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * ClassName CscpLicenseServiceImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/6 11:28
 * Version v1.0
 **/
@Service
public class CscpLicenseServiceImpl implements CscpLicenseService {
    @Autowired
    private CscpConfDao confDao;
    @Autowired
    private CscpFuncDao funcDao;
    @Autowired
    private CscpUserDetailService userDetailService;
    @Override
    public CscpLicense license(String license) throws MyException {
        byte[] info = null;
        String publicKey = confDao.getConfig("publicKey");
        try {
            byte[] bytes = Base64Util.decode(license);
            info = RSAUtil.decryptByPublicKey(bytes, publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String decryptValue = null;
        decryptValue = new String(info, StandardCharsets.UTF_8);
        JSONObject object=JSONObject.parseObject(decryptValue);
        CscpLicense cscpLicense = JSONObject.toJavaObject(object,CscpLicense.class);
        if (cscpLicense != null) {
            if(cscpLicense.isExpired())
                throw new MyException("license已过期");
            if(!cscpLicense.isValid())
                throw new MyException("license无效");
            String uid = SecurityUtils.getCurrentUserId();
            String orgID=userDetailService.findOne(uid).getOrgId();
            if(CollectionUtils.isNotEmpty(cscpLicense.getFuncs())){
                for (String funcID:cscpLicense.getFuncs()) {
                    funcDao.saveOrgFunc(orgID,funcID);
                }
            }
            confDao.insert(orgID, license);
        }
        return cscpLicense;
    }

    @Override
    public String register(CscpLicense license, String pri) {
        try {
            byte[] lic = RSAUtil.encryptByPrivateKey(SerialUtil.Object2Bytes(license), pri);
            String result = Base64Util.encode(lic);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CscpLicense getLicense() {
        byte[] info = null;
        String publicKey = confDao.getConfig("publicKey");
        String uid = SecurityUtils.getCurrentUserId();
        String orgID=userDetailService.findOne(uid).getOrgId();
        String license = confDao.getConfig(orgID);
        try {
            byte[] bytes = Base64Util.decode(license);
            info = RSAUtil.decryptByPublicKey(bytes, publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String decryptValue = null;
        decryptValue = new String(info, StandardCharsets.UTF_8);
        JSONObject object=JSONObject.parseObject(decryptValue);
        CscpLicense cscpLicense = JSONObject.toJavaObject(object,CscpLicense.class);
        return cscpLicense;
    }
}
