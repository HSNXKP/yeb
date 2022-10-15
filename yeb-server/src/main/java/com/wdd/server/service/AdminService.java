package com.wdd.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wdd.server.pojo.Admin;
import com.wdd.server.pojo.RespBean;
import com.wdd.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
public interface AdminService extends IService<Admin> {

    /**
     * 登录之后返回token
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名实现用户
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据id查询角色
     * @return
     */
    List<Role> getRoles(Integer adminId);

    /**
     * 获得全部操作员
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmins(String keywords);


    /**
     * 更新操作员
     * @param id
     * @param rids
     * @return
     */
    RespBean updateAdminRole(Integer adminId, Integer[] rids);
}
