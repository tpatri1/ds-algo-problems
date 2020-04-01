package com.trinath.dsalgo.lb.Uncategorized;

//with out using any extra memory
class Swap3Numbers {
    static int[] swapNumbers(int[] numbers){
        int[] result = new int[3];
        numbers[0] = numbers[0]+numbers[1]+numbers[2];
        numbers[1] = numbers[0] - (numbers[1]+numbers[2]);
        numbers[2] = numbers[0] - (numbers[1]+numbers[2]);
        numbers[0] = numbers[0] - (numbers[1]+numbers[2]);

        return numbers;
    }

    static void  printArray(int[] nums){
        for(int i=0; i<nums.length; i++){
            System.out.println(nums[i]);
        }
    }
    public static void main(String args[]){
        int[] nums = {10,20,30};
        printArray(swapNumbers(nums));

    }
}