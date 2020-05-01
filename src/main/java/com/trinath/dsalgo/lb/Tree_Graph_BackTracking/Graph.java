package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.*;
class Graph {
    static class Node {
        public int data;
        public List<Node> neighbors = new ArrayList<Node>();
        public Node(int d) {data = d;}
    }
         Node root ;
         private void setRoot(int data){
             this.root = new Node(data);
         }
         private Node getRoot(){
             return root;
         }

         //Problem -  1  - Clone a graph
        public Node clone(Node root) {

            Map<Node, Node> old2newNode = new HashMap<>();
            Stack<Node> visited = new Stack<Node>();// from the original graph

            Stack<Node> stack = new Stack<Node>();
            stack.push(root);
            while(!stack.isEmpty()){
                Node node = stack.pop();
                if(!visited.contains(node)){

                    old2newNode.put(node, new Node(root.data));// for the cloned graph
                    //make clone of children of oriiginal graph
                    for(Node child: node.neighbors){
                        Node childClone= null;
                        if(!old2newNode.containsKey(child)){
                            childClone= new Node(child.data);
                            old2newNode.put(child, childClone);
                            old2newNode.get(node).neighbors.add(child);
                        }
                        stack.push(child);
                        visited.add(child);
                    }
                }
            }
            return old2newNode.get(root);
        }
        //Alternative recursive

        public static Node cloneAlt(Node root){
             Node rootClone = new Node(root.data);
             Map<Node,Node> completedOld2New = new HashMap<>();

              cloneRec(root,completedOld2New );
             return completedOld2New.get(root);
        }

        private static Node cloneRec(Node node, Map<Node, Node> completedOld2New) {
            Node cNode = new Node(node.data);
            completedOld2New.put(node, cNode);

            for (Node nei : node.neighbors) {
                Node cloneNei = completedOld2New.get(nei);
                if (cloneNei == null) {
                    cNode.neighbors.add(cloneRec(nei, completedOld2New));
                }
                else{
                    cNode.neighbors.add(cloneNei);
                }
            }
            return cNode;

    }

    //add Node
        private Node addEdge(Node fromNode, Node toNode){
            if(fromNode.neighbors.isEmpty()){
                fromNode.neighbors = new ArrayList<>();
            }
            fromNode.neighbors.add(toNode);
            return toNode;
        }

        private void printGraph(Node root){
            //Use BFS
            Queue<Node> queue = new LinkedList<Node>() {
            };
            queue.add(root);
            Set<Node> visited = new HashSet<>();
            Set<Node> printed = new HashSet<>();
            while(!queue.isEmpty()){
                Node node = queue.remove();
                if(!printed.contains(node)) {
                    System.out.println(node.data + " : ");
                }

                for(Node nei: node.neighbors ){
                    if(!printed.contains(node)) {
                        System.out.println(nei.data);
                    }
                    if(!visited.contains(nei)){
                        queue.add(nei);
                    }
                }
                visited.add(node);
                printed.add(node);

            }
        }
//Problem 2- Word Chaining -- end of one letter is same as start of one ex- eve, eat, ripe, tear


    //BFS & DFS problems
    //1.Smallest Multiple With 0 and 1
    //2.Largest Distance between nodes of a Tree

    //Connectivity Problems
    //3.Commutable Islands
    //4.Black Shapes
    //5. Topological Sorting - DONE

    //Shortest Path Problems
    //6.Sum Of Fibonacci Numbers
    //7.Knight On Chess Board : https://www.techiedelight.com/chess-knight-problem-find-shortest-path-source-destination/
    //8. Word Ladder

    //Graph Hashing Problems
    //9.Clone Graph -- done above


    //TODO:: all problems of AE ~5-6



    public static void main(String args[]){
        Graph graph = new Graph();
        graph.setRoot(0);
        Node root = graph.getRoot();
        Node four = new Node(4);
        Node three = new Node(3);
        Node two = new Node(2);
        Node one = new Node(1);
        graph.addEdge(root, three);
        graph.addEdge(root, four);
        graph.addEdge(root, two);
        graph.addEdge(four, one);
        graph.addEdge(four, three);
        graph.addEdge(three, two);
        graph.addEdge(one, two);
        graph.addEdge(two, root);
        graph.addEdge(four, root);
        graph.printGraph(root);

        System.out.println("Cloning ");
        Node rootClone= graph.clone(root);
        graph.printGraph(rootClone);
        System.out.println("Cloning in recursion");
        graph.printGraph(cloneAlt(root));
    }

}
