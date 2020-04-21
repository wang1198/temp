package com.ctsi.web;

import com.ctsi.common.ResultBack;
import com.ctsi.domain.CscpBusiness;
import com.ctsi.model.CscpBusinessDTO;
import com.ctsi.service.CscpBusinessService;
import com.ctsi.utils.ExcelUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
@Api(value = "业务接口", tags = {"业务接口"})
@RequestMapping("/api")
@Validated
public class CscpBusinessController {
    @Autowired
    private CscpBusinessService businessService;

    @GetMapping("/cscpBusiness")
    public ResultBack getBusinessList(CscpBusinessDTO dto,int page, int size) {
        PageInfo<CscpBusinessDTO> businessList=businessService.getBusinessList(dto,page,size);
        return ResultBack.ok(businessList);
    }
    @PostMapping("/cscpBusiness")
    public ResultBack createBusiness(@RequestBody CscpBusiness cscpBusiness) {
        businessService.insert(cscpBusiness);
        return ResultBack.ok();
    }
    @PutMapping("/cscpBusiness")
    public ResultBack modifyBusiness(@RequestBody CscpBusiness cscpBusiness) {
        businessService.update(cscpBusiness);
        return ResultBack.ok();
    }
    @DeleteMapping("/cscpBusiness/{id}")
    public ResultBack deleteBusiness(@PathVariable String id) {
        List idList = new ArrayList();
        String[] strs = id.split(",");
        for (String str:strs){
            idList.add(str);
        }
        businessService.delete(idList);
        return ResultBack.ok();
    }
    @PostMapping(value = "/importBusiness")
    public ResultBack readExcel(@RequestParam("file") MultipartFile file,String orgId,String orgName){
        List<CscpBusiness> list = ExcelUtil.readExcel(CscpBusiness.class, file);
        List<String> result=businessService.importBusiness(list,orgId,orgName);
        return ResultBack.ok(result);
    }
}
