package com.ctsi.domain;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * ClassName PwdResetRecord
 * Description //TODO
 * Author tongliwei
 * Date 2020/3/31 13:19
 * Version v1.0
 **/
public class PwdResetRecord {
    private Integer id;
    private String userId;
    private String userName;
    private String email;
    private String token;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String expireTime;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
