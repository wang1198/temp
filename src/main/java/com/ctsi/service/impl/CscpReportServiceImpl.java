package com.ctsi.service.impl;

import com.ctsi.dao.ReportDao;
import com.ctsi.domain.ReportInfo;
import com.ctsi.domain.ReportItem;
import com.ctsi.model.ReportInfoDTO;
import com.ctsi.model.ReportMapper;
import com.ctsi.model.ReportTypeInfo;
import com.ctsi.service.CscpReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName CscpReportServiceImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/6 20:25
 * Version v1.0
 **/
@Service
public class CscpReportServiceImpl implements CscpReportService {
    @Autowired
    private ReportDao reportDao;
    @Autowired
    private ReportMapper reportMapper;
    @Override
    public PageInfo<ReportInfo> getReport(int page, int limit, ReportInfo info) {
        PageHelper.startPage(page,limit);
        List<ReportInfo> list=reportDao.getReport(info);
        PageInfo<ReportInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<ReportItem> getReportItem(ReportItem item, int page, int limit) {
        PageHelper.startPage(page, limit, true);
        List<ReportItem> list = reportDao.getReportItem(item);
        PageInfo<ReportItem> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void createReport(ReportInfoDTO info) {
        reportDao.createReport(reportMapper.toEntity(info));
    }

    @Override
    public void modifyReport(ReportInfoDTO info) {
        reportDao.modifyReport(reportMapper.toEntity(info));
    }

    @Override
    public void deleteReport(Integer id) {
        reportDao.deleteReport(id);
    }

    @Override
    public void deleteReportItem(Integer id) {
        reportDao.deleteReportItem(id);
    }

    @Override
    public List<ReportTypeInfo> getReportType(String orgId) {
        List<ReportTypeInfo> infos=reportDao.getReportType(orgId);
        return infos;
    }
}
