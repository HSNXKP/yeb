package com.wdd.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wdd.server.pojo.Employee;
import com.wdd.server.pojo.RespBean;
import com.wdd.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
public interface EmployeeService extends IService<Employee> {

    /**
     * 普通所有员工
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);


    /**
     * 获得工号
     * @return
     */
    RespBean maxWorkId();

    /**
     * 增加员工
     * @return
     */
    RespBean addEmployee(Employee employee);

    /**
     * 导出员工
     * @param id
     */
    List<Employee> getEmployeeExcel(Integer id);

    /**
     * 获得所有员工工资账套
     * @param currentPage
     * @param size
     * @return
     */
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);

}
