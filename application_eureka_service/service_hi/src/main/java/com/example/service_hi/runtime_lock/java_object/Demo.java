package com.example.service_hi.runtime_lock.java_object;

import java.util.Arrays;
import java.util.Date;
import java.util.EmptyStackException;

public class Demo {
    public static void main(String[] args) {
        CloneTest p = new CloneTest("国庆",new Date(1997, 10, 1));
        System.out.println(p.getName());
        System.out.println(p.getBirth().getYear());
//        Date temp = p.getBirth();
//        temp.setYear(2021);
        p.getBirth().setYear(2021);
        System.out.println(p.getName());
        System.out.println(p.getBirth().getYear());
        CloneTest p2 = new CloneTest("国庆c",new Date(1998, 10, 1));
        System.out.println(p.getBirth().getYear());
    }
}

class CloneTest {
    private String name;
    private Date birth;
    public CloneTest(String name, Date birth) {
        this.name = name;
//        this.birth = birth;
        this.birth = new Date(birth.getTime());
    }
//    public Date getBirth() {
//        return birth;
//    }

    public Date getBirth() {
        Date d = new Date(birth.getTime());
//        Date d = (Date) birth.clone();
        //返回对象时，我们返回这个birth属性的拷贝对象，而不是直接返回brith属性。这样每次得到对象后即使操作也不会影响原有对象本身的属性
        return d; //保护性拷贝
    }

    public String getName() {
        return name;
    }
}
