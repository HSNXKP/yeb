package com.wdd.server.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdd.server.mapper.AdminRoleMapper;
import com.wdd.server.pojo.AdminRole;
import com.wdd.server.service.AdminRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

}
