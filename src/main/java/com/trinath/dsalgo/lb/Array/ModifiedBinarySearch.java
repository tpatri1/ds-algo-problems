package com.trinath.dsalgo.lb.Array;

public class ModifiedBinarySearch {

    static int binary_search_rotated(int[] arr,int key) {
        int  low =0;
        int high = arr.length-1;
        int mid =(low+high)/2;
        while(low<=high){
            if(arr[mid]==key){
                return mid;
            }else{
                if(key>arr[mid]){//search right half in normal BST
                    if(arr[high]>=key){// >= needed
                        low=mid+1;
                    }else{
                        high = mid-1;
                    }
                }else{//search left half in normal BST
                    if(arr[low]<=key){
                        high = mid-1;
                    }else{
                        low=mid+1;
                    }
                }
            }
            mid =low+high/2;
        }
        return -1;
    }

    static int findLowIndexOfASortedArray(int[] arr,int key){
        int low = 0; int high = arr.length-1;
        while(low<=high){
           int mid = (low+high)/2;
            if(arr[mid]==key && (mid==0 || mid-1>=0 && arr[mid-1]<key)){
                return mid;
            }else{
                if(arr[mid]>=key && (mid-1>=0 && arr[mid-1]>=key)){
                    high= mid-1;
                }else{
                    low= mid+1;
                }
            }
        }
        return -1;
    }
    static int findLowIndex (int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int mid = high / 2;

        while (low <= high) {

            int midElem = arr[mid];

            if (midElem < key) {//strictly less than is the going to push always to left half, for high index this will be <= and low = mid+1
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }

            mid = low + (high - low) / 2;
        }

        if (low < arr.length && arr[low] == key) {
            return low;
        }

        return -1;
    }

    public static void main(String args[]){
        System.out.println(findLowIndex(new int[]{1,1,2,3,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5},3));
        //System.out.println(findLowIndexOfASortedArray(new int[]{1,1,2,2,2,3,3,3,3,4,4,4,4,4,5,5,5,5,5},1));
        System.out.println(binary_search_rotated(new int[]{4,5,6,1,2,3},5));
        System.out.println(binary_search_rotated(new int[]{10,11,12,13,3,4,5,6},11));
    }
}
