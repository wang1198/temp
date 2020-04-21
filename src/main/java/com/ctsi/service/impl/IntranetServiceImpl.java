package com.ctsi.service.impl;

import com.ctsi.common.ResultBack;
import com.ctsi.dao.DicMapper;
import com.ctsi.dao.IntranetMapper;
import com.ctsi.domain.Intranet;
import com.ctsi.service.IntranetService;
import com.ctsi.utils.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class IntranetServiceImpl implements IntranetService {
    @Resource
    private IntranetMapper intranetMapper;

    @Resource
    private DicMapper dicMapper;

    @Override
    public ResultBack searchIntranetByCondition(Intranet intranet, int page, int limit) {
        PageInfo<Intranet> pageInfo = null;
        List<Intranet> intranetList = null;
        try {
            PageHelper.startPage(page, limit, true);
            intranetList = intranetMapper.searchIntranetByCondition(intranet);
            for(Intranet in:intranetList){
                String paraName = dicMapper.getDicNameByType("FJYY",in.getProhibitReason().toString());
                in.setProhibitReasonDesc(paraName);
            }
            pageInfo = new PageInfo<>(intranetList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultBack.build(-1,"内网IP信息查找失败！");
        }

        return ResultBack.ok(pageInfo);
    }

    @Override
    public ResultBack addIntranet(Intranet intranet) {
        try {
            int num = intranetMapper.queryNumByIP(intranet.getIp());
            if(num>0){
                return ResultBack.build(201,"内网IP信息已存在！");
            }
            if (null==intranet.getId()||intranet.getId().isEmpty()){
                String id = UUID.randomUUID().toString();
                intranet.setId(id);
            }
            intranetMapper.addIntranet(intranet);
            return ResultBack.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultBack.build(-1,"内网IP信息新增失败！");
        }
    }

    @Override
    public ResultBack updateIntranet(Intranet intranet) {
        try {
            intranetMapper.updateIntranet(intranet);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBack.build(-1,"内网IP信息修改失败！");
        }
        return ResultBack.ok();
    }


    @Override
    public ResultBack deleteIntranet(String id) {
        try {
            intranetMapper.deleteIntranet(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBack.build(-1,"内网IP信息删除失败！");
        }
        return ResultBack.ok();
    }

    @Override
    public ResultBack searchIntranetById(String id) {
        Intranet intranet = null;
        try {
            intranet = intranetMapper.searchIntranetById(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultBack.build(-1,"内网IP信息查找失败！");
        }

        return ResultBack.ok(intranet);
    }

    @Override
    public ResultBack exportExtranet(HttpServletResponse response) {
        List<Intranet> intranetList = intranetMapper.searchAllIntranet();
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        String date = format.format(new Date());
        String fileName = date + "intranetIP.xlsx";
        ExcelUtil.exportExcel(intranetList,"内网IP封堵表","内网IP", Intranet.class,fileName,response);
        return ResultBack.ok();
    }
}


