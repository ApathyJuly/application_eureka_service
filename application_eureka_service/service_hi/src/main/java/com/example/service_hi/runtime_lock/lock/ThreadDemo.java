package com.example.service_hi.runtime_lock.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo {
    public static void main(String[] args) {
        Count count = new Count();
        new Thread() {
            public void run() {
                count.add(1);
            }
        }.start();
        new Thread() {
            public void run() {
                count.add(2);
            }
        }.start();
        count.getM();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.getM();
    }
}
class Count {
    private int m = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void add(int n) {
        lock.lock();
        System.out.println("add获取锁"+n);
        try {
            m += n;
            try {
                System.out.println("thread1等待3秒");
                Thread.sleep(3000);//线程阻塞3s，让出cpu使用权
                System.out.println("thread1结束3秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
//            condition.signalAll();
            System.out.println("add释放锁"+n);
        }
    }
    public void addd(int n) {
        lock.lock();
        System.out.println("addd获取锁"+n);
        try {
            m += n;
            try {
                System.out.println("thread2等待3秒");
                Thread.sleep(3000);
                System.out.println("thread2结束3秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
            System.out.println("addd释放锁"+n);
        }
    }

    public synchronized void addsyn(int n) {
        System.out.println("addd获取锁222");
        m += n;
        System.out.println("addd释放锁222");
    }

    public void getM() {
        System.out.println(m);
    }
}