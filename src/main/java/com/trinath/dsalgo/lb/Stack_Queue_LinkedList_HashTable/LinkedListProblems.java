package com.trinath.dsalgo.lb.Stack_Queue_LinkedList_HashTable;
//TODO:: Not tested
public class LinkedListProblems {
    public static class Node{
        public int val;
        public Node next;

        public Node(int val){
            this.val = val;
        }
    }
    public Node head;


    Node addNode(int i){
        Node node = new Node(i);
        Node ptr = head;
        if(head==null){
            head = node;
            return head;
        }
        while(head.next!=null){

        }
        return head;
    }
//TODO::Test pending
    Node reverseAlternateChunks(Node head, int k,boolean flag) {
        if (head == null) {
            return null;
        }
        Node first = head;
        Node curr = head;
        int count = k - 1;
        while (curr.next != null && count > 0) {
            head = head.next;
            count--;
        }
        Node returnVal = reverseAlternateChunks(curr.next, k, !flag);

        if(flag){
            curr.next = null;
            curr =reverse(first);
            first.next = returnVal;
            return curr;
        }
        curr.next = returnVal;
        return curr;
    }

    Node reverse(Node head){
        if(head==null || head.next==null){
            return head;
        }
        Node result = reverse(head.next);
        head.next.next = head;
        head.next=null;
        return result;
    }
    
    public static void main(String args[]){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);


    }
}
