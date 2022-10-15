package com.wdd.server.controller;

import com.wdd.server.pojo.Admin;
import com.wdd.server.pojo.AdminLoginParam;
import com.wdd.server.pojo.RespBean;
import com.wdd.server.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author: wdd
 * @date: 2022/9/23 10:05
 */

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value ="登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }


    @ApiOperation(value = "获取当前登录的用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if (principal==null){
            return null;
        }
        String username = principal.getName();
       Admin admin= adminService.getAdminByUserName(username);
       admin.setPassword(null);
       //通过admin的id查询角色  给我们的用户添加角色
       admin.setRoles(adminService.getRoles(admin.getId()));
       return admin;
    }

    @ApiOperation(value ="退出登录")
    @PostMapping("/logout")
    public RespBean logOut(){
        return RespBean.success("注销成功!");
    }

}



