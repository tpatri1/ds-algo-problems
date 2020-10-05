package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.ArrayList;

public class AllbalancedBraces {

        static ArrayList<ArrayList<Character>> printAllBraces(int n) {
            ArrayList<ArrayList<Character>> result = new ArrayList<ArrayList<Character>>();
            ArrayList<Character> output = new ArrayList<Character>();
            printAllBracesRec(n,0,0,result,output);
            return result;
        }

        static void printAllBracesRec(int n, int l, int r,ArrayList<ArrayList<Character>> result, ArrayList<Character> output){
            if(l==n && r==n){
                result.add((ArrayList)output.clone());

            }
            if(l<n){
                output.add('{');
                printAllBracesRec(n, l+1, r, result, output);
                output.remove(output.size() - 1);
            }
            if(r<l){
                output.add('}');
                printAllBracesRec(n, l, r+1, result, output);
                output.remove(output.size() - 1);
            }
        }

        public static void main(String args[]){
            System.out.println(printAllBraces(3));
        }

}
