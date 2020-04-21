package com.ctsi.web;

import com.ctsi.common.ResultBack;
import com.ctsi.domain.CscpServiceInfo;
import com.ctsi.service.CscpMonitorService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName CscpMonitorController
 * Description //TODO
 * Author tongliwei
 * Date 2020/3/19 10:36
 * Version v1.0
 **/
@RestController
@CrossOrigin
@Api(value = "监控接口", tags = {"监控接口"})
@RequestMapping("/api")
@Validated
public class CscpMonitorController {
    @Autowired
    private CscpMonitorService monitorService;

    @GetMapping("/cscpMonitor")
    public ResultBack getServiceList(CscpServiceInfo dto, int page, int size) {
        PageInfo<CscpServiceInfo> serviceList=monitorService.getServiceListByCond(dto,page,size);
        return ResultBack.ok(serviceList);
    }
    @PostMapping("/cscpMonitor")
    public ResultBack createService(@RequestBody CscpServiceInfo cscpServiceInfo) {
        monitorService.insert(cscpServiceInfo);
        return ResultBack.ok();
    }
    @PutMapping("/cscpMonitor")
    public ResultBack modifyService(@RequestBody CscpServiceInfo cscpServiceInfo) {
        monitorService.update(cscpServiceInfo);
        return ResultBack.ok();
    }
    @DeleteMapping("/cscpMonitor/{id}")
    public ResultBack deleteService(@PathVariable String id) {
        monitorService.delete(id);
        return ResultBack.ok();
    }
}
