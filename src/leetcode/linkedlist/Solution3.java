package leetcode.linkedlist;

public class Solution3 {

    public ListNode removeElements(ListNode head, int val,int depth) {

        String depthString = generateDepthString(depth);
        System.out.println(depthString+"begin_remove:"+val+" in " + head);
    //将整个node看成头和尾两个节点
    //那么头节点为空就直接return null
    if(head == null){
        System.out.println(depthString+"return:"+head);

        return null;
    }

    //在将尾节点调用removeElements方法去获取一个删除掉所有val元素的节点
    head.next  = removeElements(head.next,val,depth+1);
        System.out.println(depthString+"after_remove:"+val+" get " +head.next);

    // 剩下的节点都已经删除了val,但是头节点并没有删除相应元素
        if (head.val == val) {
            System.out.println(depthString+"return:"+head.next);
            return head.next;
        } else {
            System.out.println(depthString+"return:" +head);
            return head;
        }

}

    private String generateDepthString(int depth) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <depth ; i++) {
            sb.append("--|");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        // 这种写法太扯了,我们尝试一下给他增加一个构造方法,入参是数组
        // ListNode l1 = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(6,new ListNode(4,new ListNode(5,new ListNode(6)))))));
        int[] arr = {1,2,3,6,4,5,6};
        ListNode l1 = new ListNode(arr);
        Solution3 s = new Solution3();
        System.out.println(l1);

        s.removeElements(l1,6,0);
        System.out.println(l1);
    }
}