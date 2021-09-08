package leetcode.linkedlist;



/**
 *  翻转链表非递归实现
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution4 {

    public ListNode reverseList(ListNode head) {

        if(head == null){
            return null;
        }
//        ListNode prev = null;
//        ListNode current = head;
//        ListNode next = head.next;
//
//        while(next != null){
//            current.next = prev;
//            prev = current;
//            current =next;
//            next = next.next;
//        }
//        current.next=prev;
//        return current;


        return recursiveReverse(head);
    }


    public ListNode recursiveReverse (ListNode head) {

        if(head == null || head.next == null){
            return head;
        }
        ListNode ok = recursiveReverse(head.next);
        head.next.next=head;
        head.next=null;
        return ok;

    }

    public static void main(String[] args) {
        ListNode test  = new ListNode(new int[]{1,2,3,4});
        System.out.println(test);
        ListNode listNode = new Solution4().reverseList(test);
        System.out.println(listNode);
    }
}