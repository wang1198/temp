package com.ctsi.service;

import com.ctsi.domain.CscpServiceInfo;
import com.github.pagehelper.PageInfo;

/**
 * ClassName CscpMonitorService
 * Description TODO
 * Author tongliwei
 * Date 2020/3/20 15:42
 * Version v1.0
 **/
public interface CscpMonitorService {
    void insert(CscpServiceInfo cscpServiceInfo);
    void update(CscpServiceInfo cscpServiceInfo);
    void delete(String id);
    PageInfo<CscpServiceInfo> getServiceListByCond(CscpServiceInfo dto, int page, int limit);
}
