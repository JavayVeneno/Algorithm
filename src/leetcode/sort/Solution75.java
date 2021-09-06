package leetcode.sort;

import java.util.Arrays;

class Solution75 {
    public void sortColors(int[] nums) {
        // 现在有三种颜色
//        int[] count = new int[3];
//        for (int num : nums) {
//            count[num]++;
//        }
//        //[start,count0-1]存放0;
//        //[count0,count0+count1-1]存放1;
//        //[count0,count0+count1+count2-1]存放2;
//        for (int i = 0; i <count[0]; i++) {
//            nums[i]=0;
//        }
//        for (int i = count[0]; i <count[0]+count[1]; i++) {
//            nums[i]=1;
//        }
//        for (int i = count[0]+count[1]; i <count[0]+count[1]+count[2]; i++) {
//            nums[i]=2;
//        }

        contSort2(nums,3);
    }

    public void contSort(int[] nums,int r) {
        // 现在有r种颜色
        int[] count = new int[r];

        for (int num : nums) {
            count[num]++;
        }

        int[] index = new int[r+1];

        for (int i = 1; i < index.length; i++) {
           index[i] = index[i-1]+count[i-1];
        }

        for (int i = 0; i < index.length-1; i++) {
            for (int j = index[i]; j <index[i+1] ; j++) {
                nums[j] = i;
            }
        }

    }
//  实现一个稳定版本的countSort

    public void contSort2(int[] nums,int r) {
        // 现在有r种颜色
        int[] count = new int[r];

        for (int num : nums) {
            count[num]++;
        }

        int[] index = new int[r+1];
        // 新开一份空间
        int[] res = Arrays.copyOf(nums, nums.length);
        for (int i = 1; i < index.length; i++) {
            index[i] = index[i-1]+count[i-1];
        }

        for (int i = 0; i < nums.length; i++) {
            // 通过原数组找到相应元素
            int num = nums[i];
            // 通过元素找到其对应的index 因为 index的范围 为数据的范围+1,所以不用担心index越界
            // index中 [第一种索引,第二种索引),[第n-1种索引,第n种索引)
            int start = index[num];
            //在res种对应的索引处放置num元素
            res[start] = num;
            // 该索引向后移动
            index[num]++;
        }
        System.arraycopy(res,0,nums,0,nums.length);

    }

    public static void main(String[] args) {
        int[] nums = {0,2,2,2,0,1,1};

        new Solution75().sortColors(nums);
        for (int num : nums) {
            System.out.print(num);
        }

    }
}