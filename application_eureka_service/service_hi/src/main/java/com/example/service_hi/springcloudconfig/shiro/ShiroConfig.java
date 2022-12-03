package com.example.service_hi.springcloudconfig.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;

import java.util.LinkedHashMap;

/**
 * @ClassName ShiroConfig
 * @Description TODO
 * @Author lei
 * @Date 2020/8/18 17:42
 * @Version 1.0
 */
//@Configuration
public class ShiroConfig {
    /**
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @Description //TODO 注入shiro过滤器 * @Date 17:50 2020/8/18
     * @Param [securityManager] 安全管理器
     **/
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager getDefaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置自定义SecurityManager
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager);
        //设置登录的地址，未认证时会访问此地址
        shiroFilterFactoryBean.setLoginUrl("/user/tologin");
        //设置认证成功后要访问的地址
        shiroFilterFactoryBean.setSuccessUrl("/menus");
        //设置认证失败会访问的页面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/user/toLogin");
        // LinkedHashMap 是有序的，进行顺序拦截器配置
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        // 配置可以匿名访问的地址，可以根据实际情况自己添加，放行一些静态资源等，anon 表示放行
        map.put("/static/css/**", "anon");
        map.put("/static/elementUi/**", "anon");
        map.put("/static/img/**", "anon");
        map.put("/static/js/**", "anon");
        map.put("/static/excl/**", "anon");
        map.put("/user/tologin", "anon");
        map.put("/user/login", "anon");
        map.put("/menus", "authc");
        map.put("/userList", "authc");
        map.put("/menu/menuMapper", "authc");

        // 配置 logout 过滤器
        map.put("/user/logout", "logout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * @return org.apache.shiro.web.mgt.DefaultWebSecurityManager
     * @Description //TODO 安全管理器 * @Date 17:53 2020/8/18
     * @Param [getRealm]
     **/
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm getMyRealm) {

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        defaultWebSecurityManager.setRealm(getMyRealm);
        return defaultWebSecurityManager;
    }
/**
 * 
 * @Description //TODO 配置realm * @Date 17:57 2020/8/18
 *
 * @Param []
 * @return com.jinxiandai.operation.config.shiro.MyRealm
 **/
    @Bean
    public MyRealm getMyRealm() {
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }
    //结合shiro标签所需要的bean
//    @Bean(name = "shiroDialect")
//    public ShiroDialect shiroDialect(){
//        return new ShiroDialect();
//
//    }
}
