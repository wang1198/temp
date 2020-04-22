package com.ctsi.service;

import com.ctsi.common.ResultBack;
import com.ctsi.domain.Intranet;

import javax.servlet.http.HttpServletResponse;

public interface IntranetService {

    public ResultBack searchIntranetByCondition(Intranet intranet, int page, int limit);

    public ResultBack addIntranet(Intranet intranet);

    public ResultBack updateIntranet(Intranet intranet);

    public ResultBack deleteIntranet(String id);

    public ResultBack searchIntranetById(String id);

    public ResultBack exportExtranet(HttpServletResponse response);
}
