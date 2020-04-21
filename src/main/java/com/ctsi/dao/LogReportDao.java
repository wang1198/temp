package com.ctsi.dao;

import com.ctsi.domain.StaticsItem;
import com.ctsi.ssdc.admin.domain.ReportChartMap;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * ClassName LogReportDao
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/11 21:08
 * Version v1.0
 **/
@Mapper
public interface LogReportDao {
    @Select("select sum(light) light,sum(low) low,sum(middle) middle,sum(high) high,sum(serious) serious from t_log_level where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            " and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d')")
    StaticsItem getPieChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("SELECT ip,sum(num) total from t_log_assets where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            " and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d') GROUP BY ip order by total desc")
    @Results({
            @Result(column="ip", property="itemName", jdbcType= JdbcType.VARCHAR),
            @Result(column="total", property="itemValue", jdbcType=JdbcType.INTEGER)
    })
    List<ReportChartMap> getBarChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("select saveTime,num from t_log_count where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            " and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d') order by saveTime desc")
    @Results({
            @Result(column="saveTime", property="itemName", jdbcType= JdbcType.VARCHAR),
            @Result(column="num", property="itemValue", jdbcType=JdbcType.INTEGER)
    })
    List<ReportChartMap> getLineChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
}
