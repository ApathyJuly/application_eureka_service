package com.example.service_hi.runtime_lock.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskQeue {
    /**
     * ReentrantLock可重入锁
     * ReentrantLock比直接使用synchronized更安全，线程在tryLock()失败的时候不会导致死锁。
     */
    //创建ReentrantLock锁对象
    private final Lock lock = new ReentrantLock();
    //使用Condition对象可以实现锁的wait和notify的功能。
    private final Condition conditionOne = lock.newCondition();
    private final Condition conditionTwo = lock.newCondition();
    private Queue<String> queue = new LinkedList<>();

    public void addTask(String s) {
        //获取锁
        lock.lock();
        try {
            queue.add(s);
            //signalAll()唤醒所有等待的线程，signal()会唤醒某个等待线程
//            conditionOne.signal();//唤醒conditionOne线程
            conditionOne.signalAll();
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    public String  getTask() {
        //获取锁
        lock.lock();
        try {
            while (queue.isEmpty()) {
                //await()释放当前锁，并进入等待状态；
                conditionOne.await();
            }
            return queue.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
        return "failed!";
    }
}


