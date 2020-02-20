package com.trinath.dsalgo.lb;

import java.util.Stack;

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
    private void insertInBST(Node root, int element){
        Node parent = root;
        Node curr = root; // Copy and pass as , this can mutate the root otherwise as iterative
        //Iteratively
        if(root==null){
            root= new Node(element);
        }
        else{
            while(curr!=null){
                parent=curr;
                if(curr.val>element){
                    curr= curr.left;
                }
                else{
                    curr= curr.right;
                }
            }
            if(parent.val>=element){ // can do parent.val>element
                parent.left = new Node(element);
            }
            else if(parent.val<element){// can check isRight too
                parent.right = new Node(element);
            }
        }
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


    private int sizeOfTree(Node node) {
        if(node==null){
            return 0;
        }
        int left = sizeOfTree(node.left);
        int right = sizeOfTree(node.right);
        return 1+ left+right;

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
        bst.insertInBST(bst.root, 8);
        System.out.println(bst.sizeOfTree(root));
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

    }

}
