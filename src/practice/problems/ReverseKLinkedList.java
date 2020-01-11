package practice.problems;

public class ReverseKLinkedList {

    static class LinkedListNode{
        LinkedListNode next;
        int data;
        LinkedListNode(){

        }
        LinkedListNode(int data){
            this.data = data;
        }
    }
    static LinkedListNode createLinkedList(LinkedListNode head, int[] arr){
        LinkedListNode curr = head;
    for(int i=0; i< arr.length; i++){
        curr.data = arr[i];
        curr.next = new LinkedListNode();
        curr = curr.next;
    }
        return head;
    }
    static LinkedListNode reverse_k_nodes(
            LinkedListNode head,
            int k) {
        LinkedListNode start= head;
        LinkedListNode end= head;
        int counter =1;
        LinkedListNode reversed=null;
        while(end!=null && end.next!=null){ // Loop till end

            counter++;
            end = end.next;
            LinkedListNode next = end.next;
            LinkedListNode prevEnd = null;
            // move start to end
            if(counter%k ==0 || end==null ||end.next==null ){ // if k elems or ends
                if(counter ==k){
                    reversed = reverse(start,end);
                   // break;
                }
                else{
                   reverse(start,end);
                   }
                   prevEnd = end;
                   start.next = next; //new chain after k
                   start = next;
                   end = start;

            }

        }
        return reversed;
    }

    private static LinkedListNode reverse(LinkedListNode start, LinkedListNode end){ //return end
        end = end.next;
        LinkedListNode prev = null;
        LinkedListNode curr = start;
        LinkedListNode next = null;
        if(start == end) return curr;
        while(curr!=null && curr!= end){
            next = curr.next ;// save next
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public static void main(String[] args) {
        int[] v1 = new int[]{1, 2, 3, 4, 5, 6, 7};
        LinkedListNode list_head = ReverseKLinkedList.createLinkedList(new LinkedListNode(), v1);
        list_head = reverse_k_nodes(list_head, 3);
       // ArrayList<Integer> result = LinkedList.to_list(list_head);
        int[] expected = new int[]{3, 2, 1, 6, 5, 4, 7};

    }
}
