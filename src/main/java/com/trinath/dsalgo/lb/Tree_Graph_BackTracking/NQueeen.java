package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueeen {
    static int solveNQueens(int n, List<List<Integer>> results){
        List<Integer> solution = new ArrayList<Integer>(n);
        for (int i = 0; i < n; ++i) {
            solution.add(-1);
        }
        nQueenRec(n,0,solution,results);
        return results.size();
    }

    //solution has last colum for the row in that iteration
    static void nQueenRec(int n, int row, List<Integer> solution,List<List<Integer>> results ){
        if(n==row){
            results.add(new ArrayList<Integer>(solution));
            return;
        }
        for(int j=0; j<n; j++){
            if(isValidMove(row,j,solution)) {
                solution.set(row, j);// row column
                nQueenRec(n, row + 1, solution, results);
            }
        }

    }

    static boolean isValidMove(int nextR, int nextC, List<Integer> solution){

        for(int i=0; i<nextR; i++ ){
            int oldR = i;
            int oldC = solution.get(i);

            int diagonalOffset = Math.abs(oldR-nextR);
            if(oldC==nextC || Math.abs(oldC-nextC)==diagonalOffset){
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]){
        List<List<Integer>> results = new ArrayList<>();
        solveNQueens(4,results);
        System.out.println(results);
    }
}
