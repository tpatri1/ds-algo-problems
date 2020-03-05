package com.trinath.dsalgo.lb;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;
import java.util.stream.Collectors;

import static kotlin.test.AssertionsKt.assertEquals;

//Create a node and edge, using adjacency list just number and Node .. Traversal DFS, BFS
//Min Spanning Tree - Prims, Kruskal
//Cycle detection using Post order DFS if part of set and Union Find
//Topological Sort
//Shortest Path
public class GraphOperations {
    static class Vertex{
        String label;
        Vertex(String label) {
            this.label = label;
        }
        private String getLabel(){
            return label;
        }
        //hashcode and equals
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof Vertex))
                return false;
            if (obj == this)
                return true;
            return this.getLabel() == ((Vertex) obj).getLabel();
        }
        //Otherwise the map will put each vertext separately as the hashcode is going to be different by default
        @Override
        public int hashCode() {
            return label.hashCode();
        }
    }
    //Simple Graph Using adjacency list
    private Map<Vertex,List<Vertex>>  adjList ; // vertex to edges
    private Map<Vertex, Boolean> visited ;

    public GraphOperations(){
        this.adjList = new HashMap<>();
        this.visited = new HashMap<>();
    }

    private GraphOperations  addVertex(String  vertexName){
       this.adjList.putIfAbsent(new Vertex(vertexName), new ArrayList<>());
        return this;
    }
    // Directed Graph
    private void addEdge(String fromVertex, String toVertex) {
        Vertex v1 = new Vertex(fromVertex);
        Vertex v2 = new Vertex(toVertex);
        if (!this.adjList.containsKey(v1)) {
            this.adjList.put(v1,new ArrayList<>());
        }
        this.adjList.get(v1).add(v2);

    }

    private void printGraph(GraphOperations graph){
        Iterator<Vertex> itr = graph.adjList.keySet().iterator();
        while(itr.hasNext()) {
            Vertex vertex = itr.next();
            List<String> edges = graph.adjList.get(vertex).stream().map(e -> e.label).collect(Collectors.toList());
            System.out.println(vertex.label + "--> " + graph.adjList.get(vertex).stream().map(e -> e.getLabel()).collect(Collectors.toList()));
        }
    }

    //DFS Use a stack
    private Set<String> dfs(GraphOperations graph, Vertex root){
        Set<String> visited = new LinkedHashSet<>();
        Stack<String> stack = new Stack();
        stack.push(root.label);

        while(!stack.isEmpty()){
            String vLabel = (String)stack.pop();
            visited.add(vLabel);
            List<Vertex> children = graph.adjList.get(new Vertex(vLabel));
            for(Vertex v:children){
                if(!visited.contains(v.label)){ // to avoid cycles
                    stack.push(v.label);
                }
            }
        }
        return visited;
    }


    //BFS
    private Set<String> bfs(GraphOperations graph, Vertex root){
        Set<String> visited = new LinkedHashSet<>();//ordered
        Queue<String> queue = new LinkedList<>();
        queue.add(root.getLabel());//at rear

        while(!queue.isEmpty()){
            String vLabel = queue.remove();//from front
            visited.add(vLabel);
            List<Vertex> children = graph.adjList.get(new Vertex(vLabel));
                for(Vertex v: children){
                    if(!visited.contains(v.label)){ // to avoid cycles
                        queue.add(v.label);
                    }
                }
        }
        return visited;
    }

    //Minimum Spanning tree(connect all nodes with minimum sum of weights) - Prims

    //Minimum Spanning tree - Kruskal

    //- Topological Sorting

    private void createGraph(){
//        addVertex("A").addEdge("A","B");
//        addEdge("A","C");
//        addVertex("D").addEdge("D","C");
//        addEdge("D","E");
//        addEdge("G","H");

        addVertex("Bob");
        addVertex("Alice");
        addVertex("Mark");
        addVertex("Rob");
        addVertex("Maria");
        addEdge("Bob", "Alice");
        addEdge("Bob", "Rob");
        addEdge("Alice", "Mark");
        addEdge("Rob", "Mark");
        addEdge("Alice", "Maria");
        addEdge("Rob", "Maria");


    }

    public static void main(String args[]){
        GraphOperations graph = new GraphOperations();
        graph.createGraph();
        graph.printGraph(graph);
        //https://www.baeldung.com/java-graphs
        System.out.println("DFS: "+graph.dfs(graph,new Vertex("Bob")));
        System.out.println("BFS: "+graph.bfs(graph,new Vertex("Bob")));
        //assertEquals("[Bob, Rob, Maria, Alice, Mark]", graph.dfs(graph, new Vertex("Bob").toString());
        //assertEquals("[Bob, Alice, Rob, Mark, Maria]", graph.bfs(graph, new Vertex("Bob")).toString());
    }
}
