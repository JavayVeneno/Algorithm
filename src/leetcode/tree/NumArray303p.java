package leetcode.tree;


import dataStructure.tree.SegmentTree;

/**
 *
 * @Description: 可以使用自己实现的线段树SegmentTree来验证是否正确实现
 *
 * @author: Veneno
 * @date: 2021/8/15 22:23
 * @param:
 * @return:
 */


class NumArray303p {

    private SegmentTree<Integer> segmentTree;

    public NumArray303p(int[] nums) {
        Integer[]data = new Integer[nums.length];
//        System.arraycopy(nums,0,data,0,nums.length);
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }
       segmentTree  = new SegmentTree<>(data,(a, b) -> a+b);
    }



    public int sumRange(int left, int right) {
        return segmentTree.query(left,right);
    }



    public static void main(String[] args) {
        int[] test = {-2, 0, 3, -5, 2, -1};

        NumArray303p numArray = new NumArray303p(test);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));

    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */