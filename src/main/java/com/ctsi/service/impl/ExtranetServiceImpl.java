package com.ctsi.service.impl;

import com.ctsi.common.ResultBack;
import com.ctsi.dao.DicMapper;
import com.ctsi.dao.ExtranetMapper;
import com.ctsi.domain.Extranet;
import com.ctsi.service.ExtranetService;
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
public class ExtranetServiceImpl implements ExtranetService {

    @Resource
    private ExtranetMapper extranetMapper;

    @Resource
    private DicMapper dicMapper;

    @Override
    public ResultBack searchExtranetByCondition(Extranet extranet, int page, int limit) {
        PageInfo<Extranet> pageInfo = null;
        List<Extranet> extranetList = null;
        try {
            PageHelper.startPage(page, limit, true);
            extranetList = extranetMapper.searchExtranetByCondition(extranet);
            for(Extranet ex:extranetList){
                ex.setRiskLevelDesc(dicMapper.getDicNameByType("FXJB",ex.getRiskLevel()));
                ex.setAttackTypeDesc(dicMapper.getDicNameByType("GJLX",ex.getAttackType()));
                ex.setIsHandleDesc(dicMapper.getDicNameByType("SFCZ",ex.getIsHandle()));
                ex.setResportCompanyDesc(extranetMapper.queryCompany(ex.getResportCompany()));
                ex.setIpPort(ex.getFromIP() + ":" + ex.getFromPort());
                ex.setFromIPLocationDesc(dicMapper.queryLocationById(ex.getFromIPLocation()));
                ex.setFromIPCityDesc(dicMapper.queryLocationById(ex.getFromIPCity()));
                ex.setDetailLocation(ex.getFromIPLocationDesc() + ex.getFromIPCityDesc());
            }
            pageInfo = new PageInfo<>(extranetList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultBack.build(-1,"外网IP信息查找失败！");
        }

        return ResultBack.ok(pageInfo);
    }


    @Override
    public ResultBack addExtranet(Extranet extranet) {
        try {
            int num = extranetMapper.queryNumByConditon(extranet.getFromIP(),extranet.getFromPort(),
                    extranet.getDstIP(),extranet.getDstPort());
            if(num>0){
                return ResultBack.build(201,"外网IP信息已存在！");
            }
            if (null==extranet.getId()||extranet.getId().isEmpty()){
                String id = UUID.randomUUID().toString();
                extranet.setId(id);
            }
            extranetMapper.addExtranet(extranet);
            return ResultBack.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultBack.build(-1,"外网IP信息新增失败！");
        }
    }


    @Override
    public ResultBack updateExtranet(Extranet extranet) {
        try {
            extranetMapper.updateExtranet(extranet);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBack.build(-1,"外网IP信息修改失败！");
        }
        return ResultBack.ok();
    }


    @Override
    public ResultBack deleteExtranet(String id) {
        try {
            extranetMapper.deleteExtranet(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBack.build(-1,"外网IP信息删除失败！");
        }
        return ResultBack.ok();
    }

    @Override
    public ResultBack exportExtranet(HttpServletResponse response) {
        List<Extranet> extranetList = extranetMapper.searchAllExtranet();
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        String date = format.format(new Date());
        String fileName = date + "extranetIP.xlsx";
        ExcelUtil.exportExcel(extranetList,"外网IP封堵表","外网IP",Extranet.class,fileName,response);
        return ResultBack.ok();
    }

    @Override
    public ResultBack queryCompany(String companyId) {
        String companyDesc = extranetMapper.queryCompany(companyId);
        return ResultBack.ok(companyDesc);
    }
}
