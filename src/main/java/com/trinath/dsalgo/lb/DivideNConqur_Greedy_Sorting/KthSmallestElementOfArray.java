package com.trinath.dsalgo.lb.DivideNConqur_Greedy_Sorting;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallestElementOfArray {


    //O(log(n))
    private static int getKthSmallestNumber(int[] arr, int k){

        int start=0;
        int end =arr.length-1;
        int pivot = partition(arr,start, end);
        while(k!=pivot){
            if(pivot<k){
                start =pivot+1;
            }
            else{
                end=pivot-1;
            }
            pivot = partition(arr,start, end);
        }
        return arr[k];
    }
    private static int partition(int[] arr, int start, int end){
        int pivot = start;
        while(start<=end){
            if(arr[start]>arr[pivot] && arr[end]<arr[pivot]){
                //swap
                swap(start,end,arr);

            }
            else{ //Just move pointer if pivot is in correct position
                if(arr[start]<=arr[pivot]){
                    start++;
                }
                if(arr[end]>=arr[pivot]){
                    end--;
                }
            }
        }
        swap(pivot,end,arr);
        return end;
    }

    public static void swap(int i, int j, int[] arr){
        if(i!=j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }

    //Use Max heap for k elemets and the top gives answer
    private static int getKthSmallaestElementHeap(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;//max heap, by default mean heap
            }
        });
        int index=0;
        while(index<arr.length){
            if(k>=0) {
                pq.add(arr[index]);
                k--;
            }
            else{
                if(arr[index]<pq.peek()){
                    pq.poll();
                    pq.add(arr[index]);
                }
                //else do nothing
            }
            index++;
        }
        return pq.peek();
    }

    public static void main(String args[]){
        int[] input={3, 5, 11, 2,1, 7, 10};
        System.out.println(getKthSmallestNumber(input,2));// k is zero based
        System.out.println(getKthSmallaestElementHeap(input,2));// k is zero based

    }
}
