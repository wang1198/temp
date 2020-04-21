package com.ctsi.service;

import com.ctsi.domain.StaticsItem;
import com.ctsi.ssdc.admin.domain.ReportChartMap;

import java.util.List;

/**
 * ClassName AlarmReportService
 * Description TODO
 * Author tongliwei
 * Date 2020/4/18 17:14
 * Version v1.0
 **/
public interface AlarmReportService {
    List<ReportChartMap> getPieChart(String orgId, String begin, String end);
    List<ReportChartMap> getFlowBarChart(String orgId,String begin,String end);
    List<ReportChartMap> getLeakBarChart(String orgId,String begin,String end);
    List<ReportChartMap> getLogBarChart(String orgId,String begin,String end);
}
