package linkedlist;

public class Solution2 {

    public ListNode removeElements(ListNode head, int val) {

    //将整个node看成头和尾两个节点
    //那么头节点为空就直接return null
    if(head == null){
        return null;
    }
    //在将尾节点调用removeElements方法去获取一个删除掉所有val元素的节点
    head.next  = removeElements(head.next,val);
    // 剩下的节点都已经删除了val,但是头节点并没有删除相应元素
    return head.val == val?head.next:head;

}


    public static void main(String[] args) {
        // 这种写法太扯了,我们尝试一下给他增加一个构造方法,入参是数组
        // ListNode l1 = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(6,new ListNode(4,new ListNode(5,new ListNode(6)))))));
        int[] arr = {1,2,3,6,4,5,6};
        ListNode l1 = new ListNode(arr);
        Solution2 s = new Solution2();
        System.out.println(l1);

        s.removeElements(l1,6);
        System.out.println(l1);
    }
}