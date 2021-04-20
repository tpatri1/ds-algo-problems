package com.trinath.dsalgo.self;

import java.util.ArrayList;
import java.util.List;

public class RightSmallerThan {
    public static List<Integer> rightSmallerThan(List<Integer> array) {
        //Program program = new Program();
        if (array.size() == 0)
            return new ArrayList<Integer>();

        List<Integer> result = new ArrayList<Integer>(array);
        int lastIdx = array.size() - 1;
        SpecialBST bst = new SpecialBST(array.get(lastIdx));// populatee from last
        result.set(lastIdx, 0);
        for (int i = array.size() - 2; i >= 0; i++) {
            bst.insert(array.get(i), i, result);
        }
        return result;
    }

    static class SpecialBST {
        public int value;
        public SpecialBST left;
        public SpecialBST right;
        public int leftSubTreeSize = 0;

        public SpecialBST(int value) {
            this.value = value;
            this.leftSubTreeSize = 0;
            left = null;
            right = null;
        }

        public void insert(int value, int idx, List<Integer> rightSmallerCounts) {
            insertHelper(value, idx, rightSmallerCounts, 0);
        }

        public void insertHelper(int value, int idx, List<Integer> rightSmallerCounts, int numSmallerAtInsertTime) {
            if (value < this.value) {
                leftSubTreeSize++;
                if (left == null) {
                    left = new SpecialBST(value);
                    rightSmallerCounts.set(idx, numSmallerAtInsertTime);
                } else {
                    left.insertHelper(value, idx, rightSmallerCounts, numSmallerAtInsertTime);
                }
            } else {
                numSmallerAtInsertTime += leftSubTreeSize;
                if (value > this.value) {
                    numSmallerAtInsertTime++;
                }
                if (right == null) {
                    right = new SpecialBST(value);
                    rightSmallerCounts.set(idx, numSmallerAtInsertTime);
                } else {
                    right.insertHelper(value, idx, rightSmallerCounts, numSmallerAtInsertTime);
                }
            }

        }

    }
}
