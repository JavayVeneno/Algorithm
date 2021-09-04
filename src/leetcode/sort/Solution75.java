package leetcode.sort;

class Solution75 {
    public void sortColors(int[] nums) {
        // 现在有三种颜色
        int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }
        //[start,count0-1]存放0;
        //[count0,count0+count1-1]存放1;
        //[count0,count0+count1+count2-1]存放2;
        for (int i = 0; i <count[0]; i++) {
            nums[i]=0;
        }
        for (int i = count[0]; i <count[0]+count[1]; i++) {
            nums[i]=1;
        }
        for (int i = count[0]+count[1]; i <count[0]+count[1]+count[2]; i++) {
            nums[i]=2;
        }
    }
    public static void main(String[] args) {
        int[] nums = {0,2,1,1,0,2,2,0,1};

        new Solution75().sortColors(nums);
        for (int num : nums) {
            System.out.print(num);
        }

    }
}