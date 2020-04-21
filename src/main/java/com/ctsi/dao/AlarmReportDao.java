package com.ctsi.dao;

import com.ctsi.domain.StaticsItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName AlarmReportDao
 * Description TODO
 * Author tongliwei
 * Date 2020/4/11 21:09
 * Version v1.0
 **/
@Mapper
public interface AlarmReportDao {
    @Select("select sum(alarmInfo) light,sum(alarmLight) low,sum(alarmMiddle) middle,sum(alarmHigh) high,sum(alarmSerious) serious from t_alarm_statics where orgId = #{orgId} and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            " and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d')")
    StaticsItem getPieChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("select sum(alarmInfo) light,sum(alarmLight) low,sum(alarmMiddle) middle,sum(alarmHigh) high,sum(alarmSerious) serious from t_alarm_statics where orgId = #{orgId} and alarmModle='流量' and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            " and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d')")
    StaticsItem getFlowBarChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("select sum(alarmInfo) light,sum(alarmLight) low,sum(alarmMiddle) middle,sum(alarmHigh) high,sum(alarmSerious) serious from t_alarm_statics where orgId = #{orgId} and alarmModle='漏洞' and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            " and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d')")
    StaticsItem getLeakBarChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
    @Select("select sum(alarmInfo) light,sum(alarmLight) low,sum(alarmMiddle) middle,sum(alarmHigh) high,sum(alarmSerious) serious from t_alarm_statics where orgId = #{orgId} and alarmModle='日志' and DATE_FORMAT(saveTime, '%Y-%m-%d') >= DATE_FORMAT(#{begin}, '%Y-%m-%d')" +
            " and DATE_FORMAT(saveTime, '%Y-%m-%d') <= DATE_FORMAT(#{end}, '%Y-%m-%d')")
    StaticsItem getLogBarChart(@Param("orgId") String orgId, @Param("begin") String begin, @Param("end") String end);
}
