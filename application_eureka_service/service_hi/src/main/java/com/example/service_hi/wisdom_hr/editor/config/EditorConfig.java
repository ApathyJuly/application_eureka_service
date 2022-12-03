package com.example.service_hi.wisdom_hr.editor.config;

import com.example.service_hi.system_package.util.url.ApplicationURLUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class EditorConfig {
    @Configuration
    public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {//extends WebMvcConfigurerAdapter
        //文件上传虚拟路径和绝对路径映射
        //虚拟路径/staticDir/**
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/staticDir/**")
                    .addResourceLocations("file:"+ ApplicationURLUtils.getApplicationRelative());
            /**
             * 配置商城图片路径
             */
//            registry.addResourceHandler("/staticDir/shopping_images/**")
//                    .addResourceLocations("classpath:/shopping_images/");
        }
    }
}
