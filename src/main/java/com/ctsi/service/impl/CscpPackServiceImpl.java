package com.ctsi.service.impl;

import com.ctsi.dao.CscpPackDao;
import com.ctsi.datasource.DS;
import com.ctsi.domain.CscpPackage;
import com.ctsi.model.CscpPackDTO;
import com.ctsi.model.CscpPackMapper;
import com.ctsi.service.CscpPackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName CscpPackServiceImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/13 10:42
 * Version v1.0
 **/
@Service
public class CscpPackServiceImpl implements CscpPackService {
    @Autowired
    private CscpPackDao packDao;
    @Autowired
    private CscpPackMapper packMapper;
    @Override
    @DS
    public void insert(CscpPackDTO cscpPackage){
        CscpPackage pack= packMapper.toEntity(cscpPackage);
        packDao.insert(pack);
        List<String> funcs=cscpPackage.getFuncIDs();
        packDao.insert_pack_func(pack.getId(),funcs);
    }

    @Override
    public void update(CscpPackDTO cscpPackage){
        updateTrans(cscpPackage);
    }

    public void updateTrans(CscpPackDTO cscpPackage) {
        CscpPackage pack= packMapper.toEntity(cscpPackage);
        packDao.update(pack);
        packDao.delete_pack_func(pack.getId());
        List<String> funcs=cscpPackage.getFuncIDs();
        packDao.insert_pack_func(pack.getId(),funcs);
    }

    @Override
    public void delete(String id) {
        packDao.delete(id);
        packDao.delete_pack_func(id);
    }

    @Override
    public PageInfo getPackageList(CscpPackDTO dto,int page,int limit) {
        PageHelper.startPage(page, limit, true);
        List<CscpPackage> packageList=packDao.getPackageList(dto);
        PageInfo pageInfo = new PageInfo<>(packageList);
        pageInfo.setList(packMapper.toDto(pageInfo.getList()));
        return pageInfo;
    }
}
