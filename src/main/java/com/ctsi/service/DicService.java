package com.ctsi.service;

import com.ctsi.ssdc.admin.domain.util.Result;
import com.ctsi.utils.ResultBack;

import javax.servlet.http.HttpServletRequest;

public interface DicService {
    public ResultBack getDicByType(String paraType);

    public ResultBack queryLocation();

    public ResultBack queryCompany();

    public ResultBack queryAttackSystem(String projectId, HttpServletRequest request);
}
