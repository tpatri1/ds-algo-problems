package com.trinath.dsalgo.lb;

import java.util.Stack;

//https://leetcode.com/problems/online-stock-span/
public class StockSpan {
    static Stack mainStack = new Stack();
    static Stack tempStack = new Stack();
    static int[] calcStockSpan(int[] stocks){
        int[] result = new int[stocks.length];
        result[0] = 1;// nothing beofre it
        //int currentMaxIndex =0;
        for(int i=0; i<stocks.length; i++){
            mainStack.push(stocks[i]);
        }
        int topElem;
        int count=0;
        int index = stocks.length-1;
        //Find result
        while(!mainStack.isEmpty()){
            topElem = (int) mainStack.pop();
            //unload and load to temp stack
            while(!mainStack.isEmpty()) {
                if ((int) mainStack.peek() < topElem) {
                    tempStack.push((int) mainStack.pop());
                }
                else {
                    break;
                }
            }
                //reload to to main stack
                count = load(tempStack, mainStack);

            result[index]= ++count;

            index--;
        }
        return result;
    }

    static int load(Stack fromStack, Stack toStack){
        int count=0;
        while(!fromStack.isEmpty()){
            toStack.push(fromStack.pop());
            count++;
        }
        return count;
    }

    static void printArray(int[] arr){
        for(int i=0; i<=arr.length-1; i++){
            System.out.println(arr[i]);
        }
    }
    public static void main(String args[]){
        int[] stocks = {100, 80, 60, 70, 60, 75, 85};
        printArray(calcStockSpan(stocks));
        System.out.println("next");
        int[] stocks1 = {10, 80, 60, 70, 60, 75, 85};
        printArray(calcStockSpan(stocks1));
    }
}
