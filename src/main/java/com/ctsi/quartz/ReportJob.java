package com.ctsi.quartz;

import com.ctsi.common.ReportTypeEnum;
import com.ctsi.common.ScopeEnum;
import com.ctsi.dao.*;
import com.ctsi.domain.ReportInfo;
import com.ctsi.domain.ReportItem;
import com.ctsi.report.ReportFactory;
import com.ctsi.service.AlarmReportService;
import com.ctsi.service.LeakReportService;
import com.ctsi.service.LogReportService;
import com.ctsi.ssdc.admin.repository.ActionReportDao;
import com.ctsi.utils.DateUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ClassName ReportJob
 * Description //TODO
 * Author tongliwei
 * Date 2019/5/14 15:01
 * Version v1.0
 **/
public class ReportJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(ReportJob.class);
    @Autowired
    private ReportDao reportDao;
    @Autowired
    private ActionReportDao actionReportDao;
    @Autowired
    private LogReportService logReportService;
    @Autowired
    private LeakReportService leakReportService;
    @Autowired
    private FlowReportDao flowReportDao;
    @Autowired
    private AlarmReportService alarmReportService;
    @Autowired
    private ThreatReportDao threatReportDao;
    @Value("${web.download-path}")
    private String path;
    @Value("${ctsi.serverAddress}")
    private String serverAddress;
    @Override
    @Transactional
    public void execute(JobExecutionContext context) {
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
        List<ReportInfo> reportInfos=reportDao.getReport();
        for (ReportInfo reportInfo:reportInfos) {
            if(reportInfo.getScope().equals(ScopeEnum.WEEK.getValue())) {
                if (!format.format(DateUtils.getBeginDayOfWeek()).equals(format.format(new Date())))
                    continue;
            }
            if(reportInfo.getScope().equals(ScopeEnum.MONTH.getValue())) {
                if (!format.format(DateUtils.getBeginDayOfMonth()).equals(format.format(new Date())))
                    continue;
            }
            reportInfo.setPath(path);
            reportInfo.setUrl(serverAddress);
            String type=reportInfo.getTypeId();
            ReportItem item=null;
            if (ReportTypeEnum.ACTION.getValue().equals(type))
                item =ReportFactory.createReport(reportInfo,actionReportDao).generateReport(reportInfo);
            else if (ReportTypeEnum.LOG.getValue().equals(type))
                item =ReportFactory.createReport(reportInfo,logReportService).generateReport(reportInfo);
            else if (ReportTypeEnum.LEAK.getValue().equals(type))
                item =ReportFactory.createReport(reportInfo,leakReportService).generateReport(reportInfo);
            else if (ReportTypeEnum.THREAT.getValue().equals(type))
                item =ReportFactory.createReport(reportInfo,threatReportDao).generateReport(reportInfo);
            else if(ReportTypeEnum.FLOW.getValue().equals(type))
                item =ReportFactory.createReport(reportInfo,flowReportDao).generateReport(reportInfo);
            else
                item =ReportFactory.createReport(reportInfo,alarmReportService).generateReport(reportInfo);
            if (item!=null)
                reportDao.saveReportItem(item);
        }
    }
}
