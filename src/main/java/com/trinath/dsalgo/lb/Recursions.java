package com.trinath.dsalgo.lb;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Recursions {
    /**
     * A man puts a pair of rabbit in a closed room. Each pair produce a pair every month from second month onward(let's say they take a month
     * to mature and from second months starts produce and none of the rabbit dies).
     * How many rabbits will be in that room after a year.
     * @param n number of months
     * @return number of pairs
     */
    private static Map<Integer,Integer> cache = new HashMap<>();
    private static int fib(int n){
        if(n<2){
            return 1;
        }
        else{
            if(cache.containsKey(n)){
                return cache.get(n);
            }
            int result =  fib(n-1)+fib(n-2); // make O(2^n) as calling twice each time; but the actual complexity is : > O(2^(n/2)) for right most edge goes to n/2 levels & < O(2^n) for left edge goes to n levels
            cache.put(n, result);
            return result;
        }
    }

    /**
     *Every step n-> 2 * n-1 so O(n) = 2^0 +2^1+2^3... 2^(n-1) = 2^n - 1 ~ O(2^n) GP series
     * @param height - size of source stack
     * @param src - intially all disks are here in a sorted manner
     * @param dest -  finally all disks wiill be moved here
     * @param temp - Used as a auxulary stack for movement for intermdiate moving for height-1 disks so that last disc can be moved just like that
     */
    private static void towerOfHanoi(int height,  Stack src, Stack dest, Stack temp){
        if(height==1){
            System.out.println("Move disk from source: "+src+" to dest : "+dest); // Bottom most disk moved from src to dest
        }
        else{
            towerOfHanoi(height-1, src,temp,dest); // Move to temp and use dest as auxulary
            System.out.println("Move disk from source: "+src+" to temp : "+temp); // height-1 disks moved to temp. So that Bottom most disk moved from src to dest beofre proceeding Move from temp to dest
            towerOfHanoi(height-1, temp,dest,src); // Move  temp to dest and use src as auxulary
            //System.out.println("Move disk from temp: "+temp+" to dest : "+dest); // height-1 disks moved.
        }
    }

    public static void main(String args[]){
        long startTs = System.nanoTime();
        int fib1 = fib(20);
        long endTs = System.nanoTime();
        System.out.println( "Result : "+fib1+" time taken : ");
        System.out.println( endTs-startTs);
        Stack src = new Stack();
        src.push(9);
        src.push(7);
        src.push(5);
        src.push(3);
        src.push(1);
        towerOfHanoi(src.size(),src,new Stack(), new Stack());
    }


}
