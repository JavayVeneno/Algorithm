package leetcode.queue;


import java.util.PriorityQueue;

class Solution215 {


    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            minHeap.add(nums[i]);
        }

        for (int i = k; i <nums.length ; i++) {
            if(!minHeap.isEmpty() && nums[i]>minHeap.peek()){
                minHeap.remove();
                minHeap.add(nums[i]);
            }
        }

        return minHeap.remove();


    }


    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int kthLargest = new Solution215().findKthLargest(nums, 8);
        System.out.println(kthLargest);
    }
}