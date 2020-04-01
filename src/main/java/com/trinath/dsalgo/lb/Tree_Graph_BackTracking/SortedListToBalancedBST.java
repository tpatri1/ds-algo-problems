package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.ArrayList;

public class SortedListToBalancedBST {
    /**
     * Definition for singly-linked list.*/
      public static class ListNode {
          int val;
          ListNode next;
         ListNode(int x) { val = x; }
      }

/**
 * Definition for a binary tree node. */
  public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

  private static ArrayList<Integer> values = new ArrayList();
  private static void linkedList2ArrayList(ListNode head){
      while(head!=null){
          values.add(head.val);
          head = head.next;
      }
  }

  private static TreeNode convertValues2BST(int start, int end){
      if(start>end){
          return null;
      }

      int mid = (end +start)/2;
      TreeNode  node = new TreeNode(values.get(mid));

      if(start==end){
          return node;
      }
      node.left = convertValues2BST(start, mid-1);
      node.right = convertValues2BST(mid+1, end);
      return node;
  }

  public static TreeNode sortedListToBST(ListNode head) {

       linkedList2ArrayList(head);
      return convertValues2BST(0, values.size()-1);

    }

    private static void inOrderRec(TreeNode node){
        if(node==null){
            return;
        }
        inOrderRec(node.left);// recursively
        System.out.println(node.val);
        inOrderRec(node.right);
    }


    public static void main(String args[]){
      ListNode head = new ListNode(-7);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next = new ListNode(9);
        inOrderRec(sortedListToBST(head));


    }

}
