package com.trinath.dsalgo;

import java.util.PriorityQueue;

public class Median {
    //n=size of array=Odd numbers - middle number; if Even -> n/2 th index in a sorted array, irrespective of duplicates
    //we are solving kth smallest element in the array, 1<k<n-1

//    private static int kthSmallestElem(int a[],int k){ //k-1 element in sorted array; quick sort
//
//       return sort_helper(a,k,0,a.length-1);
//
//    }
//
//    private  static int sort_helper(int[] a,int k, int low, int high){
//        int p = partition(a, low,high); // index of pivort
//        if(p==k-1){
//            return a[p];
//        }
//        if(p<k-1){
//            sort_helper(a,k,p+1,high);
//        }
//        else{
//            sort_helper(a,k,low,p-1);
//        }
//    }
//
//    private static int partition(int[] arr, int low, int high){// gives the right poisition index
//
//    }
// Find kth element in a stream of array as we aee adding
    private static int kthLargestElemInStream(int[] a,int k){
        PriorityQueue<Integer> pq = new PriorityQueue();
        for(int i=0; i<k; i++) {
            pq.add(Integer.valueOf(a[i]));
        }
        for(int i=k; i<a.length-1; i++){
            //if a[i] > pq.peek()
            pq.add(Integer.valueOf(a[i]));
            pq.remove();
        }
        return pq.peek();

    }


}
