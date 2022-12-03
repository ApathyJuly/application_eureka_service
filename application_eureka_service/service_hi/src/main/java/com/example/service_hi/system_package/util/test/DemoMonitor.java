package com.example.service_hi.system_package.util.test;

import com.alibaba.fastjson.JSONArray;
import com.example.service_hi.user.entity.User;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

public class DemoMonitor {
    public static void main(String[] args) {
        User u = new User();
        u.setUserName("username");
        u.setPassword("password");
        getFiledArr(u, "user");
        System.out.println("user" + u.toString());
        System.out.println("user1" + getUser(u).toString());
    }
    public static void getFiledArr(Object obj,String className){
        Field[] fields=obj.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            fieldNames[i]=fields[i].getName();
        }
        JSONArray jsonArray=new JSONArray(Arrays.asList(fieldNames));
        System.out.println(className + "=" + jsonArray.toString());
    }
    public static User getUser(User user){
        return Optional.ofNullable(user)
                .filter(u->"admin".equals(user.getUserName()))
                .orElseGet(()->{
                    User user1 = new User();
                    user1.setUserName("amin");
                    return user1;
                });
    }

}
