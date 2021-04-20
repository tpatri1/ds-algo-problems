package com.trinath.dsalgo.lb.Recursion_DP;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

class SumOfPathNumbers {
    public static int findSumOfPathNumbers(TreeNode root) {
        // TODO: Write your code here
        int sum=0;
        int sum1=  findSumPathRec(root, 0);
        System.out.println(sum);
        return sum;
    }

    private static int findSumPathRec(TreeNode node, int sum){
        if(node==null){
            return 0;
        }
        sum =10*sum+node.val;
        if(node.left==null && node.right==null){
            return sum;

        }
         sum =  findSumPathRec(node.left, sum) + findSumPathRec(node.right, sum);
        System.out.println(" in recursion "+sum);
        return sum;
    }

    private static int convert(List<Integer> path){
        int sum= 0;
        int size = path.size();
        for(int i=size-1; i>=0; i--){
            sum+=path.get(size-i-1)*Math.pow(10,i);
        }
        return sum;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("sravan");
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<list.size(); i++){
            sb.append("\"").append(list.get(i)).append("\"").append(" OR ");
            if(i==list.size()-1){
                sb.replace(sb.lastIndexOf("OR"),sb.length()-1,"");
            }
        }
        System.out.println(" string "+sb.toString().trim());

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
        System.out.println("Total Sum of Path Numbers: " + SumOfPathNumbers.findSumOfPathNumbers(root));
    }
}
