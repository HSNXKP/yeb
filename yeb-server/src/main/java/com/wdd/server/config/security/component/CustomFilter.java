package com.wdd.server.config.security.component;

import com.wdd.server.pojo.Menu;
import com.wdd.server.pojo.Role;
import com.wdd.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 根据角色的url来分析所需的角色
 *
 * @author: wdd
 * @date: 2022/9/27 16:43
 */

@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取请求的url /user/32131
        String requestUrl = ((FilterInvocation)object).getRequestUrl();
        //获得所有角色对应的的菜单
        List<Menu> menus = menuService.getMenusWithRole();
        //遍历出所有的角色对应的路径url
        for (Menu menu : menus) {
            //判断请求url与角色菜单中的url是否匹配
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                //将获得的url,对应的Role,封装起来  这里的Role不是单个的一个,因为有可能存在角色不同，也可以访问相同的url
                String[] str = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                //返回给 Collection<ConfigAttribute>
                return SecurityConfig.createList(str);
            }
        }

        //没有匹配的url默认登录即可访问 并打上一个ROLE_LOGIN的用户角色
        return SecurityConfig.createList("ROLE_LOGIN");

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class
                .isAssignableFrom(clazz);
    }
}
