package com.ctsi.service;

import com.ctsi.model.CscpPackDTO;
import com.github.pagehelper.PageInfo;

/**
 * ClassName CscpPackService
 * Description TODO
 * Author tongliwei
 * Date 2020/2/13 10:38
 * Version v1.0
 **/
public interface CscpPackService {
    void insert(CscpPackDTO cscpPackage);
    void update(CscpPackDTO cscpPackage);
    void delete(String id);
    PageInfo<CscpPackDTO> getPackageList(CscpPackDTO dto,int page,int limit);
}
