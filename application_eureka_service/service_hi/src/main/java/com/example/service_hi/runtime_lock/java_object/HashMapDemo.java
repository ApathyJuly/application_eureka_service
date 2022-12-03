package com.example.service_hi.runtime_lock.java_object;

import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args) {
        getHashmapResoult();
    }
    public static void getHashmapResoult(){
        HashMap hashMap = new HashMap();
        hashMap.put("通话",1);
        hashMap.put("重地",2);
        System.out.println(hashMap);
        System.out.println("通话".hashCode());
        System.out.println("重地".hashCode());
    }
}
