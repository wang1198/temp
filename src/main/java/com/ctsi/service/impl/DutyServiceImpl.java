package com.ctsi.service.impl;

import com.ctsi.common.ResultBack;
import com.ctsi.dao.DutyMapper;
import com.ctsi.domain.Duty;
import com.ctsi.service.DutyService;
import com.ctsi.utils.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class DutyServiceImpl implements DutyService {

    @Resource
    private DutyMapper dutyMapper;
    @Override
    public ResultBack importDuty(MultipartFile file) {
        List<Duty> dutyList = ExcelUtil.readExcel(Duty.class,file);
        //将数据保存到数据库
        dutyMapper.addDuty(dutyList);
        return ResultBack.ok(dutyList);

    }

    @Override
    public ResultBack exportDuty(HttpServletResponse response) {
        List<Duty> dutyList = dutyMapper.searchDuty();
        String path = "duty.xlsx";
        ExcelUtil.exportExcel(dutyList,"人员值班表","值班表", Duty.class,path,response);
        return ResultBack.ok();
    }

    @Override
    public ResultBack searchDuty(int page,int limit) {
        PageHelper.startPage(page, limit, true);
        List<Duty> dutyList = dutyMapper.searchDuty();
        PageInfo<Duty> pageInfo = new PageInfo<>(dutyList);
        return ResultBack.ok(pageInfo);

    }
}
