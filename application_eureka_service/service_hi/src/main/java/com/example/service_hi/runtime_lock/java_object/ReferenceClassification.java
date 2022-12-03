package com.example.service_hi.runtime_lock.java_object;

import com.example.service_hi.user.entity.User;

import java.lang.ref.*;

public class ReferenceClassification {
    public static void main(String[] args) {
        reference();
    }

    public static void reference(){
        /**
         * 强引用，最常见的应用，将一个对象赋值给一个引用变量，这个引用变量就是一个强引用。
         * 当一个对象被强引用变量引用时，他处于可达状态,不会被GC。一般new出来的对象都是强引用。
         * JVM必定不会回收这个对象，即使在内存不足的情况下，JVM宁愿抛出OutOfMemory错误也不会回收.
         */
        Object obj = new Object();
        System.out.println("强引用对象状态" + obj);
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println("强引用对象状态" + obj);
        /**
         * 软引用：需要使用SoftReference类实现，对于软引用来说，当内存足够使用时他不会被回收。
         * 内存不足时它将被GC。软引用通常用于对内存敏感的程序中
         * 软引用的声明的借助强引用或者匿名对象，使用泛型SoftReference<T>；可以通过get方法获得强引用。
         * SoftReference<T> extends Reference<T>（继承关系）
         */
        SoftReference<User> softReference = new SoftReference<>(new User());
        System.out.println("软引用对象状态" + softReference.get());
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println("软引用对象状态" + softReference.get());

        /**
         * 弱引用：需要使用WeakReference类实现，其比软引用生存期更短。
         * 对于弱引用的对象来说，只要垃圾回收机制一运行，无论内存是否足够，都将会被回收。即：被弱引用指向的对象在下一次GC时被回收
         * WeakReference<T> extends Reference<T>（继承关系）
         */
        WeakReference<User> weakReference = new WeakReference<User>(new User());
        System.out.println("弱引用对象状态" + weakReference.get());
        weakReference.get().setUserId("111");
        String i =weakReference.get().getUserId();
        System.out.println(i);
        System.gc();                //通知JVM的gc进行垃圾回收，虽然发出了通知，JVM不一定会立刻执行，也就是说这句是无法确保此时JVM一定会进行垃圾回收的
        System.out.println("弱引用对象状态" + weakReference.get());

        //如果存在强引用同时与之关联，则进行垃圾回收时也不会回收该对象（软引用也是如此）
//        User user = weakReference.get();//强引用
//        System.out.println("弱引用对象关联状态" + weakReference.get());
//        System.gc();                //通知JVM的gc进行垃圾回收
//        System.out.println("弱引用对象关联状态" + weakReference.get());

        /**
         * 虚引用：需要使用PhantomReference类实现，他不能单独使用，必须和引用队列(ReferenceQueue)联合使用。
         *  如果一个对象仅持有虚引用，在任何时候都可能被垃圾回收，
         *  虚引用与软引用和弱引用的一个区别在于：虚引用必须和引用队列联合使用，虚引用主要用来跟踪对象被垃圾回收的活动。
         *  PhantomReference<T> extends Reference<T>（继承关系）
         */
        PhantomReference<User> phantomReference = new PhantomReference<User>(new User(),new ReferenceQueue<User>());
        System.out.println("虚引用对象状态" + phantomReference.get());
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println("虚引用对象状态" + phantomReference.get());
    }



}
