package com.ctsi.dao;

import com.ctsi.domain.ReportInfo;
import com.ctsi.domain.ReportItem;
import com.ctsi.model.ReportTypeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName ReportDao
 * Description //TODO
 * Author tongliwei
 * Date 2019/5/13 15:33
 * Version v1.0
 **/
@Mapper
public interface ReportDao {
    void createReport(ReportInfo info);
    void modifyReport(ReportInfo info);
    void saveReportItem(ReportItem item);
    List<ReportInfo> getReport(ReportInfo info);
    List<ReportItem> getReportItem(ReportItem item);
    void deleteReport(Integer id);
    void deleteReportItem(Integer id);
    List<ReportTypeInfo> getReportType(String orgId);
    List<ReportInfo> getReport();
}
