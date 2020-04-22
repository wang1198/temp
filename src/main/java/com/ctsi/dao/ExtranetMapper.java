package com.ctsi.dao;

import com.ctsi.domain.Extranet;
import com.ctsi.ssdc.admin.domain.util.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExtranetMapper {
    List<Extranet> searchAllExtranet();

    List<Extranet> searchExtranetByCondition(Extranet extranet);

    void addExtranet(Extranet extranet);

    void updateExtranet(Extranet extranet);

    void deleteExtranet(@Param("id")String id);

    int queryNumByConditon(@Param("fromIP")String fromIP,@Param("fromPort")String fromPort,@Param("dstIP")String dstIP,
                           @Param("dstPort")String dstPort);

}
