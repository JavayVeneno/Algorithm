package leetcode.linkedlist;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public ListNode(int[] arr) {

        if(arr == null || arr.length <= 0){
            throw new IllegalArgumentException("failed");
        }
        ListNode current = this;
        current.val = arr[0];
        for (int i = 1; i <arr.length ; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
    }

    @Override
    public String toString() {

        StringBuilder sb =  new StringBuilder();
        ListNode current = this;
        while (current != null){
            sb.append(current.val+"->");
            current = current.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}