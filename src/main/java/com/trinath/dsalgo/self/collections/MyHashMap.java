package com.trinath.dsalgo.self.collections;

import java.util.ArrayList;
import java.util.List;

//https://www.youtube.com/watch?v=1Ovg3IC-p5A
public class MyHashMap<K,V> {
    private final int SIZE = 5;
    private Entry<K,V> table[];
    public MyHashMap(){
        table = new Entry[SIZE];
    }
    //it's the entry in the bucket, essentially a linked list, the bucket is calculated by the hash code
    private class Entry<K,V>{
        private K key;
        private V value;
        private Entry<K,V> next;
        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }
        public K getKey(){
            return this.key;
        }
        public V getValue(){
            return this.value;
        }
        public void setValue(V value){
            this.value = value;
        }
    }




}
