package com.trinath.dsalgo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException, Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        final AtomicInteger atomicInteger = new AtomicInteger(0);

        for (int i = 0; i < 10; i++) {
            Future<?> future = executor.submit(() -> {
                try {
                    atomicInteger.incrementAndGet();
                    printNumbers(); // first call
                    printNumbers(); // second call
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }
        executor.shutdown();
        executor.awaitTermination(10,  TimeUnit.SECONDS);
        System.out.println("Total Executed : " + atomicInteger.get());

       // Thread.sleep(3000);
        //executor.shutdownNow();  // will interrupt the task
        //executor.awaitTermination(3, TimeUnit.SECONDS);
    }
    private static void printNumbers() throws Exception {
        for (int i = 0; i < 10; i++) {
           // if(i==4) Thread.currentThread().interrupt();
            System.out.print(i+Thread.currentThread().getName()+"\n");
           // try {
               // Thread.sleep(1000);
           // }
            //catch (InterruptedException e) {

           // }
        }
    }
}
