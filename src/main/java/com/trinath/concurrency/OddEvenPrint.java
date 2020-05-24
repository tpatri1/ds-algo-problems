package com.trinath.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class OddEvenPrint {
    //private AtomicInteger num = new AtomicInteger();
    private int  num = 0;
    Thread odd = new Thread(new Runnable() {
        @Override
        public void run() {
            for(int i=0; i<=1000; i++){
                try {
               //     System.out.println("Odd run "+ " "+Thread.currentThread().getName());
                    printOdd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    Thread even = new Thread(new Runnable() {
        @Override
        public void run() {
            for(int i=0; i<=1000; i++){
                try {
                 //   System.out.println("Even run "+ " "+Thread.currentThread().getName());
                    printEven();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    });
    private void printEven() throws InterruptedException {
        synchronized (this){
            while(num%2==1){// num is shared muutable data
                //System.out.println("Even Going to wait: "+num+ " "+Thread.currentThread().getName());
                this.wait();
            }
            System.out.println("even  "+num);
            num++;
            //isOdd= !isOdd;
            this.notifyAll();
        }
    }

    private void printOdd() throws InterruptedException {
        synchronized (this){
            while(num%2==0){
                //System.out.println("Odd Going to wait: "+num+ " "+Thread.currentThread().getName());
               // Thread.sleep(1000);
                this.wait();
            }
            System.out.println("odd  "+num);
            num++;

            this.notifyAll();
        }
    }

    public void oddEvenPrint(){
        try { odd.start();
        even.start();
        odd.join();
        even.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        OddEvenPrint oddEvenPrint = new OddEvenPrint();
        oddEvenPrint.oddEvenPrint();
    }

}
