package com.ctsi.web;

import com.alibaba.fastjson.JSONObject;
import com.ctsi.common.ResultBack;
import com.ctsi.model.ResetForm;
import com.ctsi.service.CscpForgetPassService;
import com.ctsi.ssdc.captcha.BiyiCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName CscpForgetPassController
 * Description //TODO
 * Author tongliwei
 * Date 2020/3/30 22:07
 * Version v1.0
 **/
@RestController
@CrossOrigin
@Api(value = "重置接口", tags = {"重置接口"})
@RequestMapping("/")
public class CscpForgetPassController {
    @Autowired
    private CscpForgetPassService forgetPassService;

    @ApiImplicitParams({
            @ApiImplicitParam(name="biyiCaptchaKey", value="验证码key", paramType="header", dataType="String", defaultValue=""),
            @ApiImplicitParam(name="biyiCaptcha", value="验证码", paramType="header", dataType="String", defaultValue="{\"code\":\"1234\"}")
    })
    @BiyiCaptcha(rule = "defaultRule", service = "digitalCaptchaService")
    @PostMapping("/checkUser")
    public ResultBack checkUser(@RequestBody String userName) {
        JSONObject obj=JSONObject.parseObject(userName);
        if(forgetPassService.checkUser(obj.getString("username")))
            return ResultBack.build(200,"重置密码邮件已发送，请注意查收");
        else
            return ResultBack.build(-1, "用户不存在");
    }
    @PostMapping("/resetPwd")
    public ResultBack resetPwd(@RequestBody ResetForm form) throws Exception {
        return forgetPassService.resetPwd(form);
    }
}
