package com.ctsi.service.impl;

import com.ctsi.dao.LeakReportDao;
import com.ctsi.domain.StaticsItem;
import com.ctsi.service.LeakReportService;
import com.ctsi.ssdc.admin.domain.ReportChartMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName StaticsItem
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/15 11:51
 * Version v1.0
 **/
@Service
public class LeakReportServiceImpl implements LeakReportService {
    @Autowired
    private LeakReportDao leakReportDao;
    @Override
    public List<ReportChartMap> getLeakLevelPieChart(String orgId, String begin, String end) {
        List<ReportChartMap> list=new ArrayList<>(3);
        StaticsItem item= leakReportDao.getLeakLevelPieChart(orgId,begin,end);
        if(item!=null){
            list.add(toChartMap("低",item.getLow()));
            list.add(toChartMap("中",item.getMiddle()));
            list.add(toChartMap("高",item.getHigh()));
        }
        return list;
    }

    @Override
    public List<ReportChartMap> getBarChart(String orgId, String begin, String end) {
        return leakReportDao.getBarChart(orgId,begin,end);
    }

    @Override
    public List<ReportChartMap> getLeakTypePieChart(String orgId, String begin, String end) {
        List<ReportChartMap> list=new ArrayList<>(3);
        StaticsItem item= leakReportDao.getLeakTypePieChart(orgId,begin,end);
        if(item!=null){
            list.add(toChartMap("系统化",item.getSystem()));
            list.add(toChartMap("应用",item.getApplication()));
            list.add(toChartMap("其他",item.getOther()));
        }
        return list;
    }

    @Override
    public List<ReportChartMap> getLeakStatusPieChart(String orgId, String begin, String end) {
        List<ReportChartMap> list=new ArrayList<>(9);
        StaticsItem item= leakReportDao.getLeakStatusPieChart(orgId,begin,end);
        if(item!=null){
            list.add(toChartMap("已确认",item.getConfirmed()));
            list.add(toChartMap("已指派",item.getAssigned()));
            list.add(toChartMap("处理中",item.getProcessing()));
            list.add(toChartMap("已加固",item.getReinforced()));
            list.add(toChartMap("已复测",item.getRetested()));
            list.add(toChartMap("无法加固",item.getNoreinforced()));
            list.add(toChartMap("误报",item.getMisreport()));
            list.add(toChartMap("新发现",item.getBreakthrough()));
            list.add(toChartMap("已复现",item.getReproduced()));
        }
        return list;
    }

    private ReportChartMap toChartMap(String key,Integer value){
        ReportChartMap map=new ReportChartMap();
        map.setItemName(key);
        map.setItemValue(value);
        return map;
    }
}
