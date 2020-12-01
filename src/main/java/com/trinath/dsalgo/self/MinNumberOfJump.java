package com.trinath.dsalgo.self;

public class MinNumberOfJump {
    public static int minNumberOfJumps(int[] array) {
        if(array.length==1){
            return 0;
        }
        int jump = 1;
        int currIndex = 0;
        int nextIndex = currIndex+ array[currIndex];
        int steps =0;
        System.out.print("Jump Index "+currIndex);
        for(int i=1; i<array.length; i++){
            steps++;
            if(nextIndex>=array.length-1){
                return jump;
            }
            //i+array[i]-1 => current iteration index + jump steps - 1( as starting early)
            if(i+array[i]-1>nextIndex || steps >= nextIndex - currIndex){ // total steps for the jump = nextIndex - currIndex
                jump++;
                currIndex = i;
                nextIndex = currIndex+ array[currIndex];
                steps =0;

            }
        }
        System.out.println("Jump needed"+ jump);
        return jump;

    }

    public static void main(String args[]){
        int[] arr = {2, 1, 2, 3, 1, 1, 1};
        System.out.println(minNumberOfJumps(arr));
    }
}
