package com.wdd.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdd.server.pojo.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id查询菜单
     * @param id
     * @return
     */
     List<Menu> getMenusByAdminId(Integer id);

    /**
     * 根据角色获取列表
     * @return
     */
    List<Menu> getMenusWithRole();

    List<Menu> getAllMenus();
}
