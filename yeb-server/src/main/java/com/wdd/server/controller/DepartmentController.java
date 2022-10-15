package com.wdd.server.controller;


import com.wdd.server.pojo.Department;
import com.wdd.server.pojo.RespBean;
import com.wdd.server.service.DepartmentService;
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
@RequestMapping("/system/basic/department")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/")
    @ApiOperation(value = "获取所有部门")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }


    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/")
    public RespBean deleteDepartment(@RequestBody Integer id){
        return departmentService.deleteDepartmentById(id);
    }

}
