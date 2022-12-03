package com.example.service_hi.system_package.util.test;

import com.example.service_hi.system_package.util.word.JacobWordUtil;
import com.example.service_hi.user.entity.User;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        User user = new User();
        user.setUserName("admin");

        String fieldName = "userName";
        String value = String.valueOf(getMethodByName(user,fieldName));

        System.out.println("name:"+value);

        List<User> list = new ArrayList<>();
        list.add(user);
        String str = getAllObjectName(list);
        System.out.println("new:" + str);
        new JacobWordUtil(true).createNewDocument();
    }

    private static <T> String  getAllObjectName(List<T> list){
        T user = list.get(0);
        String value = String.valueOf(getMethodByName(user,"userName"));
        System.out.println(value);
        return value;
    }

    /**
     *
     * @param o
     * @param <T> 方法入参为泛型
     * @return <T>:声名此方法持有一个泛型类型T，即此方法为泛型方法。
     */
    private static <T> String[] getFiledName(T o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }
    private static <T> Object getMethodByName(T user, String fieldName){
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        String getter = "get" + firstLetter + fieldName.substring(1);
        Method method = null;
        try {
            method = user.getClass().getMethod(getter, new Class[] {});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object value = null;
        try {
            value = method.invoke(user, new Object[] {});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }
    private static String getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            return  (String)field.get(object);
        } catch (Exception e) {
            return "exception";
        }
    }
}
