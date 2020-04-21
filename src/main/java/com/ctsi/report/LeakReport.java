package com.ctsi.report;

import com.ctsi.domain.ReportInfo;
import com.ctsi.domain.ReportItem;
import com.ctsi.model.ReportResult;
import com.ctsi.service.LeakReportService;

import java.util.Date;

/**
 * ClassName LeakReport
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/11 19:00
 * Version v1.0
 **/
public class LeakReport extends ReportBase implements Report {
    private LeakReportService service;
    public LeakReport(Object service){
        this.service= (LeakReportService) service;
    }
    @Override
    public ReportItem generateReport(ReportInfo reportInfo) {
        ReportResult result=super.generate(reportInfo);
        String orgId=result.getOrgId();
        String startTime=result.getStartTime();
        String endTime=result.getEndTime();
        result.setChart4(service.getBarChart(orgId,startTime,endTime));
        result.setChart2(service.getLeakStatusPieChart(orgId,startTime,endTime));
        result.setChart1(service.getLeakLevelPieChart(orgId,startTime,endTime));
        result.setChart3(service.getLeakTypePieChart(orgId,startTime,endTime));
        result.setTitle("漏洞报表");
        result.setChart4Title("高危端口TopN");
        result.setChart1Title("漏洞等级分布");
        result.setChart3Title("漏洞类型分布");
        result.setChart2Title("漏洞状态统计");
        String summary=String.format("%s的漏洞报表，显示自%s至%s的时间段内，展示其系统漏洞信息。具体包括：漏洞等级分布、" +
                "漏洞状态统计、漏洞类型分布、高危端口TopN。详细情况请见如下。",result.getOrgName(),result.getStartTime(),result.getEndTime());
        result.setSummary(summary);
        String url= super.genPDFReport(result,reportInfo,"leakReport.jasper");
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
