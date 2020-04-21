package com.ctsi.service;

import com.ctsi.common.ResultBack;
import com.ctsi.domain.Extranet;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;

public interface ExtranetService {

    public ResultBack searchExtranetByCondition(Extranet extranet, int page, int limit);

    public ResultBack addExtranet(@Param("extranet")Extranet extranet);

    public ResultBack updateExtranet(Extranet extranet);

    public ResultBack deleteExtranet(String id);

    public ResultBack exportExtranet(HttpServletResponse response);

    public ResultBack queryCompany(String companyId);
}
