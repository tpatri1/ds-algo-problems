package com.trinath.dsalgo.self;

import java.util.HashMap;
import java.util.Map;

public class ArrayProblems {

    static int[] findMissingDuplicate(int[] arr){
        int result[] = new int[2];
        for(int i=0; i<arr.length; i++){
            int val = Math.abs(arr[i]);
            if(arr[val-1] <0){
                result[0] = val; // duplicate
            }
            arr[val-1] = -1*arr[val-1];

        }
        for(int i=0; i<arr.length; i++){
            if(arr[i]>0 && arr[i]!=result[0] -1){
                result[i] =i+1;
            }
        }
        return result;
    }

   // 3, 4, 3, -7, 2, 1, -3, 5, 7
    static boolean ifSubArraySumZero(int[] arr){
        Map<Integer,Integer> map = new HashMap<>();
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for(int i=0; i<arr.length; i++){
            if(i>1) {
                sum[i] = sum[i-1]+ arr[i];
                if(!map.containsKey(sum[i])) {
                    map.put(sum[i], i);
                }
                else{
                    //sub array exists
                    // from get[sum[i] till i is subarray
                    return true;
                }
            }
        }
 return false;
    }
// Sort and reverse all even numbersmay work for only distinct numbers
    // Go 3 by 3 elements; after 3 it is a lesser then swap
    static int[] createWaveArray(int[] arr){
    // 1, 3, 6, 7, 8, 9, 2, 4, 7
        boolean peak = false;
        for(int i=1; i<arr.length; i++){
            if(arr[i-1]<arr[i]){ //upward slope
                if(arr[i]<arr[i+1]){
                    swapWithNext(arr, i);
                }
            }
            else{// down ward slope
                if(arr[i]>arr[i+1]){
                    swapWithNext(arr, i);
                }
            }
        }
        return arr;
    }

    static int[] swapWithNext(int[] a, int i){
        int temp = a[i];
        a[i] = a[i+1];
        a[i+1] = temp;
        return a;
    }

    static void printArray(int[] a){
        for(int i=1; i<a.length; i++){
            System.out.print(a[i]);
        }
    }

    public static void main(String args[]){
        int a[]={1, 3, 2,5,3};
        //int[] result  = findMissingDuplicate(a);
        //System.out.print(result[0]+ " "+ result[1]);

        int b[] ={1, 3, 6, 7, 8, 9, 2, 4, 7};
        int result[] =createWaveArray(b);
        printArray(result);

    }
}
