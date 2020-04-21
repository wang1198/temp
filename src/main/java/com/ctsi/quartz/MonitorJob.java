package com.ctsi.quartz;

import com.alibaba.fastjson.JSONObject;
import com.ctsi.dao.CscpMonitorServiceDao;
import com.ctsi.domain.CscpServiceInfo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName MonitorJob
 * Description //TODO
 * Author tongliwei
 * Date 2020/3/26 16:57
 * Version v1.0
 **/
public class MonitorJob implements Job {
    @Autowired
    private CscpMonitorServiceDao cscpMonitorServiceDao;
    @Autowired
    private RestTemplate restTemplate;
    private List<CscpServiceInfo> infos=new ArrayList<>();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        if(infos.isEmpty()){
            infos=cscpMonitorServiceDao.getServiceList();
        }
        for (CscpServiceInfo info:infos) {
            setServiceStatus(info);
            cscpMonitorServiceDao.update(info);
        }
    }
    private void setServiceStatus(CscpServiceInfo info){
        String url="http://"+info.getIp()+":"+info.getPort()+"/actuator/health";
        JSONObject object=null;
        try {
             object = restTemplate.getForObject(url, JSONObject.class);
        }catch (HttpServerErrorException ex){
            info.setStatus("异常");
        }catch (ResourceAccessException ex){
            info.setStatus("异常");
        }
        if(object==null)
            info.setStatus("异常");
        else {
            String status=object.getString("status");
            if ("UP".equals(status))
                info.setStatus("正常");
            else
                info.setStatus("异常");
        }
    }
}
