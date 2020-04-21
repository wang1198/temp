package com.ctsi.common;

/**
 * ClassName CycleEnum
 * Description TODO
 * Author tongliwei
 * Date 2019/10/8 16:11
 * Version v1.0
 **/
public enum CycleEnum {
    DAY("1", "日报"),
    WEEK("2", "周报"),
    MONTH("3", "月报"),
    SEASON("4", "季报");
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

    CycleEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
