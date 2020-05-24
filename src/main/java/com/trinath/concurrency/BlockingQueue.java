package com.trinath.concurrency;
//Bounded buffer or producer consumer problem
//Notify the consumer when an element is put in the queue (and ask to wait when empty buffer) and notify the producer when space is available(and ask to wait when full)
public class BlockingQueue<T> {

        T[] array;
        int size = 0;
        int capacity;
        int head = 0; // or front to dequeue
        int tail = 0; //or rear to dequeue
        Object obj = new Object();

        public BlockingQueue(int capacity) {
            array = (T[]) new Object[capacity];
            this.capacity = capacity;
        }

        public  void  enqueue(T item) throws InterruptedException {
            synchronized(obj) {//object lock
                while (size == capacity) {
                    System.out.println("***************waiting to enqueue");
                    obj.wait();//obj is the condition variable
                }
                array[tail] = item;
                size++;
                tail = ++tail % capacity;
                obj.notifyAll();

            }
        }

        public   T dequeue() throws InterruptedException {
            T item = null;
            synchronized(obj) {//object lock
                while (size == 0) {
                    System.out.println("*****************waiting to dequeue");
                    obj.wait();
                }
                item = array[head];
                array[head] = null;
                size--;
                head = (head + 1) % capacity;
                obj.notifyAll();

                return item;
            }
        }

    Runnable pro = new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<100; i++) {
                    try {
                        enqueue((T) new Integer(i));
                        System.out.println(Thread.currentThread().getName()+"enqueue"+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
     Runnable con = new Runnable() {
        @Override
        public void run() {
            for(int i=0; i<100; i++) {
                try {
                    System.out.println(Thread.currentThread().getName()+"dequeue"+dequeue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public  void runTest() throws InterruptedException {
        Thread producer = new Thread(pro);
        //Thread.sleep(10);
        Thread consumer = new Thread(con);
        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) throws InterruptedException {
        BlockingQueue<Integer> bq = new BlockingQueue(5);
        bq.runTest();
    }

}
