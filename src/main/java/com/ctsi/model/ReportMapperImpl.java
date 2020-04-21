package com.ctsi.model;

import com.ctsi.domain.ReportInfo;
import com.ctsi.ssdc.admin.domain.CscpDic;
import com.ctsi.ssdc.admin.repository.CscpDicDao;
import com.ctsi.ssdc.admin.service.CustomerService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * ClassName ReportMapperImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/9 16:11
 * Version v1.0
 **/
@Service
public class ReportMapperImpl implements ReportMapper {
    @Autowired
    private CscpDicDao dicDao;
    @Autowired
    private CustomerService customerService;
    @Override
    public ReportInfo toEntity(ReportInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        ReportInfo report=new ReportInfo();
        report.setId(dto.getId());
        report.setCreator(dto.getCreator());
        report.setCycle(dto.getCycle());
        report.setOrgId(dto.getOrgId());
        report.setTitle(dto.getTitle());
        String orgName = customerService.sellectOrgList(new String[]{dto.getOrgId()}).get(0).getOrgName();
        report.setOrgName(orgName);
        report.setTypeId(dto.getTypeId());
        List<CscpDic> type=dicDao.getDicListByType(8);
        if(CollectionUtils.isNotEmpty(type)){
            Optional<CscpDic> dic=type.stream().filter(o-> o.getDicValue().equals(Integer.valueOf(dto.getTypeId()))).findFirst();
            dic.ifPresent(cscpDic -> report.setTypeName(cscpDic.getDicDisplay()));
        }
        List<CscpDic> cycle=dicDao.getDicListByType(6);
        if(CollectionUtils.isNotEmpty(cycle)){
            Optional<CscpDic> dic=cycle.stream().filter(o-> o.getDicValue().equals(Integer.valueOf(dto.getCycle()))).findFirst();
            dic.ifPresent(cscpDic -> report.setCycleDesc(cscpDic.getDicDisplay()));
        }
        report.setScope(dto.getScope());
        List<CscpDic> scope=dicDao.getDicListByType(7);
        if(CollectionUtils.isNotEmpty(scope)){
            Optional<CscpDic> dic=scope.stream().filter(o-> o.getDicValue().equals(dto.getScope())).findFirst();
            dic.ifPresent(cscpDic -> report.setScopeDesc(cscpDic.getDicDisplay()));
        }
        return report;
    }

    @Override
    public ReportInfoDTO toDto(ReportInfo entity) {
        if (entity == null) {
            return null;
        }
        ReportInfoDTO report=new ReportInfoDTO();
        report.setId(entity.getId());
        report.setCreator(entity.getCreator());
        report.setCycle(entity.getCycle());
        report.setOrgId(entity.getOrgId());
        report.setScope(entity.getScope());
        report.setTitle(entity.getTitle());
        report.setTypeId(entity.getTypeId());
        return report;
    }

    @Override
    public List<ReportInfo> toEntity(List<ReportInfoDTO> dtoList) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<ReportInfoDTO> toDto(List<ReportInfo> entityList) {
        return Collections.EMPTY_LIST;
    }
}
