package com.trinath.dsalgo.lb.Recursion_DP;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GenerateParentheses {

    public static List<String> generateValidParentheses(int num){
        List<String> result = new ArrayList<String>();
        Queue<ParenthesesString> permutations = new LinkedList<ParenthesesString>();
        permutations.add(new ParenthesesString("",0,0));
        while(!permutations.isEmpty()){
            ParenthesesString paren = permutations.poll();
            if(paren.openCount==num && paren.closeCount==num){
                result.add(paren.str);
                //System.out.println(paren.str.toString());
            }
            if(paren.openCount<num){
                permutations.add(new ParenthesesString(paren.str+"(",paren.openCount+1,paren.closeCount));
            }
            if(paren.closeCount< paren.openCount){
                permutations.add(new ParenthesesString(paren.str+")",paren.openCount,paren.closeCount+1));
            }
        }

        return result;
    }

    public static void main(String args[]){

        List<String> strings = generateValidParentheses(4);
        strings.stream().forEach(l->System.out.println(l));
    }
}
class ParenthesesString {
    String str;
    int openCount; // open parentheses count
    int closeCount; // close parentheses count

    public ParenthesesString(String s, int openCount, int closeCount) {
        str = s;
        this.openCount = openCount;
        this.closeCount = closeCount;
    }


}
