package com.ctsi.report;

import com.ctsi.common.CycleEnum;
import com.ctsi.domain.ReportInfo;
import com.ctsi.domain.ReportItem;
import com.ctsi.model.ReportResult;
import com.ctsi.utils.DateUtils;
import com.ctsi.utils.JasperUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName ReportBase
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/11 20:20
 * Version v1.0
 **/
public class ReportBase {
    private static final Logger log = LoggerFactory.getLogger(ReportBase.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    public ReportResult generate(ReportInfo reportInfo){
        ReportResult result=new ReportResult();
        result.setId(reportInfo.getId());
        result.setCycle(reportInfo.getCycle());
        result.setOrgId(reportInfo.getOrgId());
        result.setCreator(reportInfo.getCreator());
        result.setCreateTime(sdf.format(new Date()));
        result.setCycleDesc(reportInfo.getCycleDesc());
        Date begin;
        Date end;
        result.setOrgName(reportInfo.getOrgName());
        if(reportInfo.getCycle().equals(CycleEnum.DAY.getValue())){
            begin = DateUtils.getBeginDayOfYesterday();
            end = DateUtils.getEndDayOfYesterDay();
        }
        else if(reportInfo.getCycle().equals(CycleEnum.WEEK.getValue())) {
             begin = DateUtils.getBeginDayOfLastWeek();
             end = DateUtils.getEndDayOfLastWeek();
        }
        else if(reportInfo.getCycle().equals(CycleEnum.MONTH.getValue())) {
            begin = DateUtils.getBeginDayOfLastMonth();
            end = DateUtils.getEndDayOfLastMonth();
        }
        else {
             begin = DateUtils.getFirstSeasonDate();
             end = DateUtils.getLastSeasonDate();
        }
        result.setStartTime(sdf.format(begin));
        result.setEndTime(sdf.format(end));
        return result;
    }
    public String genPDFReport(ReportResult result,ReportInfo reportInfo,String name){
        ClassPathResource resource = new ClassPathResource("jaspers" + File.separator +name);
        InputStream jasperStream = null;
        JasperReport jasperReport=null;
        try {
            jasperStream = resource.getInputStream();
            jasperReport= (JasperReport) JRLoader.loadObject(jasperStream);
        } catch (IOException | JRException ex) {
            log.error("*********读取报表模板失败"+ex.getMessage());
        }
        String fileName=reportInfo.getTypeName()+reportInfo.getCycleDesc()+format.format(new Date())+".pdf";
        Map<String, Object> reportMap = new HashMap<>();
        reportMap.put("title", result.getTitle());
        reportMap.put("createtime", result.getCreateTime());
        reportMap.put("orgName", result.getOrgName());
        reportMap.put("creator", result.getCreator());
        reportMap.put("starttime", result.getStartTime());
        reportMap.put("endtime", result.getEndTime());
        reportMap.put("type",result.getCycleDesc());
        reportMap.put("title1",result.getChart1Title());
        reportMap.put("title2",result.getChart2Title());
        reportMap.put("title3",result.getChart3Title());
        reportMap.put("title4",result.getChart4Title());
        reportMap.put("title5",result.getChart5Title());
        reportMap.put("summary",result.getSummary());
        reportMap.put("chart1",result.getChart1());
        reportMap.put("chart2",result.getChart2());
        reportMap.put("chart3",result.getChart3());
        reportMap.put("chart4",result.getChart4());
        reportMap.put("chart5",result.getChart5());
        try {
            JasperUtil.export("pdf", reportInfo.getPath()+fileName, jasperReport,reportMap);
        } catch (IOException ex) {
            log.error("*********生成报表失败"+ex.getMessage());
        }
        return reportInfo.getUrl()+fileName;
    }
}
