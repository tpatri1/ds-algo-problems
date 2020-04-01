package com.trinath.dsalgo.lb.Recursion_DP;
//https://leetcode.com/problems/trapping-rain-water/
public class TrapRainWater {

    static int calcRainWater_dynamic(int[] heights){
        //Bruteforce is to find leftmax and right max everytime
        int trap=0;
        //Algorithm : amount of water in an index i : min(left_max, right_max) -h[i] . Sum(water[i]) = ans
        if(heights==null || heights.length==0){
            return 0;
        }
        int size = heights.length;
        int[] h_left_max = new int[size];
        int[] h_right_max = new int[size];

       //Find left max and right max for all indices
        h_left_max[0] = heights[0];
        h_right_max[size - 1] = heights[size - 1];
        for (int i = 1; i < size; i++) {

            h_left_max[i] = Math.max(heights[i],h_left_max[i-1]);

        }
        for (int i = size-2; i >= 0; i--) {

            h_right_max[i] = Math.max(heights[i], h_right_max[i+1]);
        }
        for (int i =0; i <size; i++) {
            trap+=Math.min(h_left_max[i],h_right_max[i]) - heights[i];
        }
        return trap;
    }

    /**Alternate solution using two pointers
    Algo - move the lft pointer to right if h[left] < h[right] else otherwise move right pointer to left; what ever moving compare that with that_max and set; change answer ans+=that_max - h[i]
     */
    static int calcRainWater_2Ptrs(int[] h){
        int trap=0; // how much water?
        int size = h.length;
        int left=0,right=size-1; //pointers
        int left_max = 0;
        int right_max = 0;

        while(left<right) {// did not finish all elements
            if(h[left]<h[right]){// what ever is smaller , that moves
               if(h[left]>=left_max){// If it is bigger than left max, then you can't add to ans as imagine the next is bigger so no trap for water
                   left_max=h[left];
               }else{
                   trap+=left_max-h[left];
               }
               left++;
            }
            else{
                if(h[right]>=right_max){
                    right_max=h[right];
                }else{
                    trap+=right_max-h[right];
                }
                right--;
            }
        }

        return trap;
    }

    //Trap water using Stack

    public static void main(String args[]){
        int arr[]={0,2,0,1,4};
        System.out.println(calcRainWater_dynamic(arr));
        int arr1[]={0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(calcRainWater_dynamic(arr1));
        System.out.println(calcRainWater_2Ptrs(arr1));
    }
}
