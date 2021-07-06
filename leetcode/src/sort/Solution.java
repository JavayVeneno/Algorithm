package sort;

import java.util.Arrays;

class Solution {
    public int reversePairs(int[] nums) {




        return mergeSort(nums,0,nums.length-1, Arrays.copyOf(nums,nums.length));
    }

    private int mergeSort(int[] nums, int start, int end,int[] temp) {
        int res = 0;
        if(start>=end){
            return res;
        }
        int middle = start+(end-start)/2;
        res+=mergeSort(nums,start,middle,temp);
        res+=mergeSort(nums,middle+1,end,temp);
        if(nums[middle]>nums[middle+1]){
            res+=merge(nums,start,middle,end,temp);
        }
        return res;
    }

    private int merge(int[] nums, int start, int middle,int end, int[] temp) {
        System.arraycopy(nums,start,temp,start,end-start+1);
        int left = start,right = middle+1,res =0;

        for (int i = start; i <= end; i++) {
            // 如果左边越界,说明左比较完了,只剩右边了
            if(left>middle){
                nums[i] = temp[right];
                right++;
            }
            // 如果右边越界,说明右边比较完了,只剩左边了
            else if(right>end){
                nums[i] = temp[left];
                left++;
            }
            // 如果都没有越界,那么就将两者比较
            else if(temp[left]<=temp[right]){
                nums[i] = temp[left];
                left++;
            }else {
                // 如果右边小,那么就完全形成了此时右边这个元数与左边剩余元素逆序
                res+= middle-left+1;
                nums[i] = temp[right];
                right++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {1,3,2,3,1};
        int i = new Solution().reversePairs(nums);
        System.out.println(i);
    }
}