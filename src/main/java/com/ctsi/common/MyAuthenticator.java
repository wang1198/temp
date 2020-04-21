package com.ctsi.common;

/**
 * ClassName MyAuthenticator
 * Description //TODO
 * Author tongliwei
 * Date 2019/5/14 14:15
 * Version v1.0
 **/

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {
    String userName = null;
    String password = null;

    public MyAuthenticator() {
    }

    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}
