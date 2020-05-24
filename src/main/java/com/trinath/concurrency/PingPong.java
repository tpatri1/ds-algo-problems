package com.trinath.concurrency;

import java.util.concurrent.Semaphore;

public class PingPong {
    private Semaphore ping = new Semaphore(1);
    private Semaphore pong = new Semaphore(0);

    Thread tPing = new Thread(new Runnable() {
        @Override
        public void run() {
            int i =0;
            while(i<10){
                try {
                    i++;
                    printPing();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    Thread tPong = new Thread(new Runnable() {
        @Override
        public void run() {
            int i =0;
            while(i<10){
                try {
                    i++;
                    printPong();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    private void printPing() throws InterruptedException {
        ping.acquire();// Note what iis aquired , the other one is released
        System.out.println("I \n");
        pong.release();

    }
    private void printPong() throws InterruptedException {
        pong.acquire();
        System.out.println("O \n");
        ping.release();
    }

    //Alternatively using volatile;
    private volatile int val =0;
    private final int MAX =100;

    Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            for(int i=val; i<MAX;){
                if(i!=val){
                    System.out.println("Pong >>>>>> "+ val);
                    i=val;
                }
            }
        }
    });

    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            for(int i=val; i<MAX;){
                val = ++i;
                System.out.println("Ping "+ val);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    });
    private void printPongAlt() throws InterruptedException {
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

    public static void main(String args[]){
        PingPong pingpong = new PingPong();
        pingpong.tPing.start();
        pingpong.tPong.start();
        try {
            pingpong.tPing.join();
            pingpong.tPong.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Alternate using volatiile
        try {
            pingpong.printPongAlt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
