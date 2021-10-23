package com.trinath.concurrency;

import java.util.Random;

public class RaceCondition {
    int randInt = 0;
    Random random = new Random(System.currentTimeMillis());
    private  void   print(){
        int i=1000000;
        synchronized (this) {
            while (i >= 0) {
                if (randInt % 5 == 0) {
                    if (randInt % 5 != 0)
                        System.out.println(randInt);
                }
                i--;
            }
        }

    }

    private  void modify(){
        int i=1000000;
        synchronized (this) {
            while (i >= 0) {
                randInt = random.nextInt(1000);
                i--;
            }
        }
    }

    public void runTest(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                print();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                modify();
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        RaceCondition rc = new RaceCondition();
        rc.runTest();
    }
}
