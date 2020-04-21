package com.ctsi.dao;

import com.ctsi.domain.CscpFunc;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * ClassName CscpFuncDao
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/11 15:03
 * Version v1.0
 **/
@Mapper
public interface CscpFuncDao {
    @Insert("insert into cscp_org_func values(#{orgID,jdbcType=VARCHAR},#{funcID,jdbcType=VARCHAR})")
    void saveOrgFunc(@Param("orgID") String orgID, @Param("funcID") String funcID);
    @Select("select f.id,f.func_name,f.func_type,f.func_desc from cscp_org_func of,cscp_function f where of.org_id=#{orgID,jdbcType=VARCHAR} and of.func_id=f.id and f.func_type=1")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.VARCHAR, id=true),
            @Result(column="func_type", property="funcType", jdbcType=JdbcType.INTEGER),
            @Result(column="func_name", property="funcName", jdbcType=JdbcType.VARCHAR),
            @Result(column="func_desc", property="description", jdbcType=JdbcType.VARCHAR)
    })
    List<CscpFunc> getFuncs(String orgID);
    @Select("select menu_id from cscp_func_menu m left join cscp_org_func of on m.func_id=of.func_id where of.org_id=#{orgID,jdbcType=VARCHAR}")
    List<String> getFuncMenus(String orgID);
}
