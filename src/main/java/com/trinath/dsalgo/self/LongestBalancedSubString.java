package com.trinath.dsalgo.self;

import java.util.function.Function;

public class LongestBalancedSubString {
    public static int longestBalancedSubstring(String string) {
        int max = 0;
        int index = 0;
        int left =0;
        int right = 0;
        boolean leftInProgress=false, rightInProgress = false;
        while(index<string.length()){
            if(string.charAt(index)=='('){
                if(!rightInProgress){
                    max = (right < left) ? Math.max(max,2*right) : Math.max(max,2*left);
                    leftInProgress = true;
                    left++;
                    index++;
                }else{
                    //Calculate max
                    max = (right < left) ? Math.max(max,2*right) : Math.max(max,2*left);
                    left=0;
                    right=0;
                    rightInProgress = false;
                    index++;
                }
            }else if(string.charAt(index)==')'){
                if(!leftInProgress){
                    index++;
                    continue;
                }else{
                    leftInProgress = true;
                    rightInProgress = false;
                    right++;
                    index++;
                }
            }else{
                index++;
            }
        }
        return max;
    }

    public  static int  longestBalancedSubstring1(String string) {
        int max = 0;
        int index = 0;
        int left =0;
        int right = 0;
        while(index<string.length()){
            if(string.charAt(index)=='('){
                if(!isChangeDir(string, index)){
                    left++;
                    index++;
                }else{
                    //Calculate max
                    max = (right < left) ? Math.max(max,2*right) : Math.max(max,2*left);
                    left=0;
                    right=0;
                    index++;
                }
            }else if(string.charAt(index)==')'){
                if(!isChangeDir(string, index)){
                    index++;
                    continue;
                }else{
                    max = (right < left) ? Math.max(max,2*right) : Math.max(max,2*left);
                    left=0;
                    right=0;
                    index++;
                }
            }
        }
        return max;
    }

    private static boolean isChangeDir(String string, int index){
        return index+1 >= string.length() || string.charAt(index)==')' && string.charAt(index+1)=='(';
    }

    public static void main(String args[]){

        System.out.println(longestBalancedSubstring1("(())("));
        Function<Integer, Void> callback = new Function<Integer, Void>() {
            @Override
            public Void apply(Integer integer) {
                return null;
            }
        };
    }
}
