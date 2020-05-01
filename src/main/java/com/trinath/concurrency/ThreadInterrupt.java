package com.trinath.concurrency;

public class ThreadInterrupt {
    public void threadInterruptExample(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("About to sleep: "+ Thread.currentThread());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Interreupt flag is cleared on Catch Exception: "+Thread.interrupted()+ ":"+ Thread.currentThread().isInterrupted()+" for thread: "+Thread.currentThread());
                    Thread.currentThread().interrupt();
                    //System.out.println("Interreupt flag is set again : "+ Thread.currentThread().isInterrupted()+ ":" +Thread.interrupted()+" for thread: "+Thread.currentThread());
                    System.out.println("Interreupt flag is set again : "+ Thread.interrupted()+ ":" +Thread.currentThread().isInterrupted()+" for thread: "+Thread.currentThread());
                    // Static method Thread.interrupted() returns the value and clear off unlike the instance method  isInterrupted()
                }
            }
        });

        thread1.start();
        System.out.println("About to wake up the sleepy thread ...");
        thread1.interrupt();
        System.out.println("Woke up sleepy thread ...");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        ThreadInterrupt tI = new ThreadInterrupt();
        tI.threadInterruptExample();
    }

}
