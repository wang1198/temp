package com.ctsi.web;

import com.ctsi.service.DutyService;
import com.ctsi.utils.ResultBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class DutyController {

    @Autowired
    private DutyService dutyService;

    @PostMapping("/importDuty")
    public ResultBack importDuty(MultipartFile file){
        return dutyService.importDuty(file);
    }

    @GetMapping("/exportDuty")
    public ResultBack exportDuty(HttpServletResponse response){
        return dutyService.exportDuty(response);
    }

    @GetMapping("/searchDuty")
    public ResultBack searchDuty(){
        return dutyService.searchDuty();
    }
}
