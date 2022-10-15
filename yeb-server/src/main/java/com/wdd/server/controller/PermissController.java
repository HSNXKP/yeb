package com.wdd.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wdd.server.pojo.Menu;
import com.wdd.server.pojo.MenuRole;
import com.wdd.server.pojo.RespBean;
import com.wdd.server.pojo.Role;
import com.wdd.server.service.MenuRoleService;
import com.wdd.server.service.MenuService;
import com.wdd.server.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限组
 * 查看角色  以及角色可控制的菜单管理
 * @author: wdd
 * @date: 2022/9/30 10:35
 */
@RestController
@RequestMapping("/system/basic/permission")
public class PermissController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRoleService menuRoleService;

    @ApiOperation(value = "获得所有角色")
    @GetMapping("/")
    public List<Role> getAllRole(){
        return roleService.list();
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role){
        if (!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid) {
        if (roleService.removeById(rid)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenu(){
        return menuService.getAllMenus();
    }


    @GetMapping("/mid/{rid}")
    @ApiOperation(value = "根据角色id查询菜单id")
    public List<Integer> getMidByRid(@PathVariable Integer rid){
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid",rid)).stream().map(MenuRole::getMenuId).collect(Collectors.toList());

    }

    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        return menuRoleService.updateMenuRole(rid,mids);
    }

}
