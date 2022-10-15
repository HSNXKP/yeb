package com.wdd.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdd.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 *
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {


    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
