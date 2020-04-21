package com.ctsi.service.impl;

import com.ctsi.dao.LogReportDao;
import com.ctsi.domain.StaticsItem;
import com.ctsi.service.LogReportService;
import com.ctsi.ssdc.admin.domain.ReportChartMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName LogReportServiceImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/15 14:31
 * Version v1.0
 **/
@Service
public class LogReportServiceImpl implements LogReportService {
    @Autowired
    private LogReportDao logReportDao;
    @Override
    public List<ReportChartMap> getPieChart(String orgId, String begin, String end) {
        List<ReportChartMap> list=new ArrayList<>(5);
        StaticsItem item= logReportDao.getPieChart(orgId,begin,end);
        if(item!=null){
            list.add(toChartMap("普通",item.getLight()));
            list.add(toChartMap("低",item.getLow()));
            list.add(toChartMap("中",item.getMiddle()));
            list.add(toChartMap("高",item.getHigh()));
            list.add(toChartMap("严重",item.getSerious()));
        }
        return list;
    }

    @Override
    public List<ReportChartMap> getBarChart(String orgId, String begin, String end) {
        return logReportDao.getBarChart(orgId,begin,end);
    }

    @Override
    public List<ReportChartMap> getLineChart(String orgId, String begin, String end) {
        return logReportDao.getLineChart(orgId,begin,end);
    }
    private ReportChartMap toChartMap(String key,Integer value){
        ReportChartMap map=new ReportChartMap();
        map.setItemName(key);
        map.setItemValue(value);
        return map;
    }
}
