package com.ctsi.service;

import com.ctsi.common.ResultBack;
import com.ctsi.model.ResetForm;

/**
 * ClassName CscpForgetPassService
 * Description //TODO
 * Author tongliwei
 * Date 2020/3/30 22:08
 * Version v1.0
 **/
public interface CscpForgetPassService {
   boolean checkUser(String userName);
   ResultBack resetPwd(ResetForm form) throws Exception;
}
