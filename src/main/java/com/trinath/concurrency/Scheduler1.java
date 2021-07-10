package com.trinath.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
//https://stackoverflow.com/questions/27834358/multi-threaded-task-scheduler-in-java
interface  Scheduler{
    void schedule(Runnable r, long delay);
        }
public class Scheduler1 implements  Scheduler{

    private static long START_TIME;

    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);

        START_TIME = System.currentTimeMillis();
        Runnable task1 = printTask("T1");
        Runnable task2 = printTask("T2");
        Runnable task3 = printTask("T3");
        Runnable task4 = printTask("T4");

        scheduledExecutorService.scheduleAtFixedRate(task1, 10, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(task2, 50, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(task3, 100, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(task4, 200, 1, TimeUnit.SECONDS);

        Thread.sleep(15000);
        scheduledExecutorService.shutdown();
        scheduledExecutorService.awaitTermination(6000, TimeUnit.SECONDS);
    }

    private static Runnable printTask(String prefix) {
        return () -> System.out.println(prefix + ": " + (System.currentTimeMillis() - START_TIME));
    }

    @Override
    public void schedule(Runnable r, long delay) {

    }
}
