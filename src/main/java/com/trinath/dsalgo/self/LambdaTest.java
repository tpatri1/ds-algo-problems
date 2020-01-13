package com.trinath.dsalgo.self;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.groupingBy;

public class LambdaTest {

    interface Test{
        void apply();
        //void apply1();
    }
    //int count =0;

    public void go(Test test){
        test.apply();
        int count =0; // local variable has to be final
        // but we can use a list and get to a temp variable and set
        for(int i=0; i<5; i++){
        new Thread(()->System.out.println(count)).start();
        //Using anonymous class
        new Thread(new Runnable(){
            @Override
            public void run(){
                //count++; // cant increment as considered final
                int count  =1; //can shadow or redeclared
            }
        }).start();
        }
    }

    public static void main(String[] args) {
        new LambdaTest().go(()->System.out.println("hello lambda"));
        List<String> list = new ArrayList<>();
        list.add("trinath");
        Integer int1 = new Integer(5);

        list.add("ta");
        list.add("paa");
       System.out.println(list.stream().filter(new Predicate<String>() {
           @Override
           public boolean test(String s) {
               return s.length()>2;
           }
       }).collect(groupingBy(String::length)));

    }
}
