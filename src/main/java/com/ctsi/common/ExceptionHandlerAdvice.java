package com.ctsi.common;

import com.ctsi.ssdc.captcha.exception.CaptchaMissingException;
import com.ctsi.ssdc.captcha.exception.CaptchaValidateException;
import com.ctsi.ssdc.controller.UserJwtController;
import com.ctsi.ssdc.exception.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName ExceptionHandlerAdvice
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/11 14:49
 * Version v1.0
 **/
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBack validationErrorHandler(MethodArgumentNotValidException ex) {
        //同样是获取BindingResult对象，然后获取其中的错误信息
        //如果前面开启了fail_fast，事实上这里只会有一个信息
        //如果没有，则可能又多个
        List<String> errorInformation = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        log.error(errorInformation.toString());
        return ResultBack.build(-1, "校验失败，异常信息：" + errorInformation.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResultBack validationErrorHandler(ConstraintViolationException ex) {
        List<String> errorInformation = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        log.error(errorInformation.toString());
        return ResultBack.build(-1, "校验失败，异常信息：" + errorInformation.toString());
    }
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity handleInsufficientAuthentication(InsufficientAuthenticationException e) {
        log.error(e.getMessage());
        return new ResponseEntity (HttpStatus.UNAUTHORIZED);//ResultBack.build(401, "长时间未操作，已登出",e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResultBack handleException(Exception e) {
        log.error(e.getMessage());
        return ResultBack.build(-1, "服务器异常",e.getMessage());
    }
    @ExceptionHandler(IOException.class)
    public ResultBack handleIOException(IOException e) {
        log.error(e.getMessage());
        return ResultBack.build(-1, "IO异常");
    }

    @ExceptionHandler(SQLException.class)
    public ResultBack handleSQLException(SQLException e) {
        log.error(e.getMessage());
        return ResultBack.build(-1, "数据库异常");
    }
    @ExceptionHandler(MyException.class)
    public ResultBack handleSQLException(MyException e) {
        log.error(e.getMessage());
        return ResultBack.build(-1, e.getMessage());
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public ResultBack duplicateKeyErrorHandler(DuplicateKeyException ex) {
        log.error(ex.getMessage());
        String msg="";
        if(ex.getMessage().contains("pack"))
            msg= "套餐名称已存在";
        if(ex.getMessage().contains("business"))
            msg= "业务名称已存在";
        if(ex.getMessage().contains("code"))
            msg= "字典值已存在";
        if(ex.getMessage().contains("display"))
            msg= "字典名称已存在";
        return ResultBack.build(-1,msg);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResultBack handleBadCredentialsException(BadCredentialsException e) {
        log.error(e.getMessage());
        return ResultBack.build(-1, "用户名或密码错误");
    }
    @ExceptionHandler(BadRequestAlertException.class)
    public ResultBack handleBadRequestAlertException(BadRequestAlertException e) {
        log.error(e.getMessage());
        return ResultBack.build(-1, e.getMessage());
    }

    @ExceptionHandler(CaptchaMissingException.class)
    public ResultBack handleCaptchaException(CaptchaMissingException e) {
        log.error(e.getMessage());
        return ResultBack.build(-1, "请输入验证码");
    }
    @ExceptionHandler(CaptchaValidateException.class)
    public ResultBack handleCaptchaValidateException(CaptchaValidateException e) {
        log.error(e.getMessage());
        return ResultBack.build(-1, "验证码错误");
    }
}
