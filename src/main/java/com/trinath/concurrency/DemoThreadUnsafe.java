package com.trinath.concurrency;

import java.util.Random;

public class DemoThreadUnsafe {
    static Random random = new Random(System.currentTimeMillis());
    int randInt;

    public static void  main(String args[]) throws InterruptedException {


        ThreadUnsafeCounter counter = new  ThreadUnsafeCounter();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<100; i++){
                    counter.increment();
                    DemoThreadUnsafe.sleepRandomlyForLessThan10Secs();

                }
            }
        });

        // setup thread2 to decrement the badCounter 200 times
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    counter.decrement();
                    DemoThreadUnsafe.sleepRandomlyForLessThan10Secs();
                }
            }
        });

        thread1.start();
        thread2.start();
        //wait
        thread1.join();
        thread2.join();

        // print final value of counter
        counter.printFinalCounterValue();

        //
        runTest();

    }
    public static void sleepRandomlyForLessThan10Secs() {
        try {
            Thread.sleep(random.nextInt(10));
        } catch (InterruptedException ie) {
        }
    }

    public static void runTest() throws InterruptedException {
        System.out.println("Print thread interleave");
        DemoThreadUnsafe demo = new DemoThreadUnsafe();
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
               demo.printer();
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.modifier();
            }
        });

        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();
    }

    public void printer(){
        int i= 100000;
        while(i!=0){
            synchronized(this) {
                if (randInt % 5 == 0) {
                    if (randInt % 5 != 0) {
                        System.out.println(randInt);
                    }
                }
                i--;
            }
        }
    }

    public void modifier(){
        int i= 100000;
        while(i!=0){
            synchronized(this) {
                randInt = random.nextInt(1000);
                i--;
            }
        }
    }
    static class ThreadUnsafeCounter {

        int count = 0;

        public void increment() {
            count++;
        }

        public void decrement() {
            count--;
        }

        void printFinalCounterValue() {
            System.out.println("counter is: " + count);
        }
    }
}
