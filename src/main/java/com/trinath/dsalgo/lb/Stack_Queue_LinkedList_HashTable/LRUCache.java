package com.trinath.dsalgo.lb.Stack_Queue_LinkedList_HashTable;

import java.util.*;

/*https://www.interviewcake.com/concept/java/lru-cache
*LRU Cache properties : A fiixed size cache
*1. returns an item in O(1) if in Cache
*2. When adding to cache  O(1) - add to the cache at head/first/rear
* 3. O(1) remove/evict least used element when cache is full
* */
public class LRUCache {
    class Node{
        int key; // Key for the cache
        int value; // Value for the cache
        Node prev; //prev pointer to doubly LL node
        Node next; //next pointer to doubly LL node

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
        public Node(){

        }
    }
    private Map<Integer, Node> map ; // keep the key to LinkedList reference mainly to achieve delete in O(1)
    //private final Deque deque = new LinkedList();// We can't use this as it does not give access to node for removing element so design your own doubly linkedlist
    private  int capacity = 10;
    private Node head, tail; // Add at head/first/rear remove from tail/last/front

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.map= new HashMap<>();
        this.head = null;//new Node();
        this.tail = null;//new Node();
    }

    private void setElement(int key, int value){

        if(map.size()==capacity){
        //Evict from the cache from the tail end.
        int leastUsedKey = tail.key;
        tail = tail.prev; // delete tail node
        tail.next = null;
        map.remove(leastUsedKey);
    }
    // Now add at head
       addToHead(key, value);

    }

    private int getElement(int key){
        if(map.containsKey(key)){
          Node node = map.get(key);
          return node.value;
        }

        throw new NoSuchElementException("Element not found in Cache");
    }

    private void deleteIfExists(int key){
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.prev.next =node.next;
            node.next.prev = node.prev;
        }
    }

    private void addToHead(int key, int val){
        Node newNode = new Node(key, val);
        if(head!=null) {
            head.prev = newNode;
        }
        newNode.next = head;
        newNode.prev = null;
        head = newNode; // re assign head
        map.put(key, newNode);
        if(tail==null){// first time
            tail=newNode;
        }


    }


    public void displayLRUCache(){
        System.out.println("Items in the Cache");
        Node temp=head;
        while(temp!=null)
        {
            System.out.print(temp.value + " ");
            temp=temp.next;
        }
    }

    /*
    // Linked list operations
// LinkedListNode(int data)
// LinkedListNode(int key, int data)
// LinkedListNode(int data, LinkedListNode next)
// LinkedListNode(int data, LinkedListNode next, LinkedListNode arbitrary_pointer)

    class LRUCache {
        int capacity;

        //LinkedListNode holds key and value pairs
        HashMap<Integer,LinkedListNode> cache;
        LinkedList<LinkedListNode> cacheVals;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<Integer,LinkedListNode>(capacity);
            cacheVals = new LinkedList<LinkedListNode>();
        }


        int get(int key) {
            if(cache.containsKey(key)){
                LinkedListNode node = cache.get(key);
                cacheVals.remove();
                LinkedListNode node1 = new LinkedListNode(node.data);
                cacheVals.add(node1);
                return node.data;
            }
            return -1;
        }

        void set(int key, int value) {
            LinkedListNode node = cache.get(key);
            if(node==null){
                if(cacheVals.size()==capacity){
                    //Evict
                    LinkedListNode node1 = cacheVals.remove();
                    cache.remove(node1.data);
                }
                node = new LinkedListNode(key, value);
                cacheVals.addLast(node);
                cache.put(key, node);
            }
            cacheVals.remove(node);
            cacheVals.addLast(node);

        }

        String print() {
            String result = "";
            ListIterator<LinkedListNode> iterator = cacheVals.listIterator(0);
            while(iterator.hasNext()){
                LinkedListNode node = iterator.next();
                result += "(" + node.key + "," + node.data + ")";
            }
            return result;
        }

        */

    public static void main(String args[]){
        LRUCache cache = new LRUCache(5);
        cache.setElement(1,10);
        cache.setElement(2,20);
        cache.setElement(3,30);
        cache.setElement(4,40);
        cache.setElement(5,50);
        cache.displayLRUCache();
        cache.setElement(6,60);
        cache.setElement(7,70);
        cache.displayLRUCache();
        cache.deleteIfExists(6);
        cache.displayLRUCache();
        System.out.println();
        LinkedList<Integer> dll = new LinkedList<>();
        dll.addFirst(1);
        dll.addFirst(2);
        dll.removeLast();

    }
}
