package com.ctsi.dao;

import com.ctsi.domain.Model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DicMapper {
    List<Map<String, String>> getDicByType(@Param("paraType") String paraType);
    String getDicNameByType(@Param("paraType") String paraType, @Param("paraCode") String paraCode);

    List<Model> queryLocation(@Param("pid") String pid);
    List<Model> queryCompany();

    String queryCompanyById(@Param("id") String id);
    String queryLocationById(@Param("id") String id);
    String querySystemIdByProjectId(@Param("projectId") String projectId);
}
