package com.ctsi.web;

import com.ctsi.common.ResultBack;
import com.ctsi.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DicController {
    @Autowired
    private DicService dicService;

    @GetMapping("/getDicByType")
    public ResultBack getDicByType(@RequestParam("paraType") String paraType){
        return dicService.getDicByType(paraType);
    }

    @GetMapping("/queryLocation")
    public ResultBack queryLocation(){
        return dicService.queryLocation();
    }

    @GetMapping("/queryCompany")
    public ResultBack queryCompany(){
        return dicService.queryCompany();
    }
}
