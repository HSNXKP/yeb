package com.wdd.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdd.server.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 查询所有部门
     * @param parentId
     * @return
     */
    List<Department> getAllDepartments(Integer parentId);

    /**
     * 添加部门
     * @param department
     */
    void addDepartment(Department department);

    /**
     * 删除部门
     * @param department
     */
    void deleteDepartment(Department department);

}
