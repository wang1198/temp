package com.ctsi.service.impl;

import com.ctsi.service.CscpDicFileService;
import com.ctsi.ssdc.admin.domain.CscpDic;
import com.ctsi.ssdc.admin.repository.CscpDicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName CscpDicFileServiceImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/8 13:51
 * Version v1.0
 **/
@Service
public class CscpDicFileServiceImpl implements CscpDicFileService {
    @Autowired
    private CscpDicDao cscpDicDao;
    @Override
    public void importDic(List<CscpDic> dics) {
        for (CscpDic dic:dics) {
            if(dic.getDicTypeInfo()!=null)
                dic.setDicType(Integer.valueOf(dic.getDicTypeInfo().split("|")[0]));
            cscpDicDao.insert(dic);
        }
    }

    @Override
    public List<CscpDic> exportDic(CscpDic dic) {
        return cscpDicDao.getDicList(dic);
    }
}
