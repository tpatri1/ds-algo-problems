package com.trinath.dsalgo.lb.Recursion_DP;

import java.util.ArrayList;

public class BinaryTreeToDLL {
    public static BinaryTree flattenBinaryTree(BinaryTree root) {
        ArrayList<BinaryTree> list = new ArrayList<BinaryTree>();
        list.add(null);
        list.add(null);
        BinaryTree prev = null;
        BinaryTree head  = null;
        dllRec(root, prev, head);//JAva is Pass by value
        return head;
    }

//    private static void dllRec(BinaryTree root, ArrayList<BinaryTree> list){
//        if(root==null){
//            return;
//        }
//        dllRec(root.left,list);
//        if(list.get(0)==null){
//            list.set(1,root);// left most node
//        }else{
//            root.left = list.get(0);
//            list.get(0).right= root;
//        }
//        list.set(0,root);
//        dllRec(root.right,list);
//    }
    private static void dllRec(BinaryTree root, BinaryTree prev, BinaryTree head){
        if(root==null){
            return;
        }
        dllRec(root.left,prev, head);
        if(prev==null){
            head = root;// left most node
        }else{
            root.left = prev;
            prev.right= root;
        }
       prev= root;
        dllRec(root.right,prev, head);
    }

    // This is the class of the input root. Do not edit it.
    static class BinaryTree {
        int value;
        BinaryTree left = null;
        BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
    public static void main(String args[]){
        BinaryTree binaryTree  = new BinaryTree(1);
        binaryTree.left = new BinaryTree(2);
        binaryTree.right = new BinaryTree(3);
        //DLL 2-> 1-> 3
        BinaryTree dll = flattenBinaryTree( binaryTree);
        System.out.print("head"+dll.value);

    }
}
