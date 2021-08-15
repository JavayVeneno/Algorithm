package leetcode.tree;


import DataStructure.tree.SegmentTree;

class NumArray307p {


    private SegmentTree<Integer> segmentTree;

    public NumArray307p(int[] nums) {
        Integer[]data = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }
       segmentTree = new SegmentTree<>(data,(a, b) -> a+b);
    }



    public void update(int index, int val) {
       segmentTree.set(index,val);
    }


    public int sumRange(int left, int right) {
        return segmentTree.query(left,right);
    }


    public static void main(String[] args) {
        int[] test = {-2, 0, 3, -5, 2, -1};

        NumArray307p numArray = new NumArray307p(test);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
        numArray.update(1,2);
        System.out.println(numArray.sumRange(0, 2));
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
