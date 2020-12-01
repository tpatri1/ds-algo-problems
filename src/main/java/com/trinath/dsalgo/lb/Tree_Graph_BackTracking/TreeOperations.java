package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

//Mainly BST
public class TreeOperations {

    static class Node{
        Node left;
        Node right;
        int val;
        public Node(int a){
            val =a;
            left=null;
            right= null;
        }
    }
    Node root;
    public TreeOperations(Node root){
        this.root = root;
    }
    // BST: max 2 children and left<= root< right
    /**
     * Find a node in the BST
     */

    private Node findNodeBST(Node node, int val){
        while(node!=null && node.val!=val){
            if(node.val>val){
                node = node.left;
            }
            else if(node.val<val){
                node = node.right;
            }
        }
        return node;
    }

    //Insert a node in BST
    private Node insertInBST(Node root, int element){
//        Node parent = root;
//        Node curr = root; // Copy and pass as , this can mutate the root otherwise as iterative
//        //Iteratively
//        if(root==null){
//            root= new Node(element);
//        }
//        else{
//            while(curr!=null){
//                parent=curr;
//                if(curr.val>element){
//                    curr= curr.left;
//                }
//                else{
//                    curr= curr.right;
//                }
//            }
//            if(parent.val>=element){ // can do parent.val>element
//                parent.left = new Node(element);
//            }
//            else if(parent.val<element){// can check isRight too
//                parent.right = new Node(element);
//            }
//        }
        if (root == null)
            return new Node(element);
        else if(element>root.val) // x is greater. Should be inserted to right
            root.right = insertInBST(root.right,element);
        else // x is smaller should be inserted to left
            root.left = insertInBST(root.left,element);

        return root;
    }
//Visit all Left Subtree -> Node -> Visit all Right Sub tree
    private void inOrderRec(Node node){
    if(node==null){
        return;
    }
    inOrderRec(node.left);// recursively
    System.out.println(node.val);
    inOrderRec(node.right);
    }

    private void preOrderRec(Node node){
        if(node==null){
            return;
        }
        System.out.println(node.val);
        preOrderRec(node.left);// recursively
        preOrderRec(node.right);
    }
    private void postOrderRec(Node node){
        if(node==null){
            return;
        }
        postOrderRec(node.left);// recursively
        postOrderRec(node.right);
        System.out.println(node.val);
    }

    //Iterative methods
    private void inOrderIterative(Node node){
        System.out.println("In order Iterative");
        if(node==null){
            return;
        }
        Node curr = node;
        Stack stack = new Stack();

        //Has to be done using stack, that internally does the same thing as recursion
        while(!stack.isEmpty() || curr!=null){
            if(curr!=null) {
                stack.push(curr);
                curr = curr.left;
            }
            else{
                Node node1 = (Node) stack.pop();
                System.out.println(node1.val); // Print node after popping so that we are at the left most chiild
                curr = node1.right;// if it is a leaf it pops again
            }
        }
    }
    private void preOrderIterative(Node node){
        System.out.println("Pre-order Iterative");
        if(node==null){
            return;
        }
        Node curr = node;
        Stack stack = new Stack();

        //Has to be done using stack, that internally does the same thing as recursion
        while(!stack.isEmpty() || curr!=null){
            if(curr!=null) {
                System.out.println(curr.val); // Print node first before pushing
                stack.push(curr);
                curr = curr.left;
            }
            else{
                Node node1 = (Node) stack.pop();
                curr = node1.right;// if it is a leaf it pops again
            }
        }
    }
//TODO: Need to correct
    private void postOrderIterative(Node node){
        System.out.println("Post-order Iterative");
        if(node==null){
            return;
        }
        Node curr = node;
        Stack stack = new Stack();

        //Has to be done using stack, that internally does the same thing as recursion
        while(!stack.isEmpty() || curr!=null){
            if(curr!=null) {

                stack.push(curr);
                curr = curr.left;
            }
            else{
                Node node1 = (Node) stack.peek();
                //curr = node1.right;
                if(node1.right!=null){
                    curr=node1.right;
                    stack.push(curr);
                    curr= curr.left;
                }else {
                    System.out.println(node1.val); // Print node first before pushing
                    stack.pop();
                }

            }
        }
    }
    //TODO Need to correct
    private Node deleteNode(Node node, int val){

        Node node1 = findNodeBST(node,val);
        Node curr = node1;// Traverse to find right bias node// left most leaf of the right child
        //Node parent;
        boolean toRight = false;
        boolean toLeft = false;

      if(curr.right==null && curr.left==null){

            curr = null ;//TODO::deletes that node, but does not delete
            return node;// the root
        }
        Node leaf = parentOfLeafToReplace(curr,toLeft, toRight);
        if(toLeft) {
            node1.val = leaf.left.val;
            leaf.left = null;
        }
        else if(toRight){
            node1.val = leaf.right.val;
            leaf.right = null;
        }
        else{
            if(node.left!=null){
                node1.val = leaf.left.val;
                leaf.left = null;
            }
            else if(node.right!=null){
                node1.val = leaf.right.val;
                leaf.right = null;
            }
        }
        return node;// root

    }

