package com.wdd.server.controller;


import com.wdd.server.pojo.Admin;
import com.wdd.server.pojo.RespBean;
import com.wdd.server.pojo.Role;
import com.wdd.server.service.AdminService;
import com.wdd.server.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获得所有操作员")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keywords){
        return adminService.getAllAdmins(keywords);
    }

    @ApiOperation(value = "更新操作员")
    @PostMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin){
        if (adminService.updateById(admin)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@RequestBody Integer id){
        if (adminService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除成功");
    }

    @ApiOperation(value = "获得全部角色")
    @GetMapping("/roles")
    public List<Role> getAllRole(){
        return roleService.list();
    }

    @ApiOperation(value = "更新操作员角色")
    @PutMapping("/roles")
    public RespBean updateAdmin(Integer adminId,Integer[] rids){
        return adminService.updateAdminRole(adminId,rids);
    }

}
