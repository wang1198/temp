package com.ctsi.dao;

import com.ctsi.domain.PwdResetRecord;
import org.apache.ibatis.annotations.*;

/**
 * ClassName ResetPwdRecordDao
 * Description //TODO
 * Author tongliwei
 * Date 2020/3/31 13:21
 * Version v1.0
 **/
@Mapper
public interface ResetPwdRecordDao {
    @Select("select id,userId, userName,email,token,createTime,expireTime,status from cscp_find_record where token=#{token,jdbcType=VARCHAR}")
    PwdResetRecord getRecord(String token);
    @Update("update cscp_find_record set status=#{status,jdbcType=VARCHAR} where userId=#{userId,jdbcType=VARCHAR}")
    void modifyRecord(@Param("status") Integer status,@Param("userId") String userId);
    @Insert("insert into cscp_find_record(userId, userName,email,token,createTime,expireTime,status) values (#{userId,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{token,jdbcType=VARCHAR}, #{createTime,jdbcType=VARCHAR},#{expireTime,jdbcType=VARCHAR},#{status,jdbcType=INTEGER})")
    void insert(PwdResetRecord record);
}
