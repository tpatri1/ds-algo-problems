package com.trinath.dsalgo.lb.Stack_Queue_LinkedList_HashTable;

class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

class ReverseEveryKElements {

    public static ListNode reverse(ListNode head, int k) {
        int num = k;
        ListNode curr = head;
        ListNode oldTail = null;
        ListNode newTail = null;
        ListNode prev = null;
        ListNode newHead =  null;
        while(curr!=null){
             if(k==num){
               prev= null;
             }
            if(k-- >0){
                ListNode next = curr.next;// take a copy of next as it will be lost
                curr.next = prev;
                prev = curr;
                curr = next;
                System.out.println(" " +prev.value);
            }
            if(k==0){
                System.out.println(" " +curr.value+" "+k);
                k=num;
                if(newHead==null){
                    newHead= prev;
                }

                if(oldTail!=null){
                    oldTail.next = prev;
                    oldTail= newTail;
                }else{
                    oldTail = head;
                }
                newTail = curr;
            }

        }
         System.out.println(" " +prev.value+" oldTail "+oldTail.value);
          if(oldTail!=null){
            oldTail.next = prev;
          }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        ListNode result = ReverseEveryKElements.reverse(head, 3);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}