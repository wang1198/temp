package com.ctsi.dao;

import com.ctsi.domain.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportMapper {
    String queryFileNameById(@Param("id") String id);
    List<Report> queryReportByType(@Param("reportType") Integer reportType);
    Report queryLastestReportByType(@Param("reportType") Integer reportType);
    void addReport(Report report);
    void updateReport(Report report);

}
