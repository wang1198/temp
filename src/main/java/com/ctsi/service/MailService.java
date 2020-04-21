package com.ctsi.service;

import com.ctsi.domain.MailSenderInfo;

/**
 * ClassName MailService
 * Description //TODO
 * Author tongliwei
 * Date 2019/5/14 14:10
 * Version v1.0
 **/
public interface MailService {
    boolean sendTextMail(MailSenderInfo info);
    boolean sendHtmlMail(MailSenderInfo info);
}
