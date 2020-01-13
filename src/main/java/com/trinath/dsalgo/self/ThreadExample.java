package com.trinath.dsalgo.self;

import java.util.concurrent.TimeUnit;

public class ThreadExample implements Runnable
{
//    public static void main(String[] args)
//    {
//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
//
//        for (int i = 1; i <= 5; i++)
//        {
//            Task task = new Task("Task " + i);
//            System.out.println("Created : " + task.getName());
//
//            executor.execute(task);
//        }
//        executor.shutdown();
//    }
public static void main(String[] args) {
    ThreadExample runnable = new ThreadExample();
    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);
    thread1.start();
    thread2.start();
}

    public void run() {
        try {
            for (int i = 1; i <= 5; i++)
        {
                System.out.println("Executing : " + i+ Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(1);
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}