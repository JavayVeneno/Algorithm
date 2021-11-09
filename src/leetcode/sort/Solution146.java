package leetcode.sort;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Solution146 {

    public int maximumGap(int[] nums) {
        int res = 0;
        if(nums.length<2){
            return res;
        }
        sort(nums);
        for (int i = 0; i < nums.length-1; i++) {

            res = Math.max(res,Math.abs(nums[i+1]-nums[i]));
        }
        return res;

    }

    private void sort(int[] nums){



        int maxv = Integer.MIN_VALUE;
        int minv = Integer.MAX_VALUE;

        for (int i: nums) {
            maxv = Math.max(i,maxv);
            minv = Math.min(i,minv);
        }

        int c =Math.max(1, (maxv - minv) / (nums.length - 1));
        int b = (maxv-minv+1)/c + ((maxv-minv+1)%c>0? 1 : 0);

        ArrayList<Integer>[] buckets = new ArrayList[b];

        for (int i = 0; i < b; i++) {
            buckets[i] = new ArrayList<>();
        }


        for (int i : nums) {
            buckets[(i-minv)/c].add(i);
        }

        for (int i = 0; i < b; i++) {
            Collections.sort(buckets[i]);
        }

        int index = 0 ;
        for (int i = 0; i < b; i++) {
            for (int x :buckets[i]){
                nums[index ++] = x;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = {2,99999999};
        System.out.println(new Solution146().maximumGap(nums));

    }

}
