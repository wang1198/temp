package com.ctsi.dao;

import com.ctsi.ssdc.admin.domain.ReportChartMap;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * ClassName ThreatReportDao
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/11 21:09
 * Version v1.0
 **/
@Mapper
public interface ThreatReportDao {
    @Select("SELECT sip,sum(num) total from t_threat_assets where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            "and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d') GROUP BY sip order by total desc")
    @Results({
            @Result(column="sip", property="itemName", jdbcType= JdbcType.VARCHAR),
            @Result(column="total", property="itemValue", jdbcType=JdbcType.INTEGER)
    })
    List<ReportChartMap> getSipBarChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("select time,num from t_threat_change where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            "and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d') order by time")
    @Results({
            @Result(column="time", property="itemName", jdbcType= JdbcType.VARCHAR),
            @Result(column="num", property="itemValue", jdbcType=JdbcType.INTEGER)
    })
    List<ReportChartMap> getLineChartByHour(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("select saveTime,sum(num) total from t_threat_change where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            "and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d') group by saveTime order by saveTime")
    @Results({
            @Result(column="saveTime", property="itemName", jdbcType= JdbcType.VARCHAR),
            @Result(column="total", property="itemValue", jdbcType=JdbcType.INTEGER)
    })
    List<ReportChartMap> getLineChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("SELECT tip,sum(num) total from t_threat_address where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            " and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d') GROUP BY tip order by total desc")
    @Results({
            @Result(column = "tip", property = "itemName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "total", property = "itemValue", jdbcType = JdbcType.INTEGER)
    })
    List<ReportChartMap> getTipBarChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
}
