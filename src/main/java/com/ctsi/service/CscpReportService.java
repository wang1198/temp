package com.ctsi.service;

import com.ctsi.domain.ReportInfo;
import com.ctsi.domain.ReportItem;
import com.ctsi.model.ReportInfoDTO;
import com.ctsi.model.ReportTypeInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * ClassName CscpReportService
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/6 20:23
 * Version v1.0
 **/
public interface CscpReportService {
    PageInfo<ReportInfo> getReport(int page, int limit,ReportInfo info);
    PageInfo<ReportItem> getReportItem(ReportItem item, int page, int limit);
    void createReport(ReportInfoDTO info);
    void modifyReport(ReportInfoDTO info);
    void deleteReport(Integer id);
    void deleteReportItem(Integer id);
    List<ReportTypeInfo> getReportType(String orgId);
}
