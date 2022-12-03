package com.example.service_hi.system_package.util.test;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TestUtil {

    public void threadTest(){
        Thread thread = new Thread(()->{
            System.out.println("This is Thread!");
        });
        thread.start();
        List<T> list = new ArrayList<>();
        mapUtil(list);
    }
    //    Map 映射,使用 Stream 对象的 map 方法将原来的列表经由 Lambda 表达式映射为另一个列表，并通过 collect 方法转换回 List 类型

    /**
     *  <? extends T>与<? super T> 两种语法。简单来说，
     *  <? extends T> 是Get First，适用于，消费集合元素的场景；
     *  <? super T>是Put First，适用于，生产集合元素为主的场景。
     * @param list
     * @return
     */
    public List<String> mapUtil(List<? extends T> list){
        List<String> collect = list.stream().filter(str -> "".equals(str))
                .map(Object::toString)
                .collect(Collectors.toList());
        collect.forEach(str->
                System.out.println(str)
        );
        return collect;
    }

}
