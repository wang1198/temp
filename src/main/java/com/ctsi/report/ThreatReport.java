package com.ctsi.report;

import com.ctsi.common.CycleEnum;
import com.ctsi.dao.ThreatReportDao;
import com.ctsi.domain.ReportInfo;
import com.ctsi.domain.ReportItem;
import com.ctsi.model.ReportResult;

import java.util.Date;

/**
 * ClassName ThreatReport
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/11 18:59
 * Version v1.0
 **/
public class ThreatReport extends ReportBase implements Report {
    private ThreatReportDao dao;
    public ThreatReport(Object dao){
        this.dao= (ThreatReportDao) dao;
    }
    @Override
    public ReportItem generateReport(ReportInfo reportInfo) {
        ReportResult result=super.generate(reportInfo);
        String orgId=result.getOrgId();
        String startTime=result.getStartTime();
        String endTime=result.getEndTime();
        result.setChart2(dao.getSipBarChart(orgId,startTime,endTime));
        if(CycleEnum.DAY.getValue().equals(result.getCycle()))
            result.setChart1(dao.getLineChartByHour(orgId,startTime,endTime));
        else
            result.setChart1(dao.getLineChart(orgId,startTime,endTime));
        result.setChart3(dao.getTipBarChart(orgId,startTime,endTime));
        result.setTitle("威胁情报报表");
        result.setChart2Title("受风险资产TopN");
        result.setChart1Title("威胁情报趋势");
        result.setChart3Title("风险资产TopN");
        String summary=String.format("%s的威胁情报报表，显示自%s至%s的时间段内，展示其威胁情报信息。具体包括：威胁情报趋势、" +
                "受风险资产TopN、风险资产TopN。详细情况请见如下。",result.getOrgName(),result.getStartTime(),result.getEndTime());
        result.setSummary(summary);
        String url= super.genPDFReport(result,reportInfo,"threatReport.jasper");
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
