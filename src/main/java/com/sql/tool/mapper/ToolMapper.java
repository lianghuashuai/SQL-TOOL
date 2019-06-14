package com.sql.tool.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



@Repository
@Mapper
public interface ToolMapper {

    /**
     * 执行SQL语句
     *
     * @param clobsql sql语句
     * @return
     */
    void updatesql(@Param("clobsql") String clobsql, @Param("reportid") String reportid);
}