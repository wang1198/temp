package com.ctsi.common;

/**
 * ClassName ReportTypeEnum
 * Description TODO
 * Author tongliwei
 * Date 2019/10/8 16:11
 * Version v1.0
 **/
public enum ReportTypeEnum {
    ACTION("1", "行为审计报表"),
    LOG("2", "日志报表"),
    LEAK("3", "漏洞报表"),
    THREAT("4", "威胁情报报表"),
    FLOW("5", "流量报表"),
    ALARM("6", "告警报表");
    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ReportTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
