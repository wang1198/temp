package com.ctsi.service.impl;

import com.ctsi.dao.CscpConfDao;
import com.ctsi.service.CscpConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName CscpConfServiceImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/6 11:22
 * Version v1.0
 **/
@Service
public class CscpConfServiceImpl implements CscpConfService {
    @Autowired
    private CscpConfDao confDao;
    @Override
    public String getConfig(String key) {
        String value=confDao.getConfig(key);
        return value;
    }

    @Override
    public void insert(String key,String value) {
        confDao.insert(key,value);
    }
}
