package com.ctsi.service.impl;

import com.ctsi.dao.CscpBusinessDao;
import com.ctsi.dao.CscpMonitorServiceDao;
import com.ctsi.domain.CscpServiceInfo;
import com.ctsi.service.CscpMonitorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName CscpMonitorServiceImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/3/20 15:47
 * Version v1.0
 **/
@Service
public class CscpMonitorServiceImpl implements CscpMonitorService {
    @Autowired
    private CscpMonitorServiceDao cscpMonitorServiceDao;
    @Override
    public void insert(CscpServiceInfo cscpServiceInfo) {
       cscpMonitorServiceDao.insert(cscpServiceInfo);
    }

    @Override
    public void update(CscpServiceInfo cscpServiceInfo) {
       cscpMonitorServiceDao.update(cscpServiceInfo);
    }

    @Override
    public void delete(String id) {
      cscpMonitorServiceDao.delete(id);
    }

    @Override
    public PageInfo<CscpServiceInfo> getServiceListByCond(CscpServiceInfo dto, int page, int limit) {
        PageHelper.startPage(page, limit, true);
        List<CscpServiceInfo> serviceList=cscpMonitorServiceDao.getServiceListByCond(dto);
        PageInfo<CscpServiceInfo> pageInfo = new PageInfo<>(serviceList);
        return pageInfo;
    }
}
