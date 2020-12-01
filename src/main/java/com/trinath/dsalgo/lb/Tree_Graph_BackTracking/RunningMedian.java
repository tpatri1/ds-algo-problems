package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.Comparator;
import java.util.PriorityQueue;

 class RunningMedian {
    static double median = 0;
    static PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    static PriorityQueue<Integer> maxHeap= new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;//-ve is high priority than 0 than 1
        }
    });
    static boolean odd = true;

    public static void insert(int number) {

        if(odd){
           minHeap.add(number);
           median =  Double.valueOf((Integer)minHeap.peek());
           odd = !odd;
        }else{
            Integer num = (Integer) minHeap.poll();
            maxHeap.add(num);
            minHeap.add(number);
            median = Double.valueOf((Integer)minHeap.peek()+(Integer)maxHeap.peek())/2;
            odd = !odd;
        }

    }
     public static void insertAlt(int number) {

         if(maxHeap.peek()==null){
             maxHeap.add(number); //add to lower half
         }
         else if(number>=maxHeap.peek()){
             minHeap.add(number);
         }
         else{
             maxHeap.add(number);
         }
         while(minHeap.size()>maxHeap.size()+1){
             maxHeap.add(minHeap.poll());
         }
         while(maxHeap.size()>minHeap.size()+1){
             minHeap.add(maxHeap.poll());
         }

     }

    public static double getMedian() {
        if((minHeap.size()+maxHeap.size())%2==1){
            if(minHeap.size()>maxHeap.size()){
                return  Double.valueOf(minHeap.peek());
            }else{
                return Double.valueOf(maxHeap.peek());
            }
        }else{
            System.out.println(minHeap);
            System.out.println(maxHeap);
            return Double.valueOf((Integer)minHeap.peek()+(Integer)maxHeap.peek())/2;
        }
    }

    public static void main(String args[]){
        insertAlt(5);

        insertAlt(10);
        System.out.println("size of minHeap"+minHeap.size()+"size of max"+maxHeap.size()+" median "+ getMedian());
        insertAlt(100);

        insertAlt(1000);
        insertAlt(1010);
        insertAlt(1020);
        System.out.println("size of minHeap"+minHeap.size()+"size of max"+maxHeap.size()+" median "+ getMedian());
    }
}