package com.ctsi.dao;

import com.ctsi.domain.CscpPackage;
import com.ctsi.model.CscpPackDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName CscpPackDao
 * Description TODO
 * Author tongliwei
 * Date 2020/2/13 10:53
 * Version v1.0
 **/
@Mapper
public interface CscpPackDao {
    void insert(CscpPackage cscpPackage);
    void insert_pack_func(@Param("id") String id, @Param("funcs") List<String> funcs);
    void update(CscpPackage cscpPackage);
    void delete(String id);
    void delete_pack_func(String id);
    List<CscpPackage> getPackageList(CscpPackDTO cscpPackage);
}
