package com.ctsi.web;

/**
 * ClassName CscpDicImportController
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/8 13:48
 * Version v1.0
 **/

import com.ctsi.common.ResultBack;
import com.ctsi.service.CscpDicFileService;
import com.ctsi.ssdc.admin.domain.CscpDic;
import com.ctsi.utils.ExcelUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@Api(value = "字典导入接口", tags = {"字典导入接口"})
@RequestMapping("/api")
public class CscpDicImportController {
    @Autowired
    private CscpDicFileService cscpDicFileService;
    @Value("${web.download-path}")
    private String path;
    @Value("${ctsi.serverAddress}")
    private String serverAddress;
    @PostMapping(value = "/importDic")
    public ResultBack importDic(MultipartFile file){
        List<CscpDic> list = ExcelUtil.readExcel(CscpDic.class, file);
        cscpDicFileService.importDic(list);
        return ResultBack.ok();
    }
    @GetMapping(value = "/exportDic")
    public ResultBack exportDic(CscpDic dic){
        List<CscpDic> list=cscpDicFileService.exportDic(dic);
        String file=path+new SimpleDateFormat("yyyyMMdd").format(new Date())+".xlsx";
        ExcelUtil.writeExcel(list, CscpDic.class,file);
        String url= serverAddress+file.substring(file.lastIndexOf("/")+1);
        return ResultBack.ok(url);
    }
}
