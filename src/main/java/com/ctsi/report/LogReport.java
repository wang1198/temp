package com.ctsi.report;

import com.ctsi.domain.ReportInfo;
import com.ctsi.domain.ReportItem;
import com.ctsi.model.ReportResult;
import com.ctsi.service.LogReportService;

import java.util.Date;

/**
 * ClassName LogReport
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/11 18:58
 * Version v1.0
 **/
public class LogReport extends ReportBase implements Report {
    private LogReportService service;
    public LogReport(Object service){
        this.service= (LogReportService) service;
    }
    @Override
    public ReportItem generateReport(ReportInfo reportInfo) {
        ReportResult result=super.generate(reportInfo);
        String orgId=result.getOrgId();
        String startTime=result.getStartTime();
        String endTime=result.getEndTime();
        result.setChart3(service.getBarChart(orgId,startTime,endTime));
        result.setChart2(service.getPieChart(orgId,startTime,endTime));
        result.setChart1(service.getLineChart(orgId,startTime,endTime));
        result.setTitle("日志报表");
        result.setChart3Title("日志量TopN");
        result.setChart2Title("事件等级分类");
        result.setChart1Title("日志量趋势");
        String summary=String.format("%s的日志报表，显示自%s至%s的时间段内，展示其系统日志信息。具体包括：日志量趋势、" +
                "事件等级分类、日志量TopN。详细情况请见如下。",result.getOrgName(),result.getStartTime(),result.getEndTime());
        result.setSummary(summary);
        String url= super.genPDFReport(result,reportInfo,"logReport.jasper");
        ReportItem item=new ReportItem();
        item.setRid(result.getId());
        item.setStartTime(result.getStartTime());
        item.setEndTime(result.getEndTime());
        item.setFileName(url);
        item.setReportName(result.getTitle());
        item.setCreator(result.getCreator());
        item.setCreateTime(new Date());
        return item;
    }
}
