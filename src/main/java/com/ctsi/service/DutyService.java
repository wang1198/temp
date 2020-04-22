package com.ctsi.service;

import com.ctsi.utils.ResultBack;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface DutyService {
    public ResultBack importDuty(MultipartFile file);

    public ResultBack exportDuty(HttpServletResponse response);

    public ResultBack searchDuty();
}
