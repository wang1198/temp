package com.ctsi.service.impl;

import com.ctsi.dao.CscpBusinessDao;
import com.ctsi.domain.CscpBusiness;
import com.ctsi.model.CscpBusinessDTO;
import com.ctsi.model.CscpBusinessMapper;
import com.ctsi.service.CscpBusinessService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * ClassName CscpBusinessServiceImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/2 21:25
 * Version v1.0
 **/
@Service
public class CscpBusinessServiceImpl implements CscpBusinessService {
    @Autowired
    private CscpBusinessDao businessDao;
    @Autowired
    private CscpBusinessMapper businessMapper;

    @Override
    public PageInfo getBusinessList(CscpBusinessDTO dto, int page, int limit) {
        PageHelper.startPage(page, limit,true);
        List<CscpBusiness> businessList=businessDao.getBusinessList(dto);
        PageInfo pageInfo = new PageInfo<>(businessList);
        pageInfo.setList(businessMapper.toDto(pageInfo.getList()));
        return pageInfo;
    }

    @Override
    public void insert(CscpBusiness business) {
        businessDao.insert(business);
    }

    @Override
    public void update(CscpBusiness business) {
        businessDao.update(business);
    }

    @Override
    public void delete(List<String> id) {
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("id",id );
        businessDao.delete(map);
    }

    @Override
    public void importBusiness(List<CscpBusiness> list,String orgId,String orgName) {
        for (CscpBusiness info:list) {
            if(info.getTypeDesc()!=null)
                info.setBusinessType(Integer.valueOf(info.getTypeDesc().split("-")[0]));
            info.setOrgId(orgId);
            info.setOrgName(orgName);
            businessDao.insert(info);
        }
    }
}
