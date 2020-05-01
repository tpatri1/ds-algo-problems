package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;


import java.util.*;
import java.util.stream.Collectors;

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

    /*
    Pseudo code that is for both DFS and BFS using stack and queue respectively
    def search(root , st){
    st = new()
    st.add(root);
    while(!st.isEmpty()){
        Node node = st.remove();
        process(node);
        for(Node child: node.getAdjList/Children()){// this is most important to form graph
            st.add(child);
            }
        }
    }
     */
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

    //Minimum Spanning tree(connect all nodes with minimum sum of weights) - Prims - Check WeightedGraph.java

    //Minimum Spanning tree - Kruskal - Check WeightedGraph.java


    /**
     * https://www.youtube.com/watch?v=ddTC4Zovtbc
     * Topological Sorting Using recursive DFS.. Ignore already visited If for leaf node, that has no dependency then add to the stack, then move to parent and add it as well, ignore/continue nxode  If for leaf node, that has no dependency then add to the stack, then move to parent and add it as well
     * This does not work when the graph has a loop - so use cycle detection
     * @param graph
     * @return
     */
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
       sorted.push(vertex);//add the leaf as that is independent, this is head recursion after reaching top of the recursive stack, pocessing starts i.e, leaf added to sorted is processing ( while tail recursion is lie e loop)
    }

    /**
     * https://www.youtube.com/watch?v=0LjVxtLnNOk -- Stack based
     * https://www.youtube.com/watch?v=rG2-_lgcZzo  - Queue BFS based
     * Topological Sort using BFS and converting a dependency matrix to adjacencylist grap representation
     *[2, 3] means 2 is dependent on 3 like 3->2 if one node is dependent on multiple iis written by [2,3], [2,1]
     * @param dependencies
     * @return
     * @throws Exception
     */
    private Queue<Integer> topologicalSort_BFS(int[][] dependencies) throws Exception {

        Map<Integer,Integer> inDegree = new HashMap<>();//can be done as array if from 0 to n ..new int[dependencies.length];
        Map<Integer, List<Integer>> adjList = new HashMap<>(); // [2,1] [3,1] becomes 3<-1->2 that is  1->2, 1->3 that is : {1,[2,3]}
        //Crteate an adjacency list from 2d array
        for(int[] row : dependencies){ // process every record of matrix or 2D array
            List<Integer> list = adjList.getOrDefault(row[1],new ArrayList<>());// second colum is the dependency to 1st column
            list.add(row[0]);
            inDegree.put(row[0],inDegree.getOrDefault(row[0],0)+1); //[2,1] [2,3] in-degree(iinward arrow) of 2 is 2 from 1 & 3
            adjList.put(row[1],list);
        }

        //Now create a queue and add all indegree 0 and process to do BFS
        Queue<Integer> queue = new LinkedList();
        for(Integer key: adjList.keySet()){
            if(!inDegree.containsKey(key) || inDegree.get(key)==0)// independent
            {
                queue.add(key);
            }
        }
        //Process indegree 0 and 1 s of >1 then can't do sorting as there is a cycle
       Queue<Integer> sorted = new LinkedList() ;

       while(!queue.isEmpty()){
           int item = queue.poll();
           sorted.add(item);
           if(!adjList.containsKey(item)){
               continue;
           }//else
           for(int neighbour:adjList.get(item)){
               int i = inDegree.get(neighbour)-1; // each time we process an item, reduce in degree of its neighbour
               inDegree.put(neighbour,i);
               if(i==0) {
                   queue.add(neighbour);
               }
           }
       }
       if(sorted.size()!=dependencies.length+1){// vertices
           throw new Exception("All vertices are not sorted topologically: "+sorted.size()+" vertices: "+Integer.valueOf(dependencies.length+1));// there is loop
       }
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
        System.out.println("DFS: "+graph.dfs(graph,new Vertex("A")));
        System.out.println("BFS: "+graph.bfs(graph,new Vertex("A")));
        //assertEquals("[Bob, Rob, Maria, Alice, Mark]", graph.dfs(graph, new Vertex("Bob").toString());
        //assertEquals("[Bob, Alice, Rob, Mark, Maria]", graph.bfs(graph, new Vertex("Bob")).toString());
        System.out.println("Topological Sort DFS");
        Stack<Vertex> sorted =graph.topologicalSort_DFS(graph);
        while(!sorted.isEmpty()){
            Vertex  v = sorted.pop();
            System.out.println(v.getLabel());
        }
        System.out.println("Topological Sort BFS");
        int[][] dependencies = {{2,1},{3,2},{3,4},{5,3},{2,7}}; // 1->4->2->3->5
        Queue<Integer> sortedBFS=null;
        try {
            sortedBFS=graph.topologicalSort_BFS(dependencies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(!sortedBFS.isEmpty()){
            int item = sortedBFS.remove();
            System.out.println(item);
        }
    }
}
