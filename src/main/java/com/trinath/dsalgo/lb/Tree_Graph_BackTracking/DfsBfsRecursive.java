package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.ArrayList;
import java.util.List;

public class DfsBfsRecursive {
    static class Node {
        String name;
        List<Node> children = new ArrayList<Node>(); // This is adjacency list along with String name , can be replaced by a Map<Node,List<Node>>

        public Node(String name) {
            this.name = name;
        }

        public List<String> depthFirstSearch(List<String> result) {// this array is result
            result.add(this.name);
            for(int i=0; i<this.children.size(); i++){
                children.get(i).depthFirstSearch(result);
            }

            return result;
        }

        public Node addChild(String name) {
            Node child = new Node(name);
            children.add(child);
            return this;
        }
    }
    static void printList(List<String> list){
        list.stream().forEach(l ->System.out.println(l));
    }

    public static void main(String args[]){
        Node node = new Node("A");
        node.addChild("B");
        node.addChild("C");
        node.children.get(0).addChild("E");
        node.children.get(0).addChild("F");

        printList(node.depthFirstSearch(new ArrayList<>()));

    }
}
