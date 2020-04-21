package com.ctsi.report;

import com.ctsi.dao.AlarmReportDao;
import com.ctsi.domain.ReportInfo;
import com.ctsi.domain.ReportItem;
import com.ctsi.model.ReportResult;
import com.ctsi.service.AlarmReportService;

import java.util.Date;

/**
 * ClassName AlarmReport
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/11 18:59
 * Version v1.0
 **/
public class AlarmReport extends ReportBase implements Report {
    private AlarmReportService service;
    public AlarmReport(Object service){
        this.service= (AlarmReportService) service;
    }
    @Override
    public ReportItem generateReport(ReportInfo reportInfo) {
        ReportResult result=super.generate(reportInfo);
        String orgId=result.getOrgId();
        String startTime=result.getStartTime();
        String endTime=result.getEndTime();
        result.setChart2(service.getLeakBarChart(orgId,startTime,endTime));
        result.setChart1(service.getPieChart(orgId,startTime,endTime));
        result.setChart3(service.getFlowBarChart(orgId,startTime,endTime));
        result.setChart4(service.getLogBarChart(orgId,startTime,endTime));
        result.setTitle("告警报表");
        result.setChart2Title("漏洞告警分布");
        result.setChart1Title("告警类别分布");
        result.setChart3Title("流量告警分布");
        result.setChart4Title("日志告警分布");
        String summary=String.format("%s的告警报表，显示自%s至%s的时间段内，展示其告警信息。具体包括：告警类别分布、" +
                "漏洞告警分布、流量告警分布、日志告警分布。详细情况请见如下。",result.getOrgName(),result.getStartTime(),result.getEndTime());
        result.setSummary(summary);
        String url= super.genPDFReport(result,reportInfo,"alarmReport.jasper");
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
