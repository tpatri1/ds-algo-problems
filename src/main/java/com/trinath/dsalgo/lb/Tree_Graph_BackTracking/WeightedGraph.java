package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Collections.*;




//Create a node and edge, using adjacency list just number and Node .. Traversal DFS, BFS
//Min Spanning Tree - Prims, Kruskal
//Cycle detection using Post order DFS if part of set and Union Find
//Topological Sort
//Shortest Path
    public class WeightedGraph {
        static class Vertex{
            String label;
            int dist;
            Vertex(String label) {
                this.label = label;
                this.dist = Integer.MAX_VALUE; // distance is To use for Dijkestra and Bellman Ford Shortest Path
            }

            public int getDist() {
                return dist;
            }

            public void setDist(int dist) {
                this.dist = dist;
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

            @Override
            public String toString() {
                return getLabel()+" : "+getDist();
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

        public Map<Vertex, List<Edge>> getAdjList() {
            return adjList;
        }

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
     * vhttps://www.youtube.com/watch?v=4ZlRH0eK-qQ&t=720shttps://www.youtube.com/watch?v=4ZlRH0eK-qQ&t=720s -- Ignore select min edge per video, rather start wiith any source and its min out edge
     * https://www.youtube.com/watch?v=oP2-8ysT3QQ&t=714s
     * https://www.baeldung.com/java-prim-algorithm
     * Algo, A gready approach is taken for Prims MST starts with a "ANY source Vertex" , It finds all the outgoing edges of it and select the min cost edge(source--> dest) of it( and add to result MST) and add the source to the visited node.
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


    /**
     * Minimum Spanning tree - Kruskal - Find the minimum cost edge and add to result, find the next minimum edge and so on UNTIL all vertices are visited
     * @param graph
     * @return
     */
        private Map<Vertex, List<Edge>> kruskalMST(WeightedGraph graph){

            Map<Vertex,List<Edge>> adjList = graph.adjList;
            Map<Vertex, List<Edge>> mst = new HashMap<>(); // Same structure as graph adjacency list but no cycles
            Set<Vertex> visited = new HashSet<Vertex>();

            for(Vertex v: graph.adjList.keySet()){
                makeSet(v.getLabel().charAt(0)-'A');
            }
            PriorityQueue<Edge> minHeapEdges = buildMinHeapEdge(adjList);
            while( !minHeapEdges.isEmpty()){ //TODO:: WE Should take only those min edge that does not form cycle, so below implementation is not correct
                Edge minEdge = minHeapEdges.poll();

                long set1 = findSet(minEdge.getSource().getLabel().charAt(0)-'A');
                long set2 = findSet(minEdge.getDestination().getLabel().charAt(0)-'A');
                if(set1==set2){
                    continue;
                }
                union(minEdge.getSource().getLabel().charAt(0)-'A', minEdge.getDestination().getLabel().charAt(0)-'A');
                //visited.add(minEdge.getSource());// Not required for Kruskal but required for Prims
                //visited.add(minEdge.getDestination());// // Not required for Kruskal but required for Prims
                List<Edge> existingEdges = mst.getOrDefault(minEdge.getSource(),new ArrayList<>());
                existingEdges.add(minEdge);
                mst.put(minEdge.getSource(),existingEdges);
            }
            return mst;
        }
        private PriorityQueue<Edge> buildMinHeapEdge(Map<Vertex,List<Edge>> adjList){
            PriorityQueue<Edge> edgeMinHeap = new PriorityQueue<>(new Comparator<Edge>() {
                @Override
                public int compare(Edge e1, Edge e2) {
                    return e1.getWeight() - e2.getWeight();
                }
            });

            Iterator itr = adjList.keySet().iterator();
            while(itr.hasNext()){
                List<Edge> edges = (List<Edge>) adjList.get(itr.next());
                for(Edge edge: edges){
                    edgeMinHeap.add(edge);
                }
            }
        return edgeMinHeap;

        }



    /**
     * https://www.youtube.com/watch?v=XB4MIexjvY0
     * Limitaion : does not work for -ve weights, Use Bellman Ford for -ve weights
     * Single Source Shortest Path - Dijkstra - it find shortest path from a source to all vertices, we have added a property int dist for distance , that is initialized to Infinity i.e, Integer.MAX
     * Start with a source , add its destination vertex if old distance is greater than newly calculated distance(destination distance source > source dist +edge weight , then update dest dist with new value) and add to the heap.
     * If the heap has already an item remove the old and add the destination with new dist
     *And add the vertex to heap and to visited set once complete.
     * @param graph
     * @param src
     * @return
     */
    private Set<Vertex> shortestPathDijkestra(WeightedGraph graph, Vertex src) {
        Map<Vertex, List<Edge>> adjList = graph.getAdjList(); // By default dist is Integer.MAX in the beginning
        Set<Vertex> visited = new HashSet<>(); //To keep track
        PriorityQueue<Vertex> minDistHeap = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o1.getDist() - o2.getDist();
            }
        });

        if (adjList.containsKey(src)) {
            //Add first time for source
            src.setDist(0);
            minDistHeap.add(src);
            while (!minDistHeap.isEmpty()) { // That guarenties visited.size() == adjList.size()
                src = minDistHeap.poll(); // Everytime changes
                visited.add(src);
                // Evaluate each edge for newly selected source and instert to the minheap (with vertex distance if earliier one is bigger)
                for (Edge edge : adjList.get(src)) {
                    Vertex dest = edge.getDestination();
                    int newDist = src.getDist() + edge.getWeight();
                    if (dest.getDist() > newDist) { // Relaxation: Add to heap if oldDist is greater than new dostance
                        if (minDistHeap.contains(dest)) {
                            minDistHeap.remove(dest);
                        }
                        dest.setDist(newDist); // Update the distance after removing and setting new distance and relax
                        minDistHeap.add(dest);
                    }
                }
            }

        }
        return visited;
    }
        //Union find
    int[] parent = new int[52];// 26 char *2
        void makeSet(int v) {

            parent[v] = v;
        }

    int findSet(int v) {
        if (v == parent[v])
            return v;
        return findSet(parent[v]);
    }

    void union(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a != b)
            parent[b] =a;
    }
        //TODO::Single Source Shortest Path - Bellman Ford


        //TODO::Cycle Detection - Union Find

        private void createGraph(){
        addVertex("A").addEdge("A","B",1);
        addEdge("A","E",3);
        addVertex("B").addEdge("B","E",1);
        addEdge("B","C",2);
        addEdge("C","D",2);
        addEdge("D","E",5);
        addEdge("E","D",3);
        addEdge("B","D",1);


        }

        public static void main(String args[]){
            System.out.println('A');
            WeightedGraph graph = new WeightedGraph();
            graph.createGraph();
            graph.printGraph(graph.adjList);
            //https://www.baeldung.com/java-graphs
            System.out.println("DFS: "+graph.dfs(graph,new Vertex("A")));
            System.out.println("BFS: "+graph.bfs(graph,new Vertex("A")));
            //assertEquals("[Bob, Rob, Maria, Alice, Mark]", graph.dfs(graph, new Vertex("Bob").toString());
            //assertEquals("[Bob, Alice, Rob, Mark, Maria]", graph.bfs(graph, new Vertex("Bob")).toString());
            System.out.println("Prims MST");
            graph.printGraph(graph.primsMST(graph,new Vertex("A")));
            System.out.println("Kruskal MST");
            graph.printGraph(graph.kruskalMST(graph));
            System.out.println("Dijkestra Shortest Path");
            Set<Vertex> visited = graph.shortestPathDijkestra(graph, new Vertex("A"));
            System.out.println(visited.toString());
            Iterator itr = visited.iterator();
            while(itr.hasNext()){
                Vertex vertex = (Vertex) itr.next();
                System.out.println(vertex.getLabel()+": "+vertex.getDist());
            }
        }
    }


