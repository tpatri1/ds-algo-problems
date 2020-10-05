package com.trinath.dsalgo.lb.Stack_Queue_LinkedList_HashTable;

public class OddEvenList {

         static void createList(Node head){
            if(head==null){
                return;
            }
            Node temp = head;
            Node oddHead=null, odd=null;
            Node evenHead = null, even=null;
            if(head!=null){
                odd = head;
                oddHead= head;
            }
            if(head.next!=null){
                even = head.next;
                evenHead = even;
            }

            boolean isOdd = true;
            while(temp!=null)
            {
                if(isOdd){
                    odd = temp;
                    //odd.next= null;
                    odd.next = temp.next;
                }else{
                    even = temp;
                    //even.next=null;
                    odd.next = temp.next;
                }
                isOdd = !isOdd;
                temp = temp.next;


            }
            printList(oddHead);
            printList(evenHead);

        }

       static  void printList(Node head){
            if(head==null){
                return;
            }
           System.out.println("List ");
            Node temp = head;
            while(temp!=null){
                System.out.print(temp.data);
                temp = temp.next;
                if(temp!=null){
                    System.out.print(" ->");
                }
            }
        }



    public static void main(String args[]){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        printList(head);
        createList(head);
    }
}
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}