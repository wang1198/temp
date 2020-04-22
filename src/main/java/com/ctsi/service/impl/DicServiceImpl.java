package com.ctsi.service.impl;

import com.ctsi.dao.DicMapper;
import com.ctsi.domain.Model;
import com.ctsi.service.DicService;
import com.ctsi.utils.ProjectsSystem;
import com.ctsi.utils.ResultBack;
import com.ctsi.utils.TProjectsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DicServiceImpl implements DicService {

    @Resource
    private DicMapper dicMapper;

    @Autowired
    private TProjectsInterface tProjectsInterface;

    @Override
    public ResultBack getDicByType(String paraType) {
        List<Map<String, String>> list = dicMapper.getDicByType(paraType);
        return ResultBack.ok(list);
    }

    @Override
    public ResultBack queryLocation() {
        List<Model> modelList = dicMapper.queryLocation("0");
        for(Model model:modelList){
            List<Model> childrenList = dicMapper.queryLocation(model.getValue());
            model.setChildren(childrenList);
        }
        return ResultBack.ok(modelList);
    }

    @Override
    public ResultBack queryCompany() {
        List<Model> modelList = dicMapper.queryCompany();
        return ResultBack.ok(modelList);
    }

    @Override
    public ResultBack queryAttackSystem(String projectId, HttpServletRequest request) {
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        String businessId = dicMapper.querySystemIdByProjectId(projectId);
        List<ProjectsSystem> systemList = tProjectsInterface.getSystemDetailList(businessId, null, request, null);
        for(ProjectsSystem projectsSystem:systemList){
            map.put("value",projectsSystem.getSystemId());
            map.put("label",projectsSystem.getSystemName());
            list.add(map);
        }
        return ResultBack.ok(list);
    }
}