    private Node parentOfLeafToReplace(Node node, boolean toLeft,boolean toRight){
        Node nodeCopy = node;
        if(node.right!=null){//Right bias
            if(node.right.left!=null) {
                toLeft=true;
                node = node.right;
                while (node.left != null) {
                    if (node.left.left != null)//To find parent
                        node = node.left;
                    else
                        break;
                }
            }
            else{
                toRight = true;
                return node;
            }
        }else if(node.left!=null){ // Left Bias, left then right most child

            if(node.right!=null){
                toRight=true;
                node = node.left;
            while(node.right!=null){
                if(node.right.right!=null)
                    node = node.right;
                else
                    break;
            }
        }
        else{
                toLeft=true;
                return node;
            }
        }

        return node;
    }

//Total number of nodes in th tree
    private int sizeOfTree(Node node) {
        if (node == null) {
            return 0;
        }
        int left = sizeOfTree(node.left);
        int right = sizeOfTree(node.right);
        return 1 + left + right;

    }
    //Maximum height of the tree, root height is zero and grows till leaf
    private int height(Node node){
        if(node==null){
            return 0;
        }
        else{
            int lheight = height(node.left);
            int rheight = height(node.right);
            return 1+ Math.max(lheight,rheight); // Index 1 based
        }

    }

