package com.trinath.dsalgo.lb.Uncategorized;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class SumOfThree {
    public static boolean findSumOfThree(int arr[], int sum) {
        // Build map
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        for (int i = 0; i < arr.length; i++) {
            if (findSumOfTwo(arr, sum, i, map)) {
                return true;
            }
        }

        return false;
    }
//Alternate 2
    public static boolean findSumOfThreeBinarySearch(int arr[], int sum){
        Arrays.sort(arr);//nlog(n)

        for(int i=0; i<arr.length; i++){
            int min =i+1;
            int max = arr.length-1;
            int mid = min+(max-min-1)/2;
            int desired = sum-arr[i]-arr[mid];
            while(min<max && max<=arr.length-1){
                if(desired==arr[min] ||desired==arr[max]){
                    return true;
                }
                else if(desired<=arr[mid] && desired>arr[min]){
                    max = mid-1;
                }
                else if(desired>arr[mid] && desired<arr[max]){
                    min = mid+1;
                }else{
                    break;
                }

            }
        }
        return false; // when for loop ends

    }

    public static boolean findSumOfTwo(int arr[], int sum, int index, Map<Integer, Integer> map) {
        for (int i = index + 1; i < arr.length; i++) {
            int desired = sum - arr[i] - arr[index];
            int matchCount = map.getOrDefault(desired, 0);
            if(desired==arr[i] || desired==arr[index]){
                if(matchCount>1) return  true;
            }
            else{
                if(matchCount>0) return  true;
            }

        }
        return false;
    }


// Using two sum ans sorted array
    private static boolean findSumOfThreeAlt3(int[] arr, int val){
        Arrays.sort(arr);
        boolean result = false;
        for(int i=0; i<arr.length; i++){
            result = findSumOfTwoSorted(arr,val-arr[i],i+1);
            if(result){
                return true;
            }
        }
        return false;
    }
    //Alternatively for sorted O(n)
    private static boolean findSumOfTwoSorted(int[] arr, int val, int start){
        for(int i=start,j=arr.length-1; i<j;){
            int sum = arr[i]+arr[j];
            if(sum==val){
                return true;
            }
            else{
                if(val>sum){
                    j--;
                }else{
                    i++;
                }

            }
        }
        return false;
    }


    public static void main(String args[]) {
        int[] arr = {3, 7, 1, 2, 8, 4, 5};
        System.out.println(findSumOfThree(arr, 21));
        System.out.println(findSumOfThreeBinarySearch(arr, 21));
        System.out.println(findSumOfThreeAlt3(arr, 21));
    }

}