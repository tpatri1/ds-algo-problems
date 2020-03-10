package com.trinath.dsalgo.lb;

import java.util.*;
import java.util.stream.Collectors;




//Create a node and edge, using adjacency list just number and Node .. Traversal DFS, BFS
//Min Spanning Tree - Prims, Kruskal
//Cycle detection using Post order DFS if part of set and Union Find
//Topological Sort
//Shortest Path
    public class WeightedGraph {
        static class Vertex{
            String label;
            Vertex(String label) {
                this.label = label;
            }
            private String getLabel(){
                return label;
            }
            //hashcode and equals for checking in hashmap key
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
        static class Edge{
            private int weight;
            private Vertex destination;//This a directed destination vertex forms edge from a souurce vertex in the adjacency list; If it is not a weighted graph
            private Vertex source; // This is a redundant information with the key of adjacency list but helps to avoid iteration for MST problems

            public Edge(int weight, String destinationLabel ){
                this.weight = weight;
                this.destination = new Vertex(destinationLabel);
            }
            public Edge(int weight, Vertex source, Vertex destination ){
                this.weight = weight;
                this.destination = destination;
                this.source = source;
            }
            public Edge(int weight, String sourceLabel, String destinationLabel ){
                this.weight = weight;
                this.source = new Vertex(sourceLabel);
                this.destination = new Vertex(destinationLabel);
            }

            public int getWeight() {
                return weight;
            }

            public Vertex getSource() {
                return source;
            }

            public void setSource(Vertex source) {
                this.source = source;
            }

            public Vertex getDestination() {
                return destination;
            }

            public boolean equals(Object obj) {
                if (obj == null) return false;
                if (!(obj instanceof Vertex))
                    return false;
                if (obj == this)
                    return true;
                return this.getDestination().getLabel().equals(((Edge) obj).getDestination().getLabel())
                        && this.getWeight()==((Edge) obj).getWeight();
            }
            //Otherwise the map will put each vertex separately as the hashcode is going to be different by default
            @Override
            public int hashCode() {
                return destination.hashCode() + getWeight();
            }
        }
        //Simple Graph Using adjacency list, Also, it can be represented as Graph(Vs,Es) there E(V1,V2,weight)
        private Map<Vertex,List<Edge>> adjList ; // vertex to edges; Alternative: we can have  a Vertex v and List<Edge> adjacency list as well instead of map , here Edge has only destination and weight as Source vertex is given in the key of the map


        public WeightedGraph(){
            this.adjList = new HashMap<>();

        }

        private WeightedGraph addVertex(String  vertexName){
            this.adjList.putIfAbsent(new Vertex(vertexName), new ArrayList<Edge>());
            return this;
        }

        private void addEdge(String fromVertex, String toVertex) {
            addEdge(fromVertex,toVertex,1);
        }
        // Directed Graph
        private void addEdge(String fromVertex, String toVertex, int weight) {
            Vertex v1 = new Vertex(fromVertex);

            Edge e1 = new Edge(weight,fromVertex,toVertex); // Let's have default cost 1 if no cost present
            if (!this.adjList.containsKey(v1)) {
                this.adjList.put(v1,new ArrayList<>());
            }
            this.adjList.get(v1).add(e1);

        }

        private void printGraph(Map<Vertex, List<Edge>> adjList){
            Iterator<Vertex> itr = adjList.keySet().iterator();
            while(itr.hasNext()) {
                Vertex vertex = itr.next();
                List<String> edges = adjList.get(vertex).stream().map(e -> e.getDestination().label).collect(Collectors.toList());
                System.out.println(vertex.label + "--> " + adjList.get(vertex).stream().map(e -> e.getDestination().getLabel()+":"+e.getWeight()).collect(Collectors.toList()));
            }
        }

        //DFS Use a stack
        private Set<String> dfs(WeightedGraph graph, Vertex root){
            Set<String> visited = new LinkedHashSet<>();
            Stack<String> stack = new Stack();
            stack.push(root.label);

            while(!stack.isEmpty()){
                String vLabel = (String)stack.pop();
                visited.add(vLabel);
                List<Edge> edges = graph.adjList.get(new Vertex(vLabel));
                for(Edge edge:edges){
                    Vertex outV = edge.getDestination();
                    if(!visited.contains(outV.label)){ // to avoid cycles
                        stack.push(outV.label);
                    }
                }
            }
            return visited;
        }

        //BFS
        private Set<String> bfs(WeightedGraph graph, Vertex root){
            Set<String> visited = new LinkedHashSet<>();//ordered
            Queue<String> queue = new LinkedList<>();
            queue.add(root.getLabel());//at rear

            while(!queue.isEmpty()){
                String vLabel = queue.remove();//from front
                visited.add(vLabel);
                List<Edge> children = graph.adjList.get(new Vertex(vLabel));
                for(Edge edge: children){
                    Vertex outV = edge.getDestination();
                    if(!visited.contains(outV.label)){ // to avoid cycles
                        queue.add(outV.label);
                    }
                }
            }
            return visited;
        }

    //Minimum Spanning tree(connect all nodes(n) and n-1 edges with minimum sum of weights) ; Spannig tree does not have a cycle

    /**
     * vhttps://www.youtube.com/watch?v=4ZlRH0eK-qQ&t=720shttps://www.youtube.com/watch?v=4ZlRH0eK-qQ&t=720s
     * https://www.youtube.com/watch?v=oP2-8ysT3QQ&t=714s
     * Algo, A gready approach is taken for Prims MST starts with a any source Vertex , It finds all the outgoing edges of it and select the min cost edge(source--> dest) of it( and add to result MST) and add the source to the visited node.
     * The destination Vertex of selected(once) min edge is taken as next source node add all of it's out going edge to select minimum cost edges of ALL visited nodes so far. If the min edge's destination is already visited one then select next min edge until minHeap is empty
     * and finally the MST is returned when all vertices are visited
     *
     * @param graph
     * @return MST
     */
        private Map<Vertex, List<Edge>> primsMST(WeightedGraph graph, Vertex source){// source is startVertex
            Map<Vertex,List<Edge>> adjList = graph.adjList;
            Map<Vertex, List<Edge>> mst = new HashMap<>(); // Same structure as graph adjacency list but no cycles
            Set<Vertex> visited = new HashSet<Vertex>();
            PriorityQueue<Edge> edgeMinHeap = new PriorityQueue<>(new Comparator<Edge>() {
                @Override
                public int compare(Edge e1, Edge e2) {
                    return e1.getWeight() - e2.getWeight();
                }
            });

            while(visited.size()!=adjList.size()) {// Till we visit all vertices
                visited.add(source);
                List<Edge> edges = adjList.get(source);
                for (Edge edge : edges) { // Find all out edges of the source and push to a min heap so you can find min edge
                    edgeMinHeap.add(edge);
                }

                while (!edgeMinHeap.isEmpty() && !visited.contains(edgeMinHeap.peek().getDestination())) {
                    Edge minEdge = edgeMinHeap.poll(); // The lowest cost edge of all visited vertex is slelected and this edge's destination is next source
                    List<Edge> mstEdges = mst.getOrDefault(minEdge.getSource(),new ArrayList<Edge>());
                    mstEdges.add(minEdge);
                    mst.put(minEdge.getSource(),mstEdges);// add to existing mst
                    source = minEdge.getDestination(); // Min cost edge's destination is next source
                    break; // Once one non visited node is processed, update the source and repeat
                }
            }

            return mst;
        }

        //Minimum Spanning tree - Kruskal

        //- Topological Sorting

        private void createGraph(){
        addVertex("A").addEdge("A","B",1);
        addEdge("A","E",3);
        addVertex("B").addEdge("B","E",1);
        addEdge("B","C",2);
        addEdge("C","D",2);
        addEdge("D","E",5);
        addEdge("E","D",3);


        }

        public static void main(String args[]){
            WeightedGraph graph = new WeightedGraph();
            graph.createGraph();
            graph.printGraph(graph.adjList);
            //https://www.baeldung.com/java-graphs
            System.out.println("DFS: "+graph.dfs(graph,new Vertex("A")));
            System.out.println("BFS: "+graph.bfs(graph,new Vertex("A")));
            //assertEquals("[Bob, Rob, Maria, Alice, Mark]", graph.dfs(graph, new Vertex("Bob").toString());
            //assertEquals("[Bob, Alice, Rob, Mark, Maria]", graph.bfs(graph, new Vertex("Bob")).toString());
            System.out.println("MST");
            graph.printGraph(graph.primsMST(graph,new Vertex("A")));
        }
    }


