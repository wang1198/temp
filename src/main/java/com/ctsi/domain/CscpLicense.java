package com.ctsi.domain;

import com.ctsi.utils.MachineCodeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ClassName CscpLicense
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/6 11:32
 * Version v1.0
 **/
public class CscpLicense implements Serializable {
    private String machineCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expireTime;
    private boolean isExpired;
    private boolean isValid;
    @JsonIgnore
    private List<String> funcs;

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        Date now=new Date();
        return now.after(this.expireTime);
    }

    public boolean isValid() {
        String mc;
        try {
            mc = MachineCodeUtil.getMachineCode();
        } catch (Exception e) {
            mc=null;
        }
        return this.machineCode != null && this.machineCode.equals(mc);
    }

    public List<String> getFuncs() {
        return funcs;
    }

    public void setFuncs(List<String> funcs) {
        this.funcs = funcs;
    }
}
