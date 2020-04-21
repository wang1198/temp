package com.ctsi.model;

import com.ctsi.domain.CscpBusiness;
import com.ctsi.ssdc.admin.domain.CscpDic;
import com.ctsi.ssdc.admin.repository.CscpDicDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ClassName CscpBusinessMapperImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/26 14:27
 * Version v1.0
 **/
@Service
public class CscpBusinessMapperImpl implements CscpBusinessMapper {
    @Autowired
    private CscpDicDao dicDao;
    @Override
    public CscpBusiness toEntity(CscpBusinessDTO dto) {
        if (dto == null) {
            return null;
        }
        CscpBusiness cscpBusiness=new CscpBusiness();
        cscpBusiness.setId(dto.getId());
        cscpBusiness.setBusinessName(dto.getBusinessName());
        cscpBusiness.setDescription(dto.getDescription());
        cscpBusiness.setOrgId(dto.getOrgId());
        cscpBusiness.setCreateTime(dto.getCreateTime());
        cscpBusiness.setOrgName(dto.getOrgName());
        cscpBusiness.setBusinessNo(dto.getBusinessNo());
        cscpBusiness.setManager(dto.getManager());
        cscpBusiness.setYear(dto.getYear());
        cscpBusiness.setShow(dto.getShow());
        cscpBusiness.setUrl(dto.getUrl());
        if(dto.getBusinessType()!=null)
            cscpBusiness.setBusinessType(dto.getBusinessType());
        return cscpBusiness;
    }

    @Override
    public CscpBusinessDTO toDto(CscpBusiness entity) {
        if (entity == null) {
            return null;
        }
        CscpBusinessDTO cscpBusiness=new CscpBusinessDTO();
        cscpBusiness.setId(entity.getId());
        cscpBusiness.setBusinessName(entity.getBusinessName());
        cscpBusiness.setDescription(entity.getDescription());
        cscpBusiness.setOrgId(entity.getOrgId());
        cscpBusiness.setCreateTime(entity.getCreateTime());
        cscpBusiness.setBusinessType(entity.getBusinessType());
        cscpBusiness.setOrgName(entity.getOrgName());
        cscpBusiness.setBusinessNo(entity.getBusinessNo());
        cscpBusiness.setManager(entity.getManager());
        cscpBusiness.setYear(entity.getYear());
        cscpBusiness.setShow(entity.getShow());
        cscpBusiness.setUrl(entity.getUrl());
        List<CscpDic> dics=dicDao.getDicListByType(1);
        if(CollectionUtils.isNotEmpty(dics)){
            Optional<CscpDic> dic=dics.stream().filter(o-> o.getDicValue().equals(entity.getBusinessType())).findFirst();
            dic.ifPresent(cscpDic -> cscpBusiness.setBusinessTypeName(cscpDic.getDicDisplay()));
        }
        return cscpBusiness;
    }

    @Override
    public List<CscpBusiness> toEntity(List<CscpBusinessDTO> dtoList) {
        if (dtoList == null) {
            return Collections.emptyList();
        }

        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<CscpBusinessDTO> toDto(List<CscpBusiness> entityList) {
        if (entityList == null) {
            return Collections.emptyList();
        }

        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
