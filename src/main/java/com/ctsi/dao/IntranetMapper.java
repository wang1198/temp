package com.ctsi.dao;

import com.ctsi.domain.Intranet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IntranetMapper {
    List<Intranet> searchAllIntranet();

    List<Intranet> searchIntranetByCondition(Intranet intranet);

    void addIntranet(Intranet intranet);

    void updateIntranet(Intranet intranet);

    void deleteIntranet(@Param("id") String id);

    int queryNumByIP(@Param("ip") String ip);

    Intranet searchIntranetById(@Param("id") String id);
}
