package com.ctsi.service.impl;

import com.ctsi.common.MailThread;
import com.ctsi.common.ResultBack;
import com.ctsi.dao.CscpConfDao;
import com.ctsi.dao.ResetPwdRecordDao;
import com.ctsi.domain.PwdResetRecord;
import com.ctsi.model.ResetForm;
import com.ctsi.service.CscpForgetPassService;
import com.ctsi.service.MailService;
import com.ctsi.ssdc.admin.domain.dto.CscpUserDTO;
import com.ctsi.ssdc.admin.domain.dto.CscpUserPasswordUpdate;
import com.ctsi.ssdc.admin.service.CscpUserDetailService;
import com.ctsi.ssdc.admin.service.CscpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName CscpForgetPassServiceImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/3/30 22:09
 * Version v1.0
 **/
@Service
public class CscpForgetPassServiceImpl implements CscpForgetPassService {
    @Autowired
    CscpUserService cscpUserService;
    @Autowired
    CscpUserDetailService userDetailService;
    @Autowired
    MailService mailService;
    @Autowired
    CscpConfDao confDao;
    @Autowired
    ResetPwdRecordDao resetPwdRecordDao;
    @Value("${ctsi.resetPwdAddr}")
    private String resetPwdAddr;
    @Override
    public boolean checkUser(String userName) {
             CscpUserDTO dto=cscpUserService.findByName(userName);
             if(dto!=null){
             MailThread thread=new MailThread(mailService,resetPwdRecordDao,confDao,userDetailService,resetPwdAddr,dto);
             thread.run();
             return true;
         }else {
            return false;
         }
    }

    @Override
    @Transactional
    public ResultBack resetPwd(ResetForm form) throws Exception {
        if(form.getNewPwd()!=null&&form.getNewPwd().equals(form.getConfirmPwd())){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            PwdResetRecord record=resetPwdRecordDao.getRecord(form.getToken());
            if(record==null){
                return ResultBack.build(-1,"非法操作");
            }
            if(new Date().after(df.parse(record.getExpireTime()))){
                resetPwdRecordDao.modifyRecord(1,record.getUserId());
                return ResultBack.build(-1,"链接已失效");
            }
            if(record.getStatus()==1){
                return ResultBack.build(-1,"链接已失效");
            }
            CscpUserPasswordUpdate cscpUser=new CscpUserPasswordUpdate();
            cscpUser.setUserId(record.getUserId());
            cscpUser.setNewPassword(form.getNewPwd());
            cscpUserService.resetPassword(cscpUser);
            resetPwdRecordDao.modifyRecord(1,record.getUserId());
            return ResultBack.ok();
        }else {
            return ResultBack.build(-1,"两次密码不一致");
        }
    }
}
