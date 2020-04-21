package com.ctsi.service.impl;

import com.ctsi.dao.AlarmReportDao;
import com.ctsi.domain.StaticsItem;
import com.ctsi.service.AlarmReportService;
import com.ctsi.ssdc.admin.domain.ReportChartMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName AlarmReportServiceImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/18 17:17
 * Version v1.0
 **/
@Service
public class AlarmReportServiceImpl implements AlarmReportService {
    @Autowired
    private AlarmReportDao alarmReportDao;
    @Override
    public List<ReportChartMap> getPieChart(String orgId, String begin, String end) {
        List<ReportChartMap> list=new ArrayList<>(5);
        StaticsItem item= alarmReportDao.getPieChart(orgId,begin,end);
        if(item!=null){
            list.add(toChartMap("info",item.getLight()));
            list.add(toChartMap("light",item.getLow()));
            list.add(toChartMap("middle",item.getMiddle()));
            list.add(toChartMap("high",item.getHigh()));
            list.add(toChartMap("serious",item.getSerious()));
        }
        return list;
    }

    @Override
    public List<ReportChartMap> getFlowBarChart(String orgId, String begin, String end) {
        List<ReportChartMap> list=new ArrayList<>(5);
        StaticsItem item= alarmReportDao.getFlowBarChart(orgId,begin,end);
        if(item!=null){
            list.add(toChartMap("info",item.getLight()));
            list.add(toChartMap("light",item.getLow()));
            list.add(toChartMap("middle",item.getMiddle()));
            list.add(toChartMap("high",item.getHigh()));
            list.add(toChartMap("serious",item.getSerious()));
        }
        return list;
    }

    @Override
    public List<ReportChartMap> getLeakBarChart(String orgId, String begin, String end) {
        List<ReportChartMap> list=new ArrayList<>(5);
        StaticsItem item= alarmReportDao.getLeakBarChart(orgId,begin,end);
        if(item!=null){
            list.add(toChartMap("info",item.getLight()));
            list.add(toChartMap("light",item.getLow()));
            list.add(toChartMap("middle",item.getMiddle()));
            list.add(toChartMap("high",item.getHigh()));
            list.add(toChartMap("serious",item.getSerious()));
        }
        return list;
    }

    @Override
    public List<ReportChartMap> getLogBarChart(String orgId, String begin, String end) {
        List<ReportChartMap> list=new ArrayList<>(5);
        StaticsItem item= alarmReportDao.getLogBarChart(orgId,begin,end);
        if(item!=null){
            list.add(toChartMap("info",item.getLight()));
            list.add(toChartMap("light",item.getLow()));
            list.add(toChartMap("middle",item.getMiddle()));
            list.add(toChartMap("high",item.getHigh()));
            list.add(toChartMap("serious",item.getSerious()));
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
