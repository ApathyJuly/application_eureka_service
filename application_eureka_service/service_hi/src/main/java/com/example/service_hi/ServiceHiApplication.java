package com.example.service_hi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceHiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @Value("${posyspath}")
    private String poSysPath;

    @Value("${popassword}")
    private String poPassWord;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "default") String name) {
        System.out.println("ServiceHiApplication--" + name  + "--启动");
        return "ServiceHiApplication： value：" + name + ",port:" + port;
    }

    /**
     * 添加PageOffice的服务器端授权程序Servlet（必须）
     * @return
     */
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        com.zhuozhengsoft.pageoffice.poserver.Server poserver = new com.zhuozhengsoft.pageoffice.poserver.Server();
//        //设置PageOffice注册成功后,license.lic文件存放的目录
//        poserver.setSysPath(poSysPath);
//        ServletRegistrationBean srb = new ServletRegistrationBean(poserver);
//        srb.addUrlMappings("/poserver.zz");
//        srb.addUrlMappings("/posetup.exe");
//        srb.addUrlMappings("/pageoffice.js");
//        srb.addUrlMappings("/sealsetup.exe");
//        return srb;
//    }

    /**
     * 添加印章管理程序Servlet（可选）
     * @return
     */
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean2() {
//        com.zhuozhengsoft.pageoffice.poserver.AdminSeal adminSeal = new com.zhuozhengsoft.pageoffice.poserver.AdminSeal();
//        adminSeal.setAdminPassword(poPassWord);//设置印章管理员admin的登录密码
//        //设置印章数据库文件poseal.db存放目录，该文件在当前demo的“集成文件”夹中
//        adminSeal.setSysPath(poSysPath);
//        ServletRegistrationBean srb = new ServletRegistrationBean(adminSeal);
//        srb.addUrlMappings("/adminseal.zz");
//        srb.addUrlMappings("/sealimage.zz");
//        srb.addUrlMappings("/loginseal.zz");
//        return srb;
//    }

}