package com.ctsi.web;

import com.ctsi.common.ResultBack;
import com.ctsi.domain.Intranet;
import com.ctsi.service.IntranetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class IntranetController {
    @Autowired
    private IntranetService intranetService;

    @GetMapping("/searchIntranetByCondition")
    public ResultBack searchIntranetByCondition(Intranet intranet, @RequestParam int page, @RequestParam int limit){
        return intranetService.searchIntranetByCondition(intranet,page,limit);
    }

    @PostMapping("/addIntranet")
    public ResultBack addIntranet(@RequestBody Intranet intranet){
        return intranetService.addIntranet(intranet);
    }

    @PutMapping("/updateIntranet")
    public ResultBack updateIntranet(@RequestBody Intranet intranet){
        return intranetService.updateIntranet(intranet);
    }

    @DeleteMapping("/deleteIntranet")
    public ResultBack deleteIntranet(@RequestParam("id") String id){
        return intranetService.deleteIntranet(id);
    }
    @GetMapping("/searchIntranetById")
    public ResultBack searchIntranetById(@RequestParam("id")String id) {
        return intranetService.searchIntranetById(id);
    }

    @GetMapping("/exportIntranet")
    public ResultBack exportIntranet(HttpServletResponse response){
        return intranetService.exportExtranet(response);
    }
}
