package leetcode.tree;




/**
 *
 * @Description: 对于题目中给出的数组不变条件,可以尝试简单实现
 *
 * @author: Veneno
 * @date: 2021/8/15 22:32
 * @param:
 * @return:
 */


class NumArray303p2 {


    private int[] sum;

    public NumArray303p2(int[] nums) {
        int[]data = new int[nums.length];
        System.arraycopy(nums,0,data,0,nums.length);
        sum  = new int[nums.length+1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i-1]+data[i-1];
        }
    }



    public int sumRange(int left, int right) {
        return sum[right+1]-sum[left];
    }



    public static void main(String[] args) {
        int[] test = {-2, 0, 3, -5, 2, -1};

        NumArray303p2 numArray = new NumArray303p2(test);
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