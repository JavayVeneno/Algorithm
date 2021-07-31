package leetcode.sort;



class Solution2 {
    public void sortColors(int[] nums) {
        // 这种题只有三个类型元素,所以标定点直接选择中间类型就行,不能随机
        int l  = 0,r = nums.length-1 /*p = getIndexByValue(nums,1)*/ ;
        int value =1;
//        swap(nums,l,p);
        //[l,lt]<value ,[lt+1,i-1]=value,[gt,r]>value;
        int lt = l-1,i = l,gt = r+1;
        while(i<gt){
            if(nums[i]<value){
                lt++;
                swap(nums,lt,i);
                i++;
            }else if(nums[i]>value){
                gt--;
                swap(nums,i,gt);
            }else{
                i++;
            }

        }
//        swap(nums,l,lt);
    }

    private int getIndexByValue(int[] nums, int x) {
        for (int i = 0; i < nums.length; i++) {
            if(x==nums[i]){
                return i;
            }
        }
        return 0;
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
        int[] nums = {0,2,1,1,0,2,1};

        new Solution2().sortColors(nums);
        for (int num : nums) {
            System.out.println(num);
        }

    }
}