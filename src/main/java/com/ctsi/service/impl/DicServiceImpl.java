package com.ctsi.service.impl;

import com.ctsi.common.ResultBack;
import com.ctsi.dao.DicMapper;
import com.ctsi.domain.Model;
import com.ctsi.service.DicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DicServiceImpl implements DicService {

    @Resource
    private DicMapper dicMapper;

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
}