    //Diameter of th tree: algo max of sum of heights , left diameter and right diameter
    private int diameter(Node node){
        if(node==null){
            return 0;
        }
        int lheight = height(node.left);
        int rheight = height(node.right);

        int ldiameter = diameter(node.left);
        int rdiameter = diameter(node.right);

        return (Math.max(1+lheight+rheight, Math.max(ldiameter,rdiameter)));// 1+lheight+rheight gives one passes through root
    }
//Do Inorder Traversal until count =K
    private int sumOfKSmallestElements(Node node, int K){
        if(node==null){
            return 0;
        }
        Node curr = node;
        Stack stack = new Stack();
        int count = 0;
        int sum=0;

        //Has to be done using stack, that internally does the same thing as recursion
        while((!stack.isEmpty() || curr!=null) && count<K){
            if(curr!=null) {
                stack.push(curr);
                curr = curr.left;
            }
            else{
                Node node1 = (Node) stack.pop();
                sum+=node1.val;
                ++count;
                curr = node1.right;// if it is a leaf it pops again
            }

        }
        return sum;
    }
    //Level order traversal for a BST
    private void levelOrderTraversal(Node root){
        //We can do one queue but I am uusing two queues to print in lavel wise
        String res="";
        System.out.println("Level order");
        List<Queue<Node>> queues = new ArrayList<Queue<Node>>();
        queues.add(new ArrayDeque<Node>());
        queues.add(new ArrayDeque<Node>());
        Queue<Node> currentQueue = queues.get(0);
        Queue<Node> nextQueue = queues.get(1);
        currentQueue.add(root);
        int level =0;
        while(!currentQueue.isEmpty()){
            Node node = currentQueue.poll();
            System.out.print(node.val+" ");
            res +=" "+node.val;
            if(node.left!=null){
                nextQueue.add(node.left);
            }
            if(node.right!=null){
                nextQueue.add(node.right);
            }
            //Finished one level
            if(currentQueue.isEmpty()){

                System.out.println();
                level++;
                currentQueue = queues.get(level%2);// next becomes current the one after becomes next
                nextQueue = queues.get((level+1)%2);
            }
        }
        System.out.println();
    }
    //Alternatively 1 -  for level order traversal use one queue to push and pop and process all it's childern like BFS
    private static String levelOrderTraversalAlt(Node root) {
        String res = "";
        if(root==null){
            return res;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        //add to queue in a for loop for each children of the root
        while(!queue.isEmpty()){
            Node node = queue.poll();
            res +=" "+node.val;
            if(node.left!=null) queue.add(node.left);
            if(node.right!=null) queue.add(node.right);
        }
        return res;
    }
    //Alternate 2 Using recursion
    private static String levelOrderTraversalAlt2(Node node){
        System.out.print("Level order traversal with recursion");
    StringBuilder output =new StringBuilder();
    int h = getHeightOfBST(node);
    for(int i=1; i<=h;i++){
        System.out.println();
        levelOrderRec(node, i,output);

    }
    return output.toString();
    }
    private static void levelOrderRec(Node node, int height, StringBuilder output){
        if(node==null){
            return ;
        }
        if(height==1){// at the leaf level for that h
            System.out.print(node.val+" ");
            output.append(" ").append(node.val);
        }
        else {
            levelOrderRec(node.left, height - 1, output);
            levelOrderRec(node.right, height - 1, output);
        }

    }
    private static int getHeightOfBST(Node node){ // O(n)
        int height =0;//1 base
        if(node==null) return height;// 0
        int leftHeight =  1+getHeightOfBST(node.left);
        int rightHeight = 1+getHeightOfBST(node.right);
        return Math.max(leftHeight,rightHeight);
    }

    static Node concatenateLists(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        // use left for previous.
        // use right for next.
        Node tail1 = head1.left;
        Node tail2 = head2.left;

        tail1.right = head2;
        head2.left = tail1;

        head1.left = tail2;
        tail2.right = head1;
        return head1;
    }


    public static Node convertToLinkedListRec(Node root) {

        if (root == null) {
            return null;
        }

        Node list1 = convertToLinkedListRec(root.left);
        Node list2 = convertToLinkedListRec(root.right);

        root.left = root.right = root;
        Node result = concatenateLists(list1, root);
        result = concatenateLists(result, list2);

        return result;
    }

    public static Node convertToLinkedList(Node root) {
        Node head = convertToLinkedListRec(root);
        if (head != null && head.left != null) {
            head.left.right = null;
            head.left = null;
        }
        return head;
    }

    static List<Integer> get_list(Node head) {
        List<Integer> r = new ArrayList<Integer>();
        if (head == null) {
            return r;
        }

        Node temp = head;
        do {
            r.add(temp.val);
            temp = temp.right;
        } while (temp.right != null);

        return r;
    }


    private static String printPerimeterOfBST(Node root){
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        printLeftPerimeter(root.left,sb);
        printLeaves(root,sb);
        printRightPerimeter(root.right,sb);
        return sb.toString();
    }

    private static void printLeftPerimeter(Node node, StringBuilder sb) {

        while(node!=null && node.left!=null){//except left leaf
            sb.append(" ").append(node.val);
            node = node.left;
        }
    }
    private static void printLeaves(Node node,StringBuilder sb) {
        if(node==null){
            return;// that side has nothing
        }
        if(node.left==null && node.right==null){
            sb.append(" ").append(node.val);
            return ;
        }
        printLeaves(node.left,sb);
        printLeaves(node.right,sb);
//        if (node != null) {
//            printLeaves(node.left, sb);
//            printLeaves(node.right, sb);
//
//            if (node.left == null &&
//                    node.right == null) {
//                // System.out.print(root.data + " ");
//                sb.append(String.valueOf(node.val) + " ");
//            }
//        }

    }

    private static void printRightPerimeter(Node node, StringBuilder sb) {//Bottom to top
        Stack<Integer> st = new Stack<Integer>();
        while(node!=null && node.right!=null){//except right leaf
            st.push(node.val);
            node = node.right;
        }
        while(!st.isEmpty()){
            int item = st.pop();
            sb.append(" ").append(item);
        }
    }

    public static Node findNthHighestInBST(Node node, int n) {
        if(node==null){
            return null;
        }
        Stack<Node> stack = new Stack<Node>();
        while(!stack.isEmpty() || node!=null){
            if(node!=null){
                stack.push(node);
                node=node.right;
                continue;
            }
            node = stack.pop();
            n--;
            if(n==0){
                return node;
            }
            node = node.left;


        }
        return node;
}
//Alternate recursive soln
//    public static Node findNthHighestInBSTRec(Node node, int n, Node result) {
//        if(node==null){
//            return result;
//        }
//        if(n==0){
//            result = node;
//            return result;
//        }
//        findNthHighestInBSTRec(node.right,n, result);
//        n--; //for the node
//        findNthHighestInBSTRec(node.right,n,result);
//
//        return result;
//    }
    //Similar to as printing all binary string for a number
    private void printAllPathToLeaves(Node node){

    }
    static int M = Integer.MIN_VALUE;
    private static void serialize(Node root, ObjectOutputStream oos) throws IOException {
     //base case
        if(root==null){
            oos.writeInt(M);
            return;
        }
        oos.writeInt(root.val); // Pre order
        serialize(root.left, oos);
        serialize(root.left, oos);
    }

    private static Node deserialize(ObjectInputStream ois) throws IOException {
    Node root = new Node(ois.readInt());
    Node node = root;
        int val = ois.readInt();
        boolean isLeft = true;
        Stack<Node> stack = new Stack<>();
        while(ois.available()>=0) {
            val = ois.readInt();
            if (val == M) {
                stack.pop();
                continue;
            }
            if(isLeft){
                node.left = new Node(val);
                node = node.left;
            }
            else{
                node.right = new Node(val);
                node = node.right;
            }
        }
        return root;
    }
//public static Node deserialize(ObjectInputStream stream) throws java.io.IOException {
//    int val = stream.readInt();
//    if (val == M) {
//        return null;
//    }
//
//    Node node = new Node(val);
//    node.left = deserialize(stream);
//    node.right = deserialize(stream);
//    return node;
//}

    public static int deleteZeroSumSubtreeRec(Node node){

        if(node==null){
            return 0;
        }

         int left_sum = deleteZeroSumSubtreeRec(node.left);
        int right_sum = deleteZeroSumSubtreeRec(node.right);
        if(left_sum==0){
            node.left = null;
        }
        if(right_sum==0){
            node.right = null;
        }
        return node.val+left_sum+right_sum;
    }

     static int maxLevel = -1;
    private static void printRightView(Node root){
        System.out.println("Print right view");

        int level =0;
        printRightViewRec(root, level);
        System.out.println("Print right view end");
    }

    private static void printRightViewRec(Node root, int level) {
        if(root==null){
            return;
        }
        if(maxLevel<level){
            System.out.println(root.val);
            maxLevel =level;
        }
        printRightViewRec(root.right,level+1); // this wil hit maxLevl<level and print first for right view
        printRightViewRec(root.left,level+1);
    }

    static int sum1=0;// Can be done easily with a gloabal variable
    private static int sumLargerNodes(Node root, int target){
        sumLargerNodesHelperAlt(root,target);
        System.out.println("Sum of larger node 1st approach: "+sum1);
        //alt
        int sum= sumLargerNodesHelper(root, target);
        System.out.println("Sum of larger node 2nd approach: "+sum);
        return sum;


    }
    private static int sumLargerNodesHelper(Node root, int target){
        if(root==null){
            return 0;
        }
        if(root.val>=target){
            //sum1+=root.val;
             return root.val +sumLargerNodesHelper(root.right, target)+sumLargerNodesHelper(root.left, target);
        }else{
            return sumLargerNodesHelper(root.right, target)+sumLargerNodesHelper(root.left, target);
        }

    }
    private static void sumLargerNodesHelperAlt(Node root, int target){
        if(root==null){
            return ;
        }
        if(root.val>=target) {
            sum1 += root.val;
        }
        sumLargerNodesHelperAlt(root.right, target);
        sumLargerNodesHelperAlt(root.left, target);

    }

    private static Node setRoot(){
    Node root = new Node(6);
    TreeOperations bst = new TreeOperations(root);

    bst.insertInBST(bst.root, 7);
    bst.insertInBST(bst.root, 5);
    bst.insertInBST(bst.root, -3);
    bst.insertInBST(bst.root, -2);

    return root;
}

private static void treeNodeCounts(Node root, Map<Node, Integer> counts) {
    counts.put(root, 1);
    if (root.left == null && root.right == null) {
        return;
    }
    if(root.left != null){
        treeNodeCounts(root.left, counts);
    counts.put(root, counts.get(root) + counts.get(root.left));
    }
    if (root.right != null) {
        treeNodeCounts(root.right, counts);
        counts.put(root, counts.get(root) + counts.get(root.right));
    }
}
    public static void main(String args[]){
        Node root = new Node(5);
        TreeOperations bst = new TreeOperations(root);

        bst.insertInBST(bst.root, 7);
        bst.insertInBST(bst.root, 2);
        bst.insertInBST(bst.root, -3);
        bst.insertInBST(bst.root, 9);
        bst.insertInBST(bst.root, -7);
        bst.insertInBST(bst.root, 4);
        bst.insertInBST(bst.root, 6);

        System.out.println("size of tree : "+bst.sizeOfTree(root));
        System.out.println("height of tree: "+bst.height(bst.root));
        System.out.println("diameter of tree: "+bst.diameter(bst.root));
        System.out.println("Sum of K elements: "+bst.sumOfKSmallestElements(bst.root, 4));
        System.out.println("Start In Order");
        bst.inOrderRec(bst.root);
        bst.inOrderIterative(bst.root);
        System.out.println("Start preOrder");
        bst.preOrderRec(bst.root);
        bst.preOrderIterative(bst.root);
        System.out.println("Start postOrder");
        bst.postOrderRec(bst.root);
        //bst.postOrderIterative(bst.root);

        System.out.println("Start In Order");
        bst.deleteNode(bst.root,2);
        bst.inOrderRec(bst.root);
        //System.out.println("Start In Order>>");
        //bst.inOrderRec(bst.deleteNode(bst.root,8));
        bst.levelOrderTraversal(root);
        sumLargerNodes(root,5);
        System.out.println("height "+getHeightOfBST(root));
        printRightView(root);

        Map<Node, Integer> countMap = new HashMap<>();
        treeNodeCounts(root,countMap);
        for(Node node: countMap.keySet()){
            System.out.println("Node val "+node.val+" "+countMap.get(node));
        }
        //---Start of new tree, looks like tree is mutated after this--//

        bst.levelOrderTraversalAlt2(root);

        //BSST to DLL
        List<Integer> input = new ArrayList<Integer>();
        input.add(100);input.add(50);input.add(200);input.add(25);input.add(75);input.add(125);input.add(350);
        input.add(30);input.add(60);


        Node head = convertToLinkedList(bst.root);
        List<Integer> result = get_list(head);
        System.out.println(result);
       // System.out.println(" Print perimeter"+printPerimeterOfBST(root));

        System.out.println(" Nth highest node "+findNthHighestInBST(setRoot(),3).val);// Actual ans is 6 somewhere it's getting mutated
        //Node res = findNthHighestInBSTRec(setRoot(),3,new Node(0));
        //System.out.println(" Nth highest node Alt "+res.val);
        Node root1 = new Node(7);
        root1.left = new Node(5);
        root1.right =new Node(6);
        root1.left.left = new Node(-3);
        root1.left.right = new Node(-2);


        try {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ObjectOutputStream stream= new ObjectOutputStream(baos);
//            serialize(root1,stream);
//            System.out.println("OOS "+stream);
//            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
//            ObjectInputStream ois = new ObjectInputStream(bis);


            ByteArrayOutputStream baostream = new ByteArrayOutputStream();
            ObjectOutputStream stream = new ObjectOutputStream(baostream);
            serialize(root, stream);
            stream.close();

            ByteArrayInputStream baistream = new ByteArrayInputStream(
                    baostream.toByteArray());
            ObjectInputStream  istream = new ObjectInputStream(baistream);
            Node rootDeserialized = deserialize(istream);

            System.out.println("deserialize ");
            bst.preOrderIterative(rootDeserialized);

        } catch (IOException e) {
            e.printStackTrace();
        }


        deleteZeroSumSubtreeRec(root1);
        System.out.println(" after delete zero sub tree");
        bst.levelOrderTraversal(root1);

    }

}
