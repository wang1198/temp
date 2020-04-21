package com.ctsi.common;

import com.ctsi.dao.CscpConfDao;
import com.ctsi.dao.ResetPwdRecordDao;
import com.ctsi.domain.ConfigInfo;
import com.ctsi.domain.MailSenderInfo;
import com.ctsi.domain.PwdResetRecord;
import com.ctsi.service.MailService;
import com.ctsi.ssdc.admin.domain.dto.CscpUserDTO;
import com.ctsi.ssdc.admin.domain.dto.CscpUserDetailDTO;
import com.ctsi.ssdc.admin.service.CscpUserDetailService;
import com.ctsi.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ClassName MailThread
 * Description //TODO
 * Author tongliwei
 * Date 2019/10/18 16:56
 * Version v1.0
 **/
public class MailThread implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(MailThread.class);
    private MailService mailService;
    private ResetPwdRecordDao resetPwdRecordDao;
    private CscpUserDetailService userDetailService;
    private CscpConfDao confDao;
    private String resetPwdAddr;
    private CscpUserDTO dto;
    public MailThread(MailService mailService, ResetPwdRecordDao resetPwdRecordDao, CscpConfDao confDao,CscpUserDetailService userDetailsService,String resetPwdAddr,CscpUserDTO dto){
        this.mailService=mailService;
        this.resetPwdRecordDao=resetPwdRecordDao;
        this.userDetailService=userDetailsService;
        this.confDao=confDao;
        this.resetPwdAddr=resetPwdAddr;
        this.dto=dto;
    }
    @Override
    public void run() {
        try{
            CscpUserDetailDTO detail=userDetailService.findByUserId(dto.getId());
            String email=detail.getEmail();
            String token= TokenUtil.getInstance().makeToken();
            PwdResetRecord record=new PwdResetRecord();
            record.setUserName(dto.getUsername());
            record.setEmail(email);
            record.setToken(token);
            record.setStatus(0);
            record.setUserId(detail.getUserId());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            record.setCreateTime(df.format(new Date()));
            long expireTime = System.currentTimeMillis()+20*60*1000;
            Date date=new Date(expireTime);
            record.setExpireTime(df.format(date));
            List<ConfigInfo> configs=confDao.getConfigs();
            String server=configs.stream().filter(o->o.getConfigKey().equals("server")).findFirst().get().getConfigValue();
            String port=configs.stream().filter(o->o.getConfigKey().equals("port")).findFirst().get().getConfigValue();
            String user=configs.stream().filter(o->o.getConfigKey().equals("user")).findFirst().get().getConfigValue();
            String password=configs.stream().filter(o->o.getConfigKey().equals("password")).findFirst().get().getConfigValue();
            String master=configs.stream().filter(o->o.getConfigKey().equals("master")).findFirst().get().getConfigValue();
            MailSenderInfo info=new MailSenderInfo();
            info.setMailServerHost(server);
            info.setMailServerPort(port);
            info.setUserName(user);
            info.setPassword(password);
            info.setFromAddress(master);
            info.setToAddress(record.getEmail());
            info.setSubject("找回密码");
            String url=resetPwdAddr+"?token="+record.getToken();
            String content=String.format("<html><body><div>尊敬的%s:</div><div>您正通过邮箱重置安全服务平台的登录密码，请点击下面链接重置密码，如果" +
                    "链接不能跳转，请将链接复制到浏览器中打开。</div><a href='%s'>%s</a><div>链接20分钟后失效</div><div>安全服务平台</div></body></html>",record.getUserName(),url,url);
            info.setContent(content);
            mailService.sendHtmlMail(info);
            resetPwdRecordDao.insert(record);
            log.info("邮件发送成功");
        }catch (Exception e){
            log.error("邮件发送失败:"+e.getMessage());
        }
    }
}
