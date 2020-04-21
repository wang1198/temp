package com.ctsi.service.impl;

import com.ctsi.dao.CscpFuncDao;
import com.ctsi.domain.CscpFunc;
import com.ctsi.service.CscpFuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName CscpFuncServiceImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/12 14:13
 * Version v1.0
 **/
@Service
public class CscpFuncServiceImpl implements CscpFuncService {
    @Autowired
    private CscpFuncDao funcDao;
    @Override
    public List<String> getFuncMenus(String orgID) {
        List<String> list=funcDao.getFuncMenus(orgID);
        return list;
    }

    @Override
    public List<CscpFunc> getFuncs(String orgID) {
        List<CscpFunc> funcs=funcDao.getFuncs(orgID);
        return funcs;
    }
}
