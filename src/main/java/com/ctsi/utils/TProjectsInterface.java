package com.ctsi.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctsi.ssdc.admin.service.CscpUserDetailService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * REST controller for managing TProjects.
 *
 * @author ctsi-biyi-generator
 *
 */
@Api(value = "给各个子系统接口", tags = "给各个子系统接口", consumes = "application/json")
@RestController
@RequestMapping("/api/tProjectss")
public class TProjectsInterface {

    private final Logger log = LoggerFactory.getLogger(TProjectsInterface.class);


    @Autowired
    CscpUserDetailService cscpUserDetailService;

    /**
     * 业务接口地址
     */
    private static String businessUrl ;

    /**
     * 服务接口地址
     */
    private static String serviceUrl ;

    /**
     * 套餐接口地址
     */
    private static String packageUrl ;

    /**
     * 业务详情接口地址（业务id精确查询或者业务名称模糊查询）
     */
    private static String businessDetailUrl ;

    static {
        Properties verificationGateUrl = null;
        try {
            verificationGateUrl = com.ctsi.ssdc.admin.web.util.PropertiesUtils.loadProperties("interfaceInfo.properties");
            businessUrl = verificationGateUrl.getProperty("businessUrl");
            serviceUrl = verificationGateUrl.getProperty("serviceUrl");
            packageUrl = verificationGateUrl.getProperty("packageUrl");
            businessDetailUrl = verificationGateUrl.getProperty("businessDetailUrl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }




    /**
     * 业务详情接口地址（业务id精确查询或者业务名称模糊查询）
     * @param businessId
     * @param businessName
     * @param request
     * @param authorization
     * @return
     */
    public List<ProjectsSystem> getSystemDetailList(String businessId, String businessName, HttpServletRequest request, String authorization){

        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("businessId",businessId);
        urlVariables.put("businessName",businessName);
        String response = RestTemplateRequestJWT.getMap(businessDetailUrl, String.class, urlVariables, request, authorization);
        if (response == null || "".equals(response)) {
            return null;
        }
        JSONObject returnJsonObject = JSONObject.parseObject(response);
        //状态码不等于200，则数据有误
        int statusCode = returnJsonObject.getInteger("statusCode");
        if (statusCode != 200) {
            return  null;
        }
        //数据
        JSONArray data = returnJsonObject.getJSONArray("data");
        if (data == null || data.size() == 0) {
            return null;
        }
        ArrayList<ProjectsSystem> systemList= new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            systemList.add(getProjectsSystem(data.getJSONObject(i)));
        }
        return systemList;
    }

    /**
     * 封装业务
     * @param jsonObject
     * @return
     */
    public ProjectsSystem getProjectsSystem(JSONObject jsonObject) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
        ProjectsSystem system = new ProjectsSystem();
        system.setSystemId(jsonObject.getString("businessId"));
        system.setSystemName(jsonObject.getString("businessName"));
        system.setBusinessNo(jsonObject.getString("businessNo"));
        system.setManager(jsonObject.getString("manager"));
        try {
            system.setYear(sdf.parse(jsonObject.getString("year")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return system;
    }



}
