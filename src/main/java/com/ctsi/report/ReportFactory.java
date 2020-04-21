package com.ctsi.report;

import com.ctsi.common.ReportTypeEnum;
import com.ctsi.domain.ReportInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName AbstractReportFactory
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/11 19:08
 * Version v1.0
 **/
@Component
public class ReportFactory {
    private static final Map<String,Report> reportMap=new HashMap<>();
    public static synchronized Report createReport(ReportInfo info,Object dao){
        Report report=null;
        String type=info.getTypeId();
        if(reportMap.containsKey(type)){
            report=reportMap.get(type);
        }else {
            if (ReportTypeEnum.ACTION.getValue().equals(type)) {
                report = new ActionReport(dao);
            } else if (ReportTypeEnum.LOG.getValue().equals(type)) {
                report = new LogReport(dao);
            } else if (ReportTypeEnum.LEAK.getValue().equals(type)) {
                report = new LeakReport(dao);
            } else if (ReportTypeEnum.THREAT.getValue().equals(type)) {
            report = new ThreatReport(dao);
            }
            else if(ReportTypeEnum.FLOW.getValue().equals(type)) {
                report = new FlowReport(dao);
            }
            else {
                report = new AlarmReport(dao);
            }
            reportMap.put(type,report);
        }
        return report;
    }
}
