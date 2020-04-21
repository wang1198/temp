package com.ctsi.dao;

import com.ctsi.domain.CscpBusiness;
import com.ctsi.model.CscpBusinessDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ClassName CscpTestDao
 * Description TODO
 * Author tongliwei
 * Date 2020/2/2 21:26
 * Version v1.0
 **/
@Mapper
public interface CscpBusinessDao {
    List<CscpBusiness> getBusinessList(CscpBusinessDTO business);
    void insert(CscpBusiness business);
    void update(CscpBusiness business);
    int delete(Map<String,List<String>> maps);
    void saveUrl(@Param("businessId") String businessId, @Param("urls")  List<String> urls);
    void delete_url(String businessId);
    void delete_urls(Map<String,List<String>> maps);
}
