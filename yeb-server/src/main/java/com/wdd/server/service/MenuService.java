package com.wdd.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wdd.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
public interface MenuService extends IService<Menu> {
    /**
     * 通过用户id查询列表
     * @return
     */
    List<Menu> getMenusByAdminId();

    /**
     * 根据角色获取列表
     * @return
     */
    List<Menu> getMenusWithRole();

    List<Menu> getAllMenus();
}
