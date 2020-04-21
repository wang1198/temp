package com.ctsi.model;

/**
 * ClassName ResetForm
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/2 14:06
 * Version v1.0
 **/
public class ResetForm {
    private String token;
    private String newPwd;
    private String confirmPwd;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }
}
