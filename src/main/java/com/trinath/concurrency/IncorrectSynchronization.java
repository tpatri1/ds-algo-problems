package com.trinath.concurrency;

public class IncorrectSynchronization {
    //Monitor/Synchronized - Java forces you to have same thread to acquire and release same monitor-lock also in the same method.
    //In contrast,if we use semaphore, we can acquire or release lock in different threads or methods
    static Boolean flag = new Boolean(true);
    public static void main(String args[]){

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (flag){// lock on flag object
                    System.out.println("First thread about to sleep");
                    try {
                        Thread.sleep(5000);
                    System.out.println("Woke up and about to invoke wait()");
                    flag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    catch (IllegalMonitorStateException me){
                        System.out.println("Caught IllegalMonitorStateException");// this caught
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                flag= false;
                System.out.println("Boolean assignment done.");
            }
        });

        try {
            t1.start();
            t1.sleep(1000);
            t2.start();
            t1.join();
            t2.join();
        }
        catch(InterruptedException e){
            System.out.println("Caught Intereupted Exception");
        }
        catch (IllegalMonitorStateException me){
            System.out.println("Caught IllegalMonitorStateException");//does not catch
        }
    }
}
