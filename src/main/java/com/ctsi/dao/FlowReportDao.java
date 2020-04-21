package com.ctsi.dao;

import com.ctsi.ssdc.admin.domain.ReportChartMap;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * ClassName FlowReportDao
 * Description TODO
 * Author tongliwei
 * Date 2020/4/11 21:09
 * Version v1.0
 **/
@Mapper
public interface FlowReportDao {
    @Select("SELECT ip,sum(num) total from t_network_sip where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            "and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d') GROUP BY ip order by total desc")
    @Results({
            @Result(column="ip", property="itemName", jdbcType= JdbcType.VARCHAR),
            @Result(column="total", property="itemValue", jdbcType=JdbcType.INTEGER)
    })
    List<ReportChartMap> getSipBarChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("select `type`,sum(num) total from t_network_application where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            "and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d') group by `type`")
    @Results({
            @Result(column="type", property="itemName", jdbcType= JdbcType.VARCHAR),
            @Result(column="total", property="itemValue", jdbcType=JdbcType.INTEGER)
    })
    List<ReportChartMap> getTypeBarChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("select saveTime,sum(num) from t_network_change where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            "and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d') group by saveTime order by saveTime desc")
    @Results({
            @Result(column="saveTime", property="itemName", jdbcType= JdbcType.VARCHAR),
            @Result(column="num", property="itemValue", jdbcType=JdbcType.INTEGER)
    })
    List<ReportChartMap> getLineChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("select time,num from t_network_change where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            "and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d') order by time")
    @Results({
            @Result(column="time", property="itemName", jdbcType= JdbcType.VARCHAR),
            @Result(column="num", property="itemValue", jdbcType=JdbcType.INTEGER)
    })
    List<ReportChartMap> getLineChartByHour(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("SELECT ip,sum(num) total from t_network_tip where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            " and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d') GROUP BY ip order by total desc")
    @Results({
            @Result(column = "ip", property = "itemName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "total", property = "itemValue", jdbcType = JdbcType.INTEGER)
    })
    List<ReportChartMap> getTipBarChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("SELECT transport,sum(num) total from t_network_transport where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            " and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d') GROUP BY transport order by total desc")
    @Results({
            @Result(column = "transport", property = "itemName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "total", property = "itemValue", jdbcType = JdbcType.INTEGER)
    })
    List<ReportChartMap> getProtocalPieChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
}
