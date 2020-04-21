package com.ctsi.report;

import com.ctsi.common.CycleEnum;
import com.ctsi.dao.FlowReportDao;
import com.ctsi.domain.ReportInfo;
import com.ctsi.domain.ReportItem;
import com.ctsi.model.ReportResult;

import java.util.Date;

/**
 * ClassName FlowReport
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/11 19:01
 * Version v1.0
 **/
public class FlowReport extends ReportBase implements Report{
    private FlowReportDao dao;
    public FlowReport(Object dao){
        this.dao= (FlowReportDao) dao;
    }
    @Override
    public ReportItem generateReport(ReportInfo reportInfo) {
        ReportResult result=super.generate(reportInfo);
        String orgId=result.getOrgId();
        String startTime=result.getStartTime();
        String endTime=result.getEndTime();
        result.setChart2(dao.getProtocalPieChart(orgId,startTime,endTime));
        if(CycleEnum.DAY.getValue().equals(result.getCycle()))
            result.setChart1(dao.getLineChartByHour(orgId,startTime,endTime));
        else
            result.setChart1(dao.getLineChart(orgId,startTime,endTime));
        result.setChart3(dao.getSipBarChart(orgId,startTime,endTime));
        result.setChart4(dao.getTipBarChart(orgId,startTime,endTime));
        result.setChart5(dao.getTypeBarChart(orgId,startTime,endTime));
        result.setTitle("流量报表");
        result.setChart2Title("传输协议分布");
        result.setChart1Title("流量趋势图");
        result.setChart3Title("源IP流量数TopN");
        result.setChart4Title("目的IP流量数TopN");
        result.setChart5Title("应用协议TopN");
        String summary=String.format("%s的流量报表，显示自%s至%s的时间段内，展示其流量信息。具体包括：流量趋势图、" +
                "传输协议分布、源IP流量数TopN、、的IP流量数TopN、应用协议TopN。详细情况请见如下。",result.getOrgName(),result.getStartTime(),result.getEndTime());
        result.setSummary(summary);
        String url= super.genPDFReport(result,reportInfo,"flowReport.jasper");
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
