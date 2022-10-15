package com.wdd.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdd.server.pojo.MenuRole;
import com.wdd.server.pojo.RespBean;

public interface MenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);

}
