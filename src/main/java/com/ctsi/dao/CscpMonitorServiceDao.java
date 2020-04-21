package com.ctsi.dao;

import com.ctsi.domain.CscpServiceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName CscpMonitorServiceDao
 * Description TODO
 * Author tongliwei
 * Date 2020/3/20 15:50
 * Version v1.0
 **/
@Mapper
public interface CscpMonitorServiceDao {
    void insert(CscpServiceInfo cscpServiceInfo);
    void update(CscpServiceInfo cscpServiceInfo);
    void delete(String id);
    List<CscpServiceInfo> getServiceList();
    List<CscpServiceInfo> getServiceListByCond(CscpServiceInfo info);
}
