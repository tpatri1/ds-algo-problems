package com.trinath.dsalgo.lb.DivideNConqur_Greedy_Sorting;

public class BinarySearchRange {
    public static int[] searchForRange(int[] array, int target) {
        int result[] = {-1,-1};
        searchForRangeHelper(array, target, 0, array.length-1, result, true);
        searchForRangeHelper(array, target, 0, array.length-1, result, false);
        return result;
    }

    public static void searchForRangeHelper(int[] array, int target, int left, int right, int[] result, boolean goLeft){
        if(left>right){
            return ;
        }
        int mid = (left+right)/2;
        if(array[mid] < target){
            searchForRangeHelper(array, target, mid+1, right,result, goLeft);
        }else if(array[mid]>target){
            searchForRangeHelper(array, target, left, mid-1,result, goLeft);
        }
        else{
            if(goLeft){
                if(mid==0 || array[mid - 1]!=target){
                    result[0] = mid;
                }else{
                    searchForRangeHelper(array, target, left, mid-1,result,  goLeft);
                }
            }else{
                if(mid==array.length-1 || array[mid+1] != target){
                    result[1] = mid;
                }else{
                    searchForRangeHelper(array, target, mid+1,right,result, goLeft);
                }
            }
        }
    }

    public static void main(String args[]){
        searchForRange(new int[] {0,1,21,33,45,45,45,45,45,45,61,71,73},45);
    }
}
