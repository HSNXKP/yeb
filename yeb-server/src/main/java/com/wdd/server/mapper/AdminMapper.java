package com.wdd.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdd.server.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 获得所有操作员
     * @param id
     * @param keywords
     * @return
     */
    List<Admin>  getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    Integer updateAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
