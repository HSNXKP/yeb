package com.wdd.server.config.security.component;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 *
 * /USER url
 * CustomFilter ConfigAttribute->
 * CustomUrlDecisionManager -> DECIDE
 * @author: wdd
 * @date: 2022/9/27 23:18
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication,
                       Object object,
                       Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            //將之前FilterCustom中封装起来的的Roles对应的url进行遍历拿出來(多个角色对应的相同的url)
            String needRole = configAttribute.getAttribute();
            //判断是否为我们之前打上的标签ROLE_LOGIN
            if ("ROLE_LOGIN".equals(needRole)){
                 // 如果是 判断authentication 是否为匿名身份token
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("尚未登录，请登录");
                }else {
                    return;
                }
            }
            //不是ROLE_LOGIN 判断为数据库的角色（此时的Roles为多个角色）
            //判断登录的用户的角色是否在这些Roles里面  如果在里面的可以顺利的访问
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
