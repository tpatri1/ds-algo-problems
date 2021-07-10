package com.trinath.concurrency;

import java.util.LinkedHashMap;
import java.util.concurrent.locks.Lock;

public class DeadlockExample {
    private int counter = 0;
    Object lock1 =  new Object();
    Object lock2 = new Object();

    Runnable incrementor = new Runnable() {
        @Override
        public void run() {
            for(int i=0; i<100; i++){
                try {
                    incrementCounter();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Incrementing " + i);
            }
        }
    };
    Runnable decrementor = new Runnable() {
        @Override
        public void run() {
            for(int i=0; i<100; i++){
                try {
                    decrementCounter();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Decrementing " + i);
            }
        }
    };

    private void incrementCounter() throws InterruptedException {
        synchronized (lock1) {
            System.out.println("Acquired lock 1");
            Thread.sleep(100);
            synchronized (lock2) {
                counter++;
            }
        }
    }
    void decrementCounter() throws InterruptedException {
        synchronized (lock2) {
            System.out.println("Acquired lock2");

            Thread.sleep(100);
            synchronized (lock1) {
                counter--;
            }
        }
    }

    public void runTest() throws InterruptedException {

        Thread thread1 = new Thread(incrementor);
        Thread thread2 = new Thread(decrementor);

        thread1.start();
        // sleep to make sure thread 1 gets a chance to acquire lock1
        Thread.sleep(100);
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Done : " + counter);
    }

    public static void main(String args[]) throws InterruptedException {
        DeadlockExample deadlockExample = new DeadlockExample();
        deadlockExample.runTest();
        //LinkedHashMap
    }
}
