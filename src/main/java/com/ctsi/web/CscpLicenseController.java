package com.ctsi.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctsi.common.MyException;
import com.ctsi.common.ResultBack;
import com.ctsi.domain.CscpLicense;
import com.ctsi.service.CscpLicenseService;
import com.ctsi.utils.MachineCodeUtil;
import com.ctsi.utils.RSAUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName licenseApi
 * Description //TODO
 * Author tongliwei
 * Date 2020/1/31 22:57
 * Version v1.0
 **/
@RestController
@CrossOrigin
@Api(value = "license接口", tags = {"license接口"})
@RequestMapping("/api")
@Validated
public class CscpLicenseController {
    @Autowired
    private CscpLicenseService licenseService;
    @RequestMapping(value="/license",method= RequestMethod.POST)
    public ResultBack license(@RequestBody String license) throws MyException {
        JSONObject obj=JSONObject.parseObject(license);
        CscpLicense cscpLicense= licenseService.license(obj.getString("license"));
        return ResultBack.ok(cscpLicense);
    }
    @RequestMapping(value="/getMachineCode",method= RequestMethod.GET)
    public ResultBack getMachineCode(){
        return ResultBack.ok(MachineCodeUtil.getMachineCode());
    }
    @RequestMapping(value="/getLicense",method= RequestMethod.GET)
    public ResultBack getLicense(){
        if(licenseService.getLicense()==null)
            return ResultBack.build(-1,"请输入授权码");
        else
            return ResultBack.ok(licenseService.getLicense());
    }


    @RequestMapping(value="/register",method= RequestMethod.POST)
    public ResultBack register(@RequestBody String body) {
        JSONObject object=JSONObject.parseObject(body);
        JSONObject obj= (JSONObject) object.get("license");
        CscpLicense info=new CscpLicense();
        info.setMachineCode(obj.getString("MachineCode"));
        info.setExpireTime(obj.getDate("ExpireTime"));
        JSONArray funcs = obj.getJSONArray("Funcs");
        List<String> list=new ArrayList<>();
        for(int i=0;i<funcs.size();i++) {
            String func=funcs.getString(i);
            list.add(func);
        }
        info.setFuncs(list);
        String pri=object.getString("pri");
        return ResultBack.ok(licenseService.register(info,pri));
    }
    @RequestMapping(value="/getKeys",method= RequestMethod.GET)
    public ResultBack getKeys() {
        try {
            Map<String,Object> map= RSAUtil.genKeyPair();
            String result=RSAUtil.getPublicKey(map)+" -&- "+RSAUtil.getPrivateKey(map);
            return ResultBack.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBack.build(-1,"error");
        }
    }
}
