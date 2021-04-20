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
            curr = reverseRec(first);
            first.next = returnVal;
            return curr;
        }
        curr.next = returnVal;
        return curr;
    }

    static Node reverseRec(Node head){
        if(head==null || head.next==null){
            return head;
        }
        Node result = reverseRec(head.next);
        head.next.next = head;
        head.next=null;
        return result;
    }
//    //Not working as expected
//    static Node reverseAlt(Node head){
//        if(head==null || head.next==null){
//            return head;
//        }
//
//        Node prev =  head;
//        Node result = reverse(head.next);
//        head.next = prev;
//        prev.next = null;
//        return result;
//    }

    private static void printLL(Node head){
        System.out.println("Printing LL");
        while(head!=null){
            System.out.println(head.val);
            head= head.next;
        }
    }

    public static Node reverseEvenNodes(Node head) {
        //TODO: Write - Your - Code
        Node odds = head;
        Node evens = null;
        Node oddCurr = head;

        Node curr = head.next;
        Node evenCurr = curr;
        boolean isOdd = false;
        while (curr != null) {
            if (isOdd) {
                oddCurr.next = curr;
                oddCurr = oddCurr.next;
                isOdd = !isOdd;
            } else {
                if (evens == null) {
                    evens = curr;
                } else {
                    evenCurr.next = curr;
                    evenCurr = evenCurr.next;
                }
                isOdd = !isOdd;
            }
            curr = curr.next;
        }
        evenCurr.next = null;
        oddCurr.next = null;
        return evens;
    }
    
    public static void main(String args[]){
        LinkedListProblems ll = new LinkedListProblems();
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);

        //printLL(reverseEvenNodes(head));
        printLL(reverseRec(head));
        //printLL(reverseAlt(head));
        char[] chars = new char[3];
        String s =  chars.toString();

        chars[1]= 65;


    }
}
