package com.example.service_hi.runtime_lock.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        try {
            getExecutor();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void getExecutor() throws ExecutionException, InterruptedException {
        int taskSize  = 10;
        //创建一个线程池,newFixedThreadPool
        //4种线程池：newCachedThreadPool（） 、newFixedThreadPool （）、newScheduledThreadPool （）、newSingleThreadExecutor （）
         ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
         List<Future> list = new ArrayList<Future>();
         for (int i = 0; i < taskSize; i++) {
             Callable c = new MyCallable(i + " ");
            // 执行任务并获取Future对象
             Future f = pool.submit(c);
             list.add(f);
         }
        // 关闭线程池
         pool.shutdown();
        // 获取所有并发任务的运行结果
         for (Future f : list) {
            //从Future对象上获取任务的返回值，并输出到控制台
             System.out.println("res：" + f.get().toString());
         }

    }
}
class MyCallable implements Callable {
    private String str;
    public MyCallable() {
    }

    public MyCallable(String s) {
        this.str = s;
    }

    @Override
    public String call() throws Exception {
        return this.str;
    }
}
