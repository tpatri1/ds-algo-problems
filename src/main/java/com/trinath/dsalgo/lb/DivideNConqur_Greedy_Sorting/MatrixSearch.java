package com.trinath.dsalgo.lb.DivideNConqur_Greedy_Sorting;

class IntPair extends Pair<Integer, Integer> {
    public IntPair(int first, int second) {
        super(first, second);
    }
}

class Pair<X, Y> {
    public X first;
    public Y second;
    public Pair(X first, Y second) {
        this.first = first;
        this.second = second;
    }
}

class SearchMatrix{
    public static IntPair searchInMatrix(int matrix[][], int value) {
        // int rMin = 0;
        // int cMin =0;
        // int rMax = matrix.length;
        // int cMax = matrix[0].length;
        // int rMid = (rMax-rMin)/2;
        // int cMid = (cMax-cMin)/2;
        return binarySearch(matrix,value,0,0,matrix.length, matrix[0].length);
        //return new IntPair(-1, -1);
    }

    private static IntPair binarySearch(int matrix[][], int val,int rStart,int cStart, int rEnd, int cEnd){
        int rMid = (rEnd-rStart)/2;
        int cMid = (cEnd-cEnd)/2;
        if(matrix[rMid][cMid]==val){
            return new IntPair(rMid,cMid);
        }
        if(matrix[rMid][cMid]<val){
             binarySearch(matrix,val,rStart,cMid+1,rMid, cEnd);
             binarySearch(matrix,val,rMid+1,cStart,rEnd, cMid);
             binarySearch(matrix,val,rMid+1,cMid+1,rEnd, cEnd);
        }
        else{
            return binarySearch(matrix,val,rStart,cStart,rMid, cMid);
        }
        return new IntPair(-1,-1);
    }

    //Alternate
    public static IntPair searchInMatrixAlt(int matrix[][], int value) {
        int R  = matrix.length;
        int C = matrix[0].length;
        int i=0, j=C-1;
        while(i<R && j>=0){//start from 1st row last column
            if(matrix[i][j]==value){
                return new IntPair(i,j);
            }
            if(matrix[i][j]<value){
                i++;
            }else {
                j--;
            }
        }
        return new IntPair(-1,-1);
    }
    public static void main(String[] args) {
        int [] [] matrix = new int [] [] {
                {2, 4, 9, 13, 15},
                {3, 5, 11, 18, 22},
                {6, 8, 16, 21, 28},
                {9, 11, 20, 25, 31},
        };

        IntPair val_loc = searchInMatrixAlt(matrix, 8);
        System.out.println("Verifying at " + Integer.toString(val_loc.first) + "," +
                Integer.toString(val_loc.second) + ": " + Integer.toString(matrix[val_loc.first][val_loc.second]));
    }


}