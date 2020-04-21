package com.ctsi.web;

import com.ctsi.common.ResultBack;
import com.ctsi.domain.ReportInfo;
import com.ctsi.domain.ReportItem;
import com.ctsi.model.ReportInfoDTO;
import com.ctsi.model.ReportTypeInfo;
import com.ctsi.service.CscpReportService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * ClassName ReportApi
 * Description //TODO
 * Author tongliwei
 * Date 2019/5/13 15:39
 * Version v1.0
 **/
@RestController
@CrossOrigin
@Api(value = "报表接口", tags = {"报表接口"})
@RequestMapping("/api")
public class CscpReportController {
    private static final Logger log = LoggerFactory.getLogger(CscpReportController.class);
    @Autowired
    private CscpReportService reportService;

    @ApiOperation(value = "新建报表", notes = "新建报表")
    @PostMapping(value = "createReport", produces = "application/json; charset=utf-8")
    public ResultBack createReport(HttpServletRequest request, @RequestBody @ApiParam(name = "报表对象", value = "json对象", required = true) @Valid ReportInfoDTO info) {
        String username=request.getRemoteUser();
        info.setCreator(username);
        reportService.createReport(info);
        return ResultBack.ok();
    }
    @ApiOperation(value = "修改报表", notes = "修改报表")
    @PutMapping(value = "modifyReport", produces = "application/json; charset=utf-8")
    public ResultBack modifyReport(@RequestBody @ApiParam(name = "报表对象", value = "json对象", required = true) @Valid ReportInfoDTO info) {
        reportService.modifyReport(info);
        return ResultBack.ok();
    }
    @ApiOperation(value = "获取报表", notes = "获取报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "条数", required = true, dataType = "int", paramType = "query")})
    @GetMapping(value = "/getReport", produces = "application/json; charset=utf-8")
    public ResultBack getReport(HttpServletRequest request, ReportInfo info,@RequestParam int page, @RequestParam int limit) {
        info.setCreator(request.getRemoteUser());
        PageInfo<ReportInfo> infos=reportService.getReport(page,limit,info);
        return ResultBack.ok(infos);
    }

    @ApiOperation(value = "获取报表项", notes = "获取报表项")
    @GetMapping(value = "/getReportItem", produces = "application/json; charset=utf-8")
    public ResultBack getReportItem(HttpServletRequest request,ReportItem item, @RequestParam int page, @RequestParam int limit) {
        item.setCreator(request.getRemoteUser());
        PageInfo<ReportItem> items=reportService.getReportItem(item,page,limit);
        return ResultBack.ok(items);
    }

    @ApiOperation(value = "删除报表", notes = "删除报表")
    @DeleteMapping(value = "/deleteReport", produces = "application/json; charset=utf-8")
    public ResultBack deleteReport(@RequestParam Integer id) {
        reportService.deleteReport(id);
        return ResultBack.ok();
    }
    @ApiOperation(value = "删除报表项", notes = "删除报表项")
    @DeleteMapping(value = "/deleteReportItem",produces = "application/json; charset=utf-8")
    public ResultBack deleteReportItem(@RequestParam Integer id) {
        reportService.deleteReportItem(id);
        return ResultBack.ok();
    }
    @ApiOperation(value = "获取报表分类", notes = "获取报表分类")
    @GetMapping(value = "/getReportType",produces = "application/json; charset=utf-8")
    public ResultBack getReportType(@RequestParam String orgId) {
        List<ReportTypeInfo> infos=reportService.getReportType(orgId);
        return ResultBack.ok(infos);
    }
}
