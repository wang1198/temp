package com.ctsi.dao;

import com.ctsi.domain.ConfigInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * ClassName CscpConfDao
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/6 11:19
 * Version v1.0
 **/
@Mapper
public interface CscpConfDao {
    @Select("select config_value from cscp_config where config_key=#{key,jdbcType=VARCHAR}")
    String getConfig(String key);
    @Select("select config_key,config_value from cscp_config")
    @Results({
            @Result(column="config_key", property="configKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="config_value", property="configValue", jdbcType=JdbcType.VARCHAR),
    })
    List<ConfigInfo> getConfigs();
    @Insert("insert into cscp_config (config_key,config_value) values(#{key,jdbcType=VARCHAR},#{value,jdbcType=VARCHAR}) ON DUPLICATE KEY UPDATE\n" +
            "        config_key = #{key,jdbcType=VARCHAR},config_value=#{value,jdbcType=VARCHAR}")
    void insert(@Param("key") String key, @Param("value") String value);
}
