package com.ctsi.report;

import com.ctsi.common.CycleEnum;
import com.ctsi.domain.ReportInfo;
import com.ctsi.domain.ReportItem;
import com.ctsi.model.ReportResult;
import com.ctsi.ssdc.admin.repository.ActionReportDao;

import java.util.Date;

/**
 * ClassName ActionReport
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/11 19:01
 * Version v1.0
 **/
public class ActionReport extends ReportBase implements Report{
    private ActionReportDao dao;
    public ActionReport(Object dao){
        this.dao= (ActionReportDao) dao;
    }
    @Override
    public ReportItem generateReport(ReportInfo reportInfo) {
        ReportResult result=super.generate(reportInfo);
        String orgId=result.getOrgId();
        String startTime=result.getStartTime();
        String endTime=result.getEndTime();
        result.setChart3(dao.getBarChart(orgId,startTime,endTime));
        result.setChart2(dao.getPieChart(orgId,startTime,endTime));
        if(CycleEnum.DAY.getValue().equals(result.getCycle()))
            result.setChart1(dao.getLineChartByHour(orgId,startTime,endTime));
        else
            result.setChart1(dao.getLineChart(orgId,startTime,endTime));
        result.setChart4(dao.getIpBarChart(orgId,startTime,endTime));
        result.setTitle("行为审计报表");
        result.setChart3Title("登录频度TopN");
        result.setChart2Title("登录状态分布");
        result.setChart1Title("登录次数趋势");
        result.setChart4Title("IP登录频度TopN");
        String summary=String.format("%s的行为审计报表，显示自%s至%s的时间段内，展示其用户登录行为信息。具体包括：登录次数趋势、" +
                "登录状态分布、登录频度TopN、IP登录频度TopN。详细情况请见如下。",result.getOrgName(),result.getStartTime(),result.getEndTime());
        result.setSummary(summary);
        String url= super.genPDFReport(result,reportInfo,"actionReport.jasper");
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
