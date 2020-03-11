package com.trinath.dsalgo.lb;


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
            return this.getLabel().equals(((Vertex) obj).getLabel());
        }
        //Otherwise the map will put each vertex separately as the hashcode is going to be different by default
        @Override
        public int hashCode() {
            return label.hashCode();
        }
    }
    //Simple Graph Using adjacency list
    private Map<Vertex,List<Vertex>>  adjList ; // vertex to edges


    public GraphOperations(){
        this.adjList = new HashMap<>();

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

    //- Topological Sorting Using recursive DFS.. Ignore already visited If for leaf node, that has no dependency then add to the stack, then move to parent and add it as well, ignore/continue nxode  If for leaf node, that has no dependency then add to the stack, then move to parent and add it as well
    private Stack<Vertex> topologicalSort_DFS(GraphOperations graph){// We don't need a weighted grap for this
        Map<Vertex, List<Vertex>> adjList = graph.adjList;
        Stack<Vertex> sorted = new Stack<>();
        Set<Vertex> visited = new HashSet<>();

        //Iterate each vertex
        Iterator itr = adjList.keySet().iterator();
        while(itr.hasNext()){
            Vertex vertex = (Vertex) itr.next();
            if(!visited.contains(vertex)){
                topologicalSortHelper(vertex, sorted, visited, adjList);
            }
        }
        return sorted;

    }

    private void topologicalSortHelper(Vertex vertex, Stack<Vertex> sorted, Set<Vertex> visited, Map<Vertex, List<Vertex>> adjList){
        visited.add(vertex);
        if(adjList.containsKey(vertex)) {
            for (Vertex child : adjList.get(vertex)) {// If for leaf node, that has no dependency then add to the stack, then move to parent and add it as well
                if (visited.contains(child)) {
                    continue;
                }
                topologicalSortHelper(child, sorted, visited, adjList); // this recursion does DFS
            }
        }
       sorted.push(vertex);//add the leaf
    }

    private Stack<Vertex> topologicalSort_BFS(GraphOperations graph) {
        Stack<Vertex> sorted = new Stack<>();
        return sorted;
    }

    private void createGraph(){
        addEdge("A","B");
        addEdge("A","E");
        addEdge("B","E");
        addEdge("B","C");
        addEdge("C","D");
        addEdge("D","E");
        addEdge("E","D");
        addEdge("B","D");
        //addEdge("D","B");


//        addVertex("Bob");
//        addVertex("Alice");
//        addVertex("Mark");
//        addVertex("Rob");
//        addVertex("Maria");
//        addEdge("Bob", "Alice");
//        addEdge("Bob", "Rob");
//        addEdge("Alice", "Mark");
//        addEdge("Rob", "Mark");
//        addEdge("Alice", "Maria");
//        addEdge("Rob", "Maria");


    }

    public static void main(String args[]){
        GraphOperations graph = new GraphOperations();
        graph.createGraph();
        graph.printGraph(graph);
        //https://www.baeldung.com/java-graphs
       // System.out.println("DFS: "+graph.dfs(graph,new Vertex("A")));
       // System.out.println("BFS: "+graph.bfs(graph,new Vertex("A")));
        //assertEquals("[Bob, Rob, Maria, Alice, Mark]", graph.dfs(graph, new Vertex("Bob").toString());
        //assertEquals("[Bob, Alice, Rob, Mark, Maria]", graph.bfs(graph, new Vertex("Bob")).toString());
        System.out.println("Topological Sort");
        Stack<Vertex> sorted =graph.topologicalSort_DFS(graph);
        while(!sorted.isEmpty()){
            Vertex  v = sorted.pop();
            System.out.println(v.getLabel());
        }
    }
}
