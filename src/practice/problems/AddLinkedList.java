package practice.problems;

public class AddLinkedList {
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
    static LinkedListNode add_integers(LinkedListNode integer1, LinkedListNode integer2) {
        LinkedListNode result = new LinkedListNode(0);
        LinkedListNode result_head = null;
        int carry =0;
        int sum = 0;

//int prev_carry =0
        while(integer1!=null && integer2!=null){

            sum =integer1.data + integer2.data + carry;
            carry=0;
            if( sum>10){
                carry=1;
                sum = sum %10;
            }
            result.data = sum;
            integer1 = integer1.next;
            integer2 = integer2.next;
            //result = result.next;
            if(result_head==null){
                result_head = result;
            }
            result.next = new LinkedListNode(0);
            result = result.next;
        }

        return result_head;
    }

    public static void main(String[] args) {
        int[] v1 = new int[]{1, 2, 3};
        LinkedListNode list_head = createLinkedList(new LinkedListNode(), v1);
        int[] v2 = new int[]{1, 2, 3, 4};
        LinkedListNode list_head1 = createLinkedList(new LinkedListNode(), v2);
        list_head = add_integers(list_head, list_head1);
        // ArrayList<Integer> result = LinkedList.to_list(list_head);
        int[] expected = new int[]{3, 2, 1, 6, 5, 4, 7};

    }
}
