package com.wdd.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdd.server.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoles(Integer adminId);

}
