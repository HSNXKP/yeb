package com.wdd.server.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdd.server.mapper.DepartmentMapper;
import com.wdd.server.pojo.Department;
import com.wdd.server.pojo.RespBean;
import com.wdd.server.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    @Override
    public RespBean addDepartment(Department department)  {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
        if (1==department.getResult()){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @Override
    public RespBean deleteDepartmentById(Integer id) {
         Department department= new Department();
         department.setId(id);
         departmentMapper.deleteDepartment(department);
         if (-2==department.getResult()){
             return RespBean.error("该部门下还有子部门,删除失败");
         }
         if (-1==department.getResult()){
             return RespBean.error("该部门下还有员工，删除失败");
         }
         if (1==department.getResult()){
             return RespBean.success("删除成功");
         }
         return RespBean.error("删除失败");
    }
}
