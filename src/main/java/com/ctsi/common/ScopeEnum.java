package com.ctsi.common;

/**
 * ClassName CycleEnum
 * Description TODO
 * Author tongliwei
 * Date 2019/10/8 16:11
 * Version v1.0
 **/
public enum ScopeEnum {
    DAY(1, "每天"),
    WEEK(2, "每周"),
    MONTH(3, "每月");
    private Integer value;
    private String desc;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ScopeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
