package com.trinath.dsalgo.lb.Array;

import java.util.Stack;

public class ShadowSpan {

    private static int[] shadowSpan(int a[]){
        int[] res = new int[a.length];
        Stack<Pair> stack = new Stack();
        stack.push(new Pair(a[0],0));
        res[0] = 1;
        for(int i=1; i<a.length; i++){
            if(a[i] < stack.peek().first){
                //int idx = i - stack.peek().second;
                stack.push(new Pair(a[i],i));
                res[i] = 1;
            }else{
                while(!stack.isEmpty() && a[i]>stack.peek().first){
                    stack.pop();
                }
                res[i] = i-stack.peek().second;
            }
        }
    return res;

    }

    private static int[] shadowSpanAlt(int a[]){
        int[] res = new int[a.length];
        Stack<Integer> stack = new Stack();
        stack.push(0);//index
        res[0] = 1;
        for(int i=1; i<a.length; i++){
            if(a[i] < a[stack.peek()]){
                res[i] = 1;
            }else{
                while(!stack.isEmpty() && a[i]>a[stack.peek()]){
                    stack.pop();
                }
                res[i] = i-stack.peek();
            }
            stack.push(i);
        }
        return res;

    }

    public static void main(String args[]){
        int[] a= {100,80,60,70,80,50,90};
        int[] res = shadowSpan(a);
        int[] res1 = shadowSpanAlt(a);
        for(int i=0; i<a.length; i++){
            System.out.print(","+res[i]);
        }
        System.out.println("Alt ");
        for(int i=0; i<a.length; i++){
            System.out.print(","+res1[i]);
        }
    }
}

class Pair{
    int first;
    int second;
    Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
    int getFirst(){
        return first;
    }
    int getSecond(){
        return second;
    }

}
