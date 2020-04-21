package com.ctsi.web;

import com.ctsi.common.ResultBack;
import com.ctsi.dao.ExtranetMapper;
import com.ctsi.domain.Extranet;
import com.ctsi.service.ExtranetService;
import com.ctsi.ssdc.admin.domain.ProvinceCityDistrict;
import com.ctsi.ssdc.admin.domain.util.Result;
import com.ctsi.ssdc.admin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExtranetController {

    @Autowired
    private ExtranetService extranetService;

    @Autowired
    private CustomerService customerService;

    @Resource
    private ExtranetMapper extranetMapper;

    @GetMapping("/searchExtranetByCondition")
    public ResultBack searchExtranetByCondition(Extranet extranet, @RequestParam int page, @RequestParam int limit){
        return extranetService.searchExtranetByCondition(extranet,page,limit);
    }

    @PostMapping("/addExtranet")
    public ResultBack addExtranet(@RequestBody Extranet extranet){
        return extranetService.addExtranet(extranet);
    }

    @PutMapping("/updateExtranet")
    public ResultBack updateExtranet(@RequestBody Extranet extranet){
        return extranetService.updateExtranet(extranet);
    }


    @DeleteMapping("/deleteExtranet")
    public ResultBack deleteExtranet(@RequestParam("id")String id){
        return extranetService.deleteExtranet(id);
    }

    @GetMapping("/exportExtranet")
    public ResultBack exportExtranet(HttpServletResponse response) {
        return extranetService.exportExtranet(response);
    }

    public Result getProvince(){
        List<ProvinceCityDistrict> provinceList = customerService.getProvince();
        return null;
    }
}
