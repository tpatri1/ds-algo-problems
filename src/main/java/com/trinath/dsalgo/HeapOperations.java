package com.trinath.dsalgo;

import java.util.Arrays;
import java.util.Scanner;

/**
 A min heap is a a heap where the root is the min elem.
It is a representaion of an array.
 Items to be added at leaf -> then heapifyUp()
 Items to be removed from the root -> then heapifyDown()
 */

public class HeapOperations {
    int size = 0;
    int capacity = 10; //to start with
    int[] items;


// helper functions for Index
    private int getLeftChildIndex(int parentIndex){
        return 2*parentIndex+1;
    }
    private int getRightChildIndex(int parentIndex){
        return 2*parentIndex+2;
    }
    private int getParentIndex(int childIndex){
        return (childIndex-1)/2; // works both child
    }

    //has child and parent helpers

    private boolean hasLeftChild(int index){
        return getLeftChildIndex(index) < size;
    }
    private boolean hasRightChild(int index){
        return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index){
        return getRightChildIndex(index) >=0 ;
    }

    //get item

    private int getLeftChild(int index){
        return items[getLeftChildIndex(index)];
    }

    private int getRightChild(int index){
        return items[getRightChildIndex(index)];
    }

    private int getParent(int index){
        return items[getParentIndex(index)];
    }
    //increase capacity
    private void checkAndMakeExtraCapacity(){
        if(size==capacity){
            items = Arrays.copyOf(items, capacity*2);
            capacity *=2;
        }
    }
    //swap nodes

    private void swap(int index1, int index2){
        int temp;
        temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;

    }

    private void add(int item){
        //add to the last element and heapifyUp
        checkAndMakeExtraCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    private void poll(){ //remove root

        items[0]= items[size-1];
        size--;
        heapifyDown();
    }
// if parent is bigger then swap
    private void heapifyUp(){
        int ptr =size-1;
        while(hasParent(ptr) && items[ptr] < getParent(ptr))

            swap(ptr, getParentIndex(ptr));
            ptr = getParentIndex(ptr);

    }

    //

    private void heapifyDown(){

        int index = 0;
        while(hasLeftChild(index) ){
            int smallerChildIndex = getLeftChildIndex(index);
            if(hasRightChild(index) && getLeftChild(index) > getRightChild(index)){
                smallerChildIndex = getRightChildIndex(index);

            }
            if(items[index] < items[smallerChildIndex]) {
                break;
            }
                else{
                    swap(index, smallerChildIndex);
                    index = smallerChildIndex;
                }

        }
    }

    private void print(){
        for(int i=0; i<items.length; i++){
            System.out.println(items[i]);
        }
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        HeapOperations h = new HeapOperations();
        Scanner scanner = new Scanner(System.in);
        int numberOfInput = scanner.nextInt();
        for(int i=0; i< numberOfInput; i++){
            int type = scanner.nextInt();

            if(type==1){
                h.add(scanner.nextInt());
            }
            if(type==2){
                h.poll();
            }
            if(type==3){
                h.print();
            }
        }
    }
    //
}
