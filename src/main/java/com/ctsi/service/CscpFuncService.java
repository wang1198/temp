package com.ctsi.service;

import com.ctsi.domain.CscpFunc;

import java.util.List;

/**
 * ClassName CscpFuncService
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/12 14:13
 * Version v1.0
 **/
public interface CscpFuncService {
    List<String> getFuncMenus(String orgID);
    List<CscpFunc> getFuncs(String orgID);
}
