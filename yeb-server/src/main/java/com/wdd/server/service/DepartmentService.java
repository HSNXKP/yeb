package com.wdd.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wdd.server.pojo.Department;
import com.wdd.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments();


    /**
     * 添加部门
     * @return
     */
    RespBean addDepartment(Department department);

    /**
     * 删除部门
     * @return
     */
    RespBean deleteDepartmentById(Integer id);
}
