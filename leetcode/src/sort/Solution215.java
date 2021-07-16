package sort;

import java.util.Random;

class Solution215 {

    private static final Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        int maxIndex  = nums.length-k;


        return nums[selectKNotR(nums,maxIndex,0,nums.length-1)];
    }

    // 递归实现
    private int selectK(int[] nums, int maxIndex, int l , int r){
        int p  = partition(nums,l,r);
        if(p == maxIndex){
            return p;
        }else if(p>maxIndex){
            return selectK(nums,maxIndex,l,p-1);
        }else{
            return selectK(nums,maxIndex,p+1,r);
        }
    }

    // 非递归实现 大同小异

    private int selectKNotR(int[] nums,int maxIndex,int l,int r){
        int p  = partition(nums,l,r);
        while(l<=r){
            if( p == maxIndex){
                return p;
            }else if(p>maxIndex){
                r = p-1;
            }else {
                l = p+1;
            }
            p = partition(nums,l,r);
        }
        return p;
    }


    private int partition(int[] nums, int l, int r) {
        int p = l + random.nextInt(r - l + 1);
        int value = nums[p];
     swap(nums,l,p);
     int i = l+1,j = r;
     // [l,i-1]<=value,[j+1,r]>=value
     while(true){
         while(i<=j && nums[i]<value ){
             i++;
         }

         while(j>=i && nums[j]>value){
             j--;
         }
         if(i>=j){
             break;
         }
         swap(nums,i,j);
         i++;
         j--;
     }
     swap(nums,l,j);
       return j;

    }

    private void swap(int[] nums, int l, int p) {
        if(l==p){
            return ;
        }
        nums[l] = nums[l] ^ nums[p];
        nums[p] = nums[l] ^ nums[p];
        nums[l] = nums[l] ^ nums[p];
    }

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int kthLargest = new Solution215().findKthLargest(nums, 9);
        System.out.println(kthLargest);
    }
}