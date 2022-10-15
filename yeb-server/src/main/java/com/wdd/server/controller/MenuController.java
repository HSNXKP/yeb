package com.wdd.server.controller;


import com.wdd.server.pojo.Menu;
import com.wdd.server.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
@RestController
@RequestMapping("/system/config")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId(){
        return menuService.getMenusByAdminId();
    }

//    @ApiOperation(value = "根据角色查询列表")
//    @GetMapping("/menus")
//    public List<Menu> getMenusWithRole(){
//        return menuService.getMenusWithRole();
//    }

}
