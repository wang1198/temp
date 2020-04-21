package com.ctsi.service;

import com.ctsi.common.ResultBack;
import com.ctsi.domain.Intranet;

import javax.servlet.http.HttpServletResponse;

public interface IntranetService {

    ResultBack searchIntranetByCondition(Intranet intranet, int page, int limit);

    ResultBack addIntranet(Intranet intranet);

    ResultBack updateIntranet(Intranet intranet);

    ResultBack deleteIntranet(String id);

    ResultBack searchIntranetById(String id);

    ResultBack exportExtranet(HttpServletResponse response);
}
