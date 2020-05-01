package com.trinath.dsalgo.lb.DivideNConqur_Greedy_Sorting;

public class MedianOf2SortedArrays {

    //brute froce O(n)
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLen = nums1.length+nums2.length;
        int mIndex = totalLen/2;

        //merge until mIndex
        int k=0;
        int i=0;
        int j=0;
        int[] arr = new int[totalLen];//TODO:: Can replace with two variables to make space complexity reduced to O(1)
        while(k<=mIndex){
            if(i==nums1.length){
                arr[k] = nums2[j];
                j++;
                k++;
            }
            else if(j==nums2.length || nums1[i]<=nums2[j]){
                arr[k] = nums1[i];
                i++;
                k++;
            }else{
                arr[k] = nums2[j];
                j++;
                k++;
            }
        }
        if(totalLen%2==1){
            return arr[mIndex];
        }
        else{
            return (Double.valueOf(arr[mIndex]+arr[mIndex-1]))/2;
        }
    }

    // This is a divide and concur algo

    public static void main(String args[]){
        int[] a= {1,2};
        int[] b={3,4};
        System.out.println(findMedianSortedArrays(a,b));
    }
}
