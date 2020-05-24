package com.trinath.concurrency;

public class LoopMayNeverEnd {
    private boolean mDone = false;

    Thread T1 = new Thread(new Runnable() {
        @Override
        public void run() {
                    //     System.out.println("Odd run "+ " "+Thread.currentThread().getName());
            work();
            }
    });


    Thread T2 = new Thread(new Runnable() {
        @Override
        public void run() {
                    //   System.out.println("Even run "+ " "+Thread.currentThread().getName());
            stopWork();
        }

    });

    private void work() {
        while(!mDone){
            System.out.println("Working..");
        }
    }
    private void stopWork() {
        mDone = true;
        System.out.println("I am done..");
    }

    public  void runTest(){
        try { T1.start();
            T2.start();
            T1.join();
            T2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        LoopMayNeverEnd obj = new LoopMayNeverEnd();
        obj.runTest();
    }

}
