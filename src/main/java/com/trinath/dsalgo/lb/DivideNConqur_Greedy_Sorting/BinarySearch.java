package com.trinath.dsalgo.lb.DivideNConqur_Greedy_Sorting;

public class BinarySearch {
    static int binarySearch(int[] arr, int k){
        if(arr.length==1 && arr[0]==k){
            return 0;
        }else if(arr.length==2){
            if(arr[0]==k) return 0;
            if(arr[1]==k) return 1;
        }else{
            return bSearchRec(arr,  k, 0, arr.length-1);
        }
        return -1;
    }

    static int bSearchRec(int[] arr, int k, int s, int e){
        if(s>e){
            return -1;
        }
        int mid = (s+e)/2;
        //binary search no repeat
        if(arr[mid]==k){
            return mid;
        }
        if(arr[mid]>k){
           return bSearchRec(arr, k, s, mid-1);
        }else{
           return  bSearchRec(arr, k, mid+1,e);
        }
    }
    static int bSearchRecFirstIndex(int[] arr, int k, int s, int e){
        if(s>e){
            return -1;
        }
        int mid = (s+e)/2;

        //first index
        if(mid==0 || arr[mid-1]<k && arr[mid]==k){ // for last index mid==arr.length-1 || arr[mid+1]>k && arr[mid]==k
            return mid;
        }

        if(arr[mid]<=k){
            return bSearchRec(arr, k, s, mid-1);
        }else{
            return  bSearchRec(arr, k, mid+1,e);
        }
    }

    static int bSearchRecLastIndex(int[] arr, int k, int s, int e){
        if(s>e){
            return -1;
        }
        int mid = (s+e)/2;
        if(mid==arr.length-1 || arr[mid+1]>k && arr[mid]==k){
            return mid;
        }
        if(arr[mid]>k){
            return bSearchRec(arr, k, s, mid-1);
        }else{
            return  bSearchRec(arr, k, mid+1,e);
        }
    }

    static int bitonicIndex(int[] arr, int start, int end){
        if(end>start) return -1;
        int mid  = (start+end)/2;
        if(arr[mid]>arr[mid+1] &&arr[mid]>arr[mid-1] ){
            return mid;
        }
        if(arr[mid]<arr[mid-1]){//arr[mid]>arr[mid+1] &&
            return bitonicIndex(arr, mid+1,end);
        }else{
            return  bitonicIndex(arr,  start,mid-1);
        }
    }

    private static int findPivot(int[] arr, int start, int end){
        if(start>end){
            return -1;
        }
        int mid = (start+end)/2;
        System.out.println(mid);
        if((mid-1>=0 && mid+1<arr.length && arr[mid]>arr[mid-1] && arr[mid]>arr[mid+1])
                || (mid==0 && arr[mid]>arr[mid+1]) || (mid==arr.length-1 && arr[mid]>arr[mid-1])){
            return mid;
        }else if (mid > start && arr[mid] < arr[mid - 1])
            return (mid - 1);
        else if(arr[mid]>arr[start] && arr[mid]>arr[end]){//right
            return findPivot(arr, mid+1, end);
        }else{                                            //left
            return findPivot(arr, start, mid-1);
        }
        //return -1;
    }
    public static int rotatedArraySearch(int[] array, int target) {
        int pivot = findPivot(array, 0, array.length-1);
        if(pivot!=-1) {
            if (array[pivot] == target) {
                return pivot;
            } else if (target > array[array.length - 1]) {//in left half
                return bSearchRec(array, target, 0, pivot - 1);
            } else {
                return bSearchRec(array, target, pivot + 1, array.length - 1);
            }
        }else{
            return bSearchRec(array, target, 0, array.length - 1);
        }
    }


    public static void main(String args[]){

        int arr[] ={1,2,3,3,3,3,3,4,5,6,7};
        int arr2[] ={1,2,3,4,5,6,7};
        System.out.println(binarySearch(arr2,7));
        System.out.println(bSearchRecFirstIndex(arr,3, 0, arr.length-1));
        System.out.println(bSearchRecLastIndex(arr,3, 0, arr.length-1));
        
        int arr1[] ={1,2,3,4,5,6,7,6,5};
        //System.out.println(bitonicIndex(arr1,0,arr1.length-1));

        //int[] arrRotated = {4,5,6,7,8,9,10,1,2,3};// rotated 3 times, find the pivot element and apply binary search in two arrays
        int[] arrRotated ={33, 37, 45, 61, 71, 72, 73, 355, 0, 1, 21};
        System.out.println("Pivot "+findPivot(arrRotated,0,arrRotated.length-1));
    }
}
