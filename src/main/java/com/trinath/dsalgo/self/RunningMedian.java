package com.trinath.dsalgo.self;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class RunningMedian {
    private static double findMedian(int a[]) {
        double median;
        Arrays.sort(a);
        int index = a.length / 2;
        median = (a.length % 2) != 0 ? a[index] : (((double) a[index] + (double) a[index - 1])) / 2;
        return median;
    }

    private static double[] getMedian(int[] a) {
        PriorityQueue<Integer> lowers = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return -1 * a.compareTo(b);
            }
        }); // max heap
        PriorityQueue<Integer> highers = new PriorityQueue<Integer>(); //min heap
        double[] medians = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            int number = a[i];
            addNumber(lowers, highers, number);
            rebalance(lowers, highers);
            medians[i] = getMedian(lowers, highers);
        }
return medians;
    }

    private static void addNumber(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers, int number){
        if(lowers.size()==0 || number<lowers.peek()){
            lowers.add(number);
        }
        else{
            highers.add(number);
        }
    }

    private static void rebalance(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers){
        PriorityQueue<Integer> biggerHeap = lowers.size()>highers.size() ?  lowers : highers;
        PriorityQueue<Integer> smallerHeap = lowers.size()<highers.size() ? lowers : highers;
        if(biggerHeap.size()-smallerHeap.size()>=2){
            smallerHeap.add(biggerHeap.poll());
        }
    }

    private static double getMedian(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers){
        PriorityQueue<Integer> biggerHeap = lowers.size()>highers.size() ?  lowers : highers;
        PriorityQueue<Integer> smallerHeap = lowers.size()<highers.size() ? lowers : highers;

        if(biggerHeap.size()==smallerHeap.size()){
            return (biggerHeap.peek()+smallerHeap.peek())/2;
        }
        else{
            return biggerHeap.peek();
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
            // int a_new[] = new int[a_i+1];
            // for(int j=0; j<=a_i; j++){
            //     a_new[j]=a[j];
            // }
            // System.out.println(findMedian(a_new));
        }

    }
}
