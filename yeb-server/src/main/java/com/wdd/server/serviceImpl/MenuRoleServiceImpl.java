package com.wdd.server.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdd.server.mapper.MenuRoleMapper;
import com.wdd.server.pojo.MenuRole;
import com.wdd.server.pojo.RespBean;
import com.wdd.server.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author: wdd
 * @date: 2022/9/26 15:42
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements MenuRoleService {



    @Autowired
    private MenuRoleMapper menuRoleMapper;


    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if (null==mids || 0==mids.length){
            return RespBean.success("更新成功");
        }
      Integer result=  menuRoleMapper.insertRecord(rid,mids);
        if (result==mids.length){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
