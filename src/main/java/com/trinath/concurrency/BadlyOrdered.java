package com.trinath.concurrency;

public class BadlyOrdered {
    private boolean a = false;
    private boolean b = false;

    Thread T1 = new Thread(new Runnable() {
        @Override
        public void run() {
            int i=0;
            while(i<1000) {//may reorder
                method1();
                i++;
                //System.out.println("order exedcuted " + i);
            }
        }
    });


    Thread T2 = new Thread(new Runnable() {
        @Override
        public void run() {
            int i=0;
            while(true) {
                if (method2()) {
                    System.out.println("Out of order exedcuted " + i);

                }
                i++;
            }
        }

    });

    private void method1() {
        a= true; // first
        b= true; // second, but can execute out of order
    }
    private boolean method2() {
        boolean r1 = b;
        boolean r2 = a;// sees false even it is set before b value
        boolean r3 = a;// sees true
        return (r1 && !r2) && r3;
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
        BadlyOrdered obj = new BadlyOrdered();
        obj.runTest();
    }
}
