package com.example.service_hi.springcloudconfig.shiro;


import com.example.service_hi.user.entity.User;
import com.example.service_hi.user.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;


/**
 * @ClassName MyRealm
 * @Description TODO
 * @Author Apathy
 * @Date 2021/1/19 17:53
 * @Version 1.0
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
//    private RoleService roleService;
//    @Autowired
//    private RoleUserDao roleUserDao;
    /**
     *
     * @Description //TODO 授权相关 * @Date 18:16 2021/1/19
     *
     * @Param [principalCollection]
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取数据库的该用户的角色信息
        User user = (User) principal.getPrimaryPrincipal();
//        List<RoleUser> roleUsers = roleUserDao.queryByUserId(user.getUserId());
//        if (roleUsers == null) {
//            //判断是否给该用户分配角色，如果没有则返回空
//            return null;
//        }
        ArrayList<String> roles = new ArrayList<>();
        //把角色信息添加到当前登录用户中
//        for (int i = 0; i < roleUsers.size(); i++) {
//            roles.add(roleService.queryById(roleUsers.get(i).getRoleId()).getRoleName());
//        }
        info.addRoles(roles);
        return info;
    }

    /**
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @Description //TODO 认证相关 * @Date 18:16 2021/1/19
     * @Param [authenticationToken]
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //根据token获取用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //取出token中的用户名和密码
        String username = token.getUsername();
        //查询用户是否存在
        User user = userService.selectUserByUserName(username);
        if (user == null) {
            return null;
        }
        //无需判断密码，shiro会帮我们判断
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return simpleAuthenticationInfo;
    }
}
