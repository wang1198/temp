package com.ctsi.service;

import com.ctsi.ssdc.admin.domain.ReportChartMap;

import java.util.List;

/**
 * ClassName LeakReportService
 * Description TODO
 * Author tongliwei
 * Date 2020/4/15 11:26
 * Version v1.0
 **/
public interface LeakReportService {
    List<ReportChartMap> getLeakStatusPieChart(String orgId, String begin, String end);
    List<ReportChartMap> getLeakTypePieChart(String orgId, String begin, String end);
    List<ReportChartMap> getBarChart(String orgId, String begin, String end);
    List<ReportChartMap> getLeakLevelPieChart(String orgId, String begin, String end);
}
