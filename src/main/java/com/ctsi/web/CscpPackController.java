package com.ctsi.web;

import com.ctsi.common.ResultBack;
import com.ctsi.domain.CscpFunc;
import com.ctsi.domain.CscpPackage;
import com.ctsi.model.CscpPackDTO;
import com.ctsi.service.CscpFuncService;
import com.ctsi.service.CscpPackService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName CscpBusinessController
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/2 21:21
 * Version v1.0
 **/
@RestController
@CrossOrigin
@Api(value = "套餐接口", tags = {"套餐接口"})
@RequestMapping("/api")
@Validated
public class CscpPackController {
    @Autowired
    private CscpPackService packService;
    @Autowired
    private CscpFuncService funcService;

    @GetMapping("/cscpPackages")
    public ResultBack getPackageList(CscpPackDTO dto,int page,int size) {
        PageInfo<CscpPackDTO> packageList=packService.getPackageList(dto,page,size);
        return ResultBack.ok(packageList);
    }
    @GetMapping("/cscpFuncs")
    public ResultBack getFuncList(String orgID) {
        List<CscpFunc> funcList=funcService.getFuncs(orgID);
        return ResultBack.ok(funcList);
    }
    @PostMapping("/cscpPackages")
    public ResultBack createPackage(@RequestBody CscpPackDTO cscpPackage) {
        packService.insert(cscpPackage);
        return ResultBack.ok();
    }
    @PutMapping("/cscpPackages")
    public ResultBack modifyPackage(@RequestBody CscpPackDTO cscpPackage) {
        packService.update(cscpPackage);
        return ResultBack.ok();
    }
    @DeleteMapping("/cscpPackages/{id}")
    public ResultBack deletePackage(@PathVariable String id) {
        packService.delete(id);
        return ResultBack.ok();
    }
}

