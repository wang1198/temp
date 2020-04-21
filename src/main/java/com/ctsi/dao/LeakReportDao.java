package com.ctsi.dao;

import com.ctsi.domain.StaticsItem;
import com.ctsi.ssdc.admin.domain.ReportChartMap;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * ClassName LeakReportDao
 * Description TODO
 * Author tongliwei
 * Date 2020/4/11 21:08
 * Version v1.0
 **/
@Mapper
public interface LeakReportDao {
    @Select("select sum(low) low,sum(middle) middle,sum(high) high from t_leak_statics where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d') and" +
            " DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d')")
    StaticsItem getLeakLevelPieChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("SELECT port,sum(num) total from t_leak_high where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            " and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d') GROUP BY port order by total desc")
    @Results({
            @Result(column="port", property="itemName", jdbcType= JdbcType.VARCHAR),
            @Result(column="total", property="itemValue", jdbcType=JdbcType.INTEGER)
    })
    List<ReportChartMap> getBarChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("select sum(system) system,sum(application) application,sum(other) other from t_leak_statics where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d') and" +
            " DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d')")
    StaticsItem getLeakTypePieChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("select sum(confirmed) confirmed,sum(assigned) assigned,sum(processing) processing,sum(retested) retested,sum(reinforced) reinforced,sum(misreport) misreport,sum(noreinforced) noreinforced,sum(breakthrough) breakthrough,sum(reproduced) reproduced from t_leak_statics where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            " and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d')")
    StaticsItem getLeakStatusPieChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
}
