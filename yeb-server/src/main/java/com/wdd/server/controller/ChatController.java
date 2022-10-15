package com.wdd.server.controller;

import com.wdd.server.pojo.Admin;
import com.wdd.server.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: wdd
 * @date: 2022/10/14 19:22
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "获得所有操作员")
    @GetMapping("/admin")
    public List<Admin> getAllAdmins(String keywords){
        return adminService.getAllAdmins(keywords);

    }

}
