package com.ctsi.service;

import com.ctsi.common.ResultBack;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface DutyService {
    ResultBack importDuty(MultipartFile file);

    ResultBack exportDuty(HttpServletResponse response);

    ResultBack searchDuty(int page,int limit);
}
