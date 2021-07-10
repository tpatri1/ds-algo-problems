package com.trinath.dsalgo.self;

import java.util.*;

class Element{
    int num;
    int freq;
    int seq;

    public Element(int num, int freq, int seq){
        this.num = num;
        this.freq = freq;
        this.seq = seq;

    }

}
public class DataStream {
    Map<Integer, Integer> freqMap = new HashMap<>();
    List<Integer> list =  new ArrayList<>();
    int sequence = 0;
    PriorityQueue<Element> minHeap = new PriorityQueue<Element>((e1, e2)->{
        if(e1.freq!=e2.freq){
            return e1.freq-e2.freq;
        }else{
            return e1.seq - e2.seq;
        }
    });


    public DataStream(){
        // do intialization if necessary
    }
    /**
     * @param num: next number in stream
     * @return: nothing
     */
    public void add(int num) {
        freqMap.put(num, freqMap.getOrDefault(num, 0)+1);
        if(freqMap.get(num)==1){
            list.add(num);
        }
        //minHeap.add(new Element(num, freqMap.get(num), ++sequence));
    }

    /**
     * @return: the first unique number in stream
     */
    public int firstUnique() {
        if(list.size()>0)
            return list.get(0);
         else
            return -1;
        }

    public static void main(String args[]){
//        add(1) -> 1
//        add(2) -> 1, 2
//        firstUnique() -> 1
//        add(1) -> 1, 2, 1
//        firstUnique() -> 2
        DataStream ds = new DataStream();
        ds.add(1);
        ds.add(2);
        System.out.println(ds.firstUnique());
        ds.add(1);
        System.out.println(ds.firstUnique());
    }
    }




