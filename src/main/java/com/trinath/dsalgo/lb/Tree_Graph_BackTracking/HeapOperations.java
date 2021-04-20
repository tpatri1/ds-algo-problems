package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.*;

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

    private int poll(){ //swap , remove, heapifyDown
        int res = items[0];
        items[0]= items[size-1];
        items[size-1]=0;//null
        size--;
        heapifyDown(0);
        return res;
    }

    private int removeAt(int index){
        int res = items[index];
        items[index] =items[size-1];// this removes the index element
        items[size-1]=0;//null
        size--;
        heapifyDown(index);
        return res;
    }
// if parent is bigger then swap
    private void heapifyUp(){ // we can have index i as an arg and pass size-1 from caller
        int ptr =size-1;
        while(hasParent(ptr) && items[ptr] < getParent(ptr))

            swap(ptr, getParentIndex(ptr));
            ptr = getParentIndex(ptr);

    }

    //

    private void heapifyDown(int index){//can be converted to any index i

        //int index = 0;
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
//Delete from a heap - swap with last elem and heapifydown

    //Heapify all, last non leaf((h.size()-1)/2) node to root, call heapify down.- this will give O(n) instead of nlogn

    //Check if it is a max heap or mean  heap or nothing -- parent is smaller than children- min heap

    //remove anyIndex

    //poll() - remove top of heap - O(1), then sawp last elem and heapify down
    private void print(){
        for(int i=0; i<items.length; i++){
            System.out.println(items[i]);
        }
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
//        HeapOperations h = new HeapOperations();
//        Scanner scanner = new Scanner(System.in);
//        int numberOfInput = scanner.nextInt();
//        for(int i=0; i< numberOfInput; i++){
//            int val = scanner.nextInt();
//
//        }
        PriorityQueue minHeap= new PriorityQueue();//default min heap
        minHeap.add(4);
        minHeap.add(2);
        minHeap.add(5);
        minHeap.add(1);

        System.out.println("top elm "+minHeap.peek());
        minHeap.poll();
        System.out.println("2nd top elm "+minHeap.peek());

        PriorityQueue maxHeap= new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;//-ve is high priority than 0 than 1
            }
        });//default min heap
        maxHeap.add(4);
        maxHeap.add(2);
        maxHeap.add(5);
        maxHeap.add(1);
        System.out.println("top elm "+maxHeap.peek());
        maxHeap.poll();
        System.out.println("2nd top elm "+maxHeap.peek());


    }
    //
}
