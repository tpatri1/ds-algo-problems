package com.trinath.concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock_Semaphore {

    //signal is senton a condition variable when no thread is waiting
    private static void reentrantMissedSignal(){
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Thread signaler = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock(); // acquire the lock
                condition.signal();// send the signal when no therad is waiting so will be missed
                System.out.println("Sent signal");
                reentrantLock.unlock();

            }
        });

        Thread wiater = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                try{
                    condition.await();// wait for ever as signal is missed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    reentrantLock.unlock();
                }

            }
        });

        try {
            signaler.start();
            signaler.join();
            wiater.start(); // started later so the sent signal is missed as no thread is waiting.. if we start waiter first can catch the signal
            wiater.join();
            System.out.println("Program Exiting.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
// Does not miss the signal
    private static void semaphoretMissedSignal(){
        Semaphore semaphore = new Semaphore(1);


        Thread signaler1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println("Sending signal after releasing semaphore");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();//In semaphore if you don't release the semaphore before attempting to acquire again , will block the other thread(so release in finally) for ever unlike reentrant lock that is non blocking on recursive acquire

                System.out.println("Sent signal");

            }
        });

        Thread wiater1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    System.out.println("Waiting to Acquire semaphore");
                    semaphore.acquire();// wait to acquire the permit as soon as Signaler release a semaphore
                    System.out.println("Received signal");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        try {
            signaler1.start();
            System.out.println("Signaler Started .");
            Thread.sleep(5000);

            wiater1.start(); // started later so the sent signal is missed as no thread is waiting.. if we start waiter first can catch the signal
            signaler1.join();
            wiater1.join();
            System.out.println("Program Exiting.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        ReentrantLock_Semaphore.semaphoretMissedSignal();
        //ReentrantLock_Semaphore.reentrantMissedSignal(); - missed
    }
}
