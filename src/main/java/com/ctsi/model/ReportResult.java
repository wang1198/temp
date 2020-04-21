package com.ctsi.model;

import com.ctsi.ssdc.admin.domain.ReportChartMap;

import java.util.List;

/**
 * ClassName ReportResult
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/11 21:14
 * Version v1.0
 **/
public class ReportResult {
    private Integer id;
    private String cycle;
    private String orgId;
    private String title;
    private String creator;
    private String createTime;
    private String orgName;
    private String startTime;
    private String endTime;
    private String cycleDesc;
    private String chart1Title;
    private String chart2Title;
    private String chart3Title;
    private String chart4Title;
    private String chart5Title;
    private String summary;
    private List<ReportChartMap> chart1;
    private List<ReportChartMap> chart2;
    private List<ReportChartMap> chart3;
    private List<ReportChartMap> chart4;
    private List<ReportChartMap> chart5;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCycleDesc() {
        return cycleDesc;
    }

    public void setCycleDesc(String cycleDesc) {
        this.cycleDesc = cycleDesc;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getChart1Title() {
        return chart1Title;
    }

    public void setChart1Title(String chart1Title) {
        this.chart1Title = chart1Title;
    }

    public String getChart2Title() {
        return chart2Title;
    }

    public void setChart2Title(String chart2Title) {
        this.chart2Title = chart2Title;
    }

    public String getChart3Title() {
        return chart3Title;
    }

    public void setChart3Title(String chart3Title) {
        this.chart3Title = chart3Title;
    }

    public String getChart4Title() {
        return chart4Title;
    }

    public void setChart4Title(String chart4Title) {
        this.chart4Title = chart4Title;
    }

    public List<ReportChartMap> getChart1() {
        return chart1;
    }

    public void setChart1(List<ReportChartMap> chart1) {
        this.chart1 = chart1;
    }

    public List<ReportChartMap> getChart2() {
        return chart2;
    }

    public void setChart2(List<ReportChartMap> chart2) {
        this.chart2 = chart2;
    }

    public List<ReportChartMap> getChart3() {
        return chart3;
    }

    public void setChart3(List<ReportChartMap> chart3) {
        this.chart3 = chart3;
    }

    public List<ReportChartMap> getChart4() {
        return chart4;
    }

    public void setChart4(List<ReportChartMap> chart4) {
        this.chart4 = chart4;
    }

    public List<ReportChartMap> getChart5() {
        return chart5;
    }

    public void setChart5(List<ReportChartMap> chart5) {
        this.chart5 = chart5;
    }

    public String getChart5Title() {
        return chart5Title;
    }

    public void setChart5Title(String chart5Title) {
        this.chart5Title = chart5Title;
    }
}
