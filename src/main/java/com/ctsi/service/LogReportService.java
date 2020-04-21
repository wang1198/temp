package com.ctsi.service;

import com.ctsi.ssdc.admin.domain.ReportChartMap;

import java.util.List;

/**
 * ClassName LogReportService
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/15 14:31
 * Version v1.0
 **/
public interface LogReportService {
    List<ReportChartMap> getPieChart(String orgId, String begin, String end);
    List<ReportChartMap> getBarChart(String orgId, String begin, String end);
    List<ReportChartMap> getLineChart(String orgId, String begin, String end);
}
