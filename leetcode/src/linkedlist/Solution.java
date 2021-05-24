package linkedlist;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {

        // 使用虚拟头节点

        ListNode dummyHead = new ListNode(-1,head);



//        // 先解决头部,再解决其他
//
//        while(head != null && head.val==val){
//            ListNode del =head;
//            head = head.next;
//            del.next = null;
//        }
//        if(head == null){
//            return null;
//        }

        ListNode prev = dummyHead;
        while(prev.next != null ){
            if(prev.next.val == val){

                //算法是解决方案不考虑loitering对象,所以可以不去释放del.next引用
                // 所以下面三行代码可以改成这样: prev.next = prev.next.next;

                ListNode del = prev.next;
                prev.next = del.next;
                del.next = null;
            }else{
                prev = prev.next;
            }
        }
        return dummyHead.next;

    }


    public static void main(String[] args) {
        // 这种写法太扯了,我们尝试一下给他增加一个构造方法,入参是数组
        // ListNode l1 = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(6,new ListNode(4,new ListNode(5,new ListNode(6)))))));
        int[] arr = {1,2,3,6,4,5,6};
        ListNode l1 = new ListNode(arr);
        Solution s = new Solution();
        System.out.println(l1);

        s.removeElements(l1,6);
        System.out.println(l1);
    }
}