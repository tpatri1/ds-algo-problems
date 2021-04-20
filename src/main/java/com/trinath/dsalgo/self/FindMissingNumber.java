package com.trinath.dsalgo.self;

import java.util.ArrayList;
import java.util.List;

public class FindMissingNumber {
    public static int findNumber(int[] nums) {
        //1st way -ve index way
        int i=0;
        // while(i<nums.length){
        //   int index = Math.abs(nums[i])-1;
        //   if(nums[index]<0){
        //     return Math.abs(nums[i]);//alredy visited once so duplicate, ith index data is ans
        //   }
        //   nums[index]= -nums[index];
        //   i++;
        // }
        //Alternately cyclic sort
        while(i<nums.length){
            int index = nums[i]-1;
            if(nums[index]==nums[i]){
                System.out.println("i "+i+" index "+index+" num "+nums[i]);
                if(i!=index){
                    return nums[i];
                }else{
                    i++;
                }

            }else{
                System.out.println(" swap i "+i+" index "+index+" num "+nums[i]);
                swap(nums, i, index);
            }
        }
        return -1;
    }

    public static int[] findMissingAndDuplicate(int nums[]){
        int[] res = new int[2];
        int i=0;
        while(i<nums.length){
            int index = Math.abs(nums[i])-1;//1 based so..
            if(nums[index]<0 && res[0]==0){
                res[0] = Math.abs(nums[i]);//number and index are 1 apart
            }
            nums[index] = -nums[index];//mutate
            i++;
        }
        //missing
        i=0;
        while(i<nums.length){
            if(nums[i]>0){
                res[1]= i+1;
                break;
            }
            i++;
        }
        return res;
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

public static void main(String args[]){
    System.out.println(findMissingAndDuplicate(new int[]{3, 1,2,3, 6, 4}));
   // System.out.print(findNumber(new int[]{1, 4, 4, 3, 2}));
    int c = 'c';
    System.out.println("number "+c);
}
}
