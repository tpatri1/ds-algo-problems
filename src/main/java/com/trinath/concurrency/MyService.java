package com.trinath.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyService {
    //Stateless service is thread safe
    int count=0;
    int getCount(){
        return count;
    };
    public void service(List<Integer> input, List<Integer> output){
        output.addAll(input);
        //Qn -  why does output is always 10 + input, that is not in synchronized??
        output.add(input.get(0)+10);
        synchronized (this){
            count++;
        }

    }

    public static void main(String args[]) throws InterruptedException {
        MyService s = new MyService();

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<1000000; i++){
                    List<Integer> input = new ArrayList<>();
                    input.add(i);
                    List<Integer> output = new ArrayList<>();
                    s.service(input, output);
                    if(output.size()!=2 || output.get(1)-output.get(0)!=10){
                        System.out.println(output);
                    }
                }
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<1000000; i++){
                    List<Integer> input = new ArrayList<>();
                    input.add(i);
                    List<Integer> output = new ArrayList<>();
                    s.service(input, output);
                    if(output.size()!=2 || output.get(1)-output.get(0)!=10){
                        System.out.println(output);
                    }
                }
            }
        };
        Thread t1 = new Thread(r1);
        Thread t2  = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Done.."+ s.getCount());

    }
}
