package com.ctsi.service;

import com.ctsi.ssdc.admin.domain.CscpDic;

import java.util.List;

/**
 * ClassName CscpDicFileService
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/8 13:51
 * Version v1.0
 **/
public interface CscpDicFileService {
    void importDic(List<CscpDic> dics);
    List<CscpDic> exportDic(CscpDic dic);
}
