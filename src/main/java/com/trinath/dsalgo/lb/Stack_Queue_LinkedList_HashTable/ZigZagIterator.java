package com.trinath.dsalgo.lb.Stack_Queue_LinkedList_HashTable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class ZigZagIterator {
    Vector<Integer> v1, v2;
    //For 2nd type
    Queue queue;
    int i, j;
    public ZigZagIterator(Vector v1, Vector v2){
        this.v1 = v1;
        this.v2 = v2;
        i=0;
        j=0;
    }
    public ZigZagIterator(Vector v1, Vector v2,boolean isQueue){
        this.queue = new LinkedList();
        this.v1 = v1;
        this.v2 = v2;
        if(isQueue && !v1.isEmpty()) queue.add(v1.iterator()); // first v1
        if(isQueue && !v2.isEmpty()) queue.add(v2.iterator()); // second v2 in the order
    }

    private int next(){
        if(i<v1.size() && j<v2.size()) {
            if (i % 2 == j%2) {
                return v1.get(i++);
            } else {
                return v2.get(j++);
            }
        }
        else if(i==v1.size()){
            return v2.get(j++);
        }
        else{
            return v1.get(i++);
        }
    }

    private int next1(){

           Iterator iterator = (Iterator) queue.poll(); // like pop in stack
            int i= (int) iterator.next();
            if(iterator.hasNext()) queue.add(iterator); // so that next time other will be at front/head to be polled if it has elements
        return i;
    }

    public boolean hasNext1() {
        return !queue.isEmpty(); // when there is no element no add will happen
    }

    private boolean hasNext(){
        return i<v1.size() || j<v2.size();
    }

    public static void main(String  ARGS[]){
        Vector vector1 = new Vector();
        vector1.add(1);
        vector1.add(3);
        vector1.add(5);
        vector1.add(7);

        Vector vector2 = new Vector();
        vector2.add(2);
        vector2.add(4);
        vector2.add(6);
        vector2.add(8);
        vector2.add(12);

        ZigZagIterator zigzag = new ZigZagIterator(vector1,vector2);

        while(zigzag.hasNext()){
            System.out.println(zigzag.next());
        }
        System.out.println(">>>>>");

        ZigZagIterator zigzag1 = new ZigZagIterator(vector1,vector2,true);

        while(zigzag1.hasNext1()){
            System.out.println(zigzag1.next1());
        }
    }
}
