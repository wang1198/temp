package com.ctsi.model;

import com.ctsi.domain.CscpFunc;
import com.ctsi.domain.CscpPackage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName CscpPackMapperImpl
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/25 13:40
 * Version v1.0
 **/
@Service
public class CscpPackMapperImpl implements CscpPackMapper {
    @Override
    public CscpPackage toEntity(CscpPackDTO dto) {
        if (dto == null) {
            return null;
        }
        CscpPackage cscpPackage=new CscpPackage();
        cscpPackage.setId(dto.getId());
        cscpPackage.setPackName(dto.getPackName());
        cscpPackage.setDescription(dto.getDescription());
        cscpPackage.setOrgId(dto.getOrgId());
        cscpPackage.setOrgName(dto.getOrgName());
        cscpPackage.setCreateTime(dto.getCreateTime());
        if(dto.getFuncIDs()!=null&&dto.getFuncIDs().size()>0){
            List<CscpFunc> funcs=new ArrayList<>();
            for (String id:dto.getFuncIDs()) {
                CscpFunc func=new CscpFunc();
                func.setId(id);
                funcs.add(func);
            }
            cscpPackage.setFuncs(funcs);
        }
        return cscpPackage;
    }

    @Override
    public CscpPackDTO toDto(CscpPackage entity) {
        if (entity == null) {
            return null;
        }
        CscpPackDTO dto=new CscpPackDTO();
        dto.setId(entity.getId());
        dto.setPackName(entity.getPackName());
        dto.setDescription(entity.getDescription());
        dto.setOrgId(entity.getOrgId());
        dto.setOrgName(entity.getOrgName());
        dto.setCreateTime(entity.getCreateTime());
        if(entity.getFuncs()!=null&&entity.getFuncs().size()>0){
            List<String> funcIDs=new ArrayList<>();
            StringBuilder builder=new StringBuilder();
            for (CscpFunc func:entity.getFuncs()) {
                funcIDs.add(func.getId());
                builder.append(func.getFuncName());
                builder.append(",");
            }
            dto.setFuncNames(builder.toString().substring(0,builder.toString().length()-1));
            dto.setFuncIDs(funcIDs);
        }
        return dto;
    }

    @Override
    public List<CscpPackage> toEntity(List<CscpPackDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<CscpPackDTO> toDto(List<CscpPackage> entityList) {
        if (entityList == null) {
            return null;
        }

        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
