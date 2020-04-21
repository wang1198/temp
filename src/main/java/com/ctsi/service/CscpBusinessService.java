package com.ctsi.service;

import com.ctsi.domain.CscpBusiness;
import com.ctsi.model.CscpBusinessDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * ClassName CscpBusinessService
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/2 21:22
 * Version v1.0
 **/
public interface CscpBusinessService {
    PageInfo<CscpBusinessDTO> getBusinessList(CscpBusinessDTO dto, int page, int limit);
    void insert(CscpBusiness business);
    void update(CscpBusiness business);
    void delete(List<String> id);
    List<String> importBusiness(List<CscpBusiness> list,String orgId,String orgName);
}
