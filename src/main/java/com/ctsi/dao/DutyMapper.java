package com.ctsi.dao;

import com.ctsi.domain.Duty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DutyMapper {

    List<Duty> searchDuty();

    void addDuty(List<Duty> DutyList);

    void deleteAllDuty();
}
