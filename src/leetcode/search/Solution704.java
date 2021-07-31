package leetcode.search;

public class Solution704 {


    public  int search(int[] nums, int target) {
        return search(nums,0,nums.length-1,target);
    }

    private   int search(int[] nums, int start, int end, int target) {

        if(end<start){
            return -1;
        }
        int middle = start + (end-start)/2;
        if(nums[middle]==target){
            return middle;
        }else if(nums[middle]<target){
            return search(nums,middle+1,end,target);
        }else {
            return search(nums,start,middle-1,target);

        }

    }


}
