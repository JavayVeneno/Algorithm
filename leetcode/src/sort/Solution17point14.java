package sort;

import java.util.Arrays;
import java.util.Random;

class Solution17point14 {

    private static final Random random = new Random();


    public int[] smallestK(int[] arr, int k) {
        if(k==0){
            return new int[0];
        }

        int p  = selectKNotR(arr,k-1,0,arr.length-1);

        return Arrays.copyOf(arr,k);
    }
    // 换个定义来实现selectK的方式

    public int[] samllestK2(int[] arr,int k ){
        if(k == 0){

            return new int[0];
        }
//        int p  = selectK2(arr,k-1,0,arr.length);
//        int p = selectk2R(arr,k-1,0,arr.length);
        int p = selectkByNewPartition(arr, k - 1, 0, arr.length);
        return Arrays.copyOf(arr,k);
    }

    private int selectk2R(int[] arr, int index, int l, int r) {
        int p  = partition(arr,l,r-1);
        //[l, p),[p,r)
        if(p == index){
            return p ;
        }else if(p>index){
            return selectk2R(arr,index,l,p);
        }else{
            return selectk2R(arr,index,p+1,r);
        }

    }

    private int selectkByNewPartition(int[] arr, int index, int l, int r) {
        int p  = partition2(arr,l,r);
        //[l, p),[p,r)
        if(p == index){
            return p ;
        }else if(p>index){
            return selectk2R(arr,index,l,p);
        }else{
            return selectk2R(arr,index,p+1,r);
        }

    }

    private int partition2(int[] arr, int l, int r) {
        int p = l+random.nextInt((r-l)/2);
        int value = arr[p];
        swap(arr,l,p);
        // [l+1,i-1]>=value,[j,r)<=value; [i,j-1]=value;

        int i = l+1,j=r-1;
        while(true){
            while(i<=j && arr[i] <value){
                i++;
            }
            while(i<=j && arr[j] > value){
                j--;
            }
            if(i>=j){
                break;
            }
            swap(arr,j,i);
            i++;
            j--;
        }
        swap(arr,l,j);
        return j;
    }

    private int selectK2(int[] arr, int target, int l, int r) {
        int p  = partition(arr,l,r-1);

        while (l < r){
            if(target == p){
                return p;
            }else if(target > p){
                l =p+1;
            }else {
                r =p;
            }
             p = selectK2(arr,target,l,r);
        }
        return p;
    }

    private int selectK(int[] arr, int index, int l, int r) {

        int p = partition(arr,l,r);
        if(index == p){
            return p;
        }else if(index < p){
            return selectK(arr,index,l,p-1);
        }else{
            return  selectK(arr,index,p+1,r);
        }
    }
    private int selectKNotR(int[] arr ,int index,int l ,int r){

        int p = partition(arr,l,r);

        while(l<=r){
            if(p==index){
                return p;
            }else if(p>index){
                r = p-1;
            }else{
                l = p+1;
            }
            p = partition(arr,l,r);
        }
        return p;
    }


    private int partition(int[] arr, int l, int r) {
        int v = l+random.nextInt(r-l+1);
        swap(arr,l,v);
        int value = arr[l];
        //[l,i-1]<value,[j+1,r]>value;
        int i = l+1,j = r;
        while(true){
            while(i<=j && arr[i]<value){
                i++;
            }
            while(j>=i && arr[j]>value){
                j--;
            }
            if(i>=j){
                break;
            }
            swap(arr,j,i);
            i++;
            j--;
        }
        swap(arr,l,j);
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
        int[] test = {4,5,1,6,2,7,3,8};
        int[] leastNumbers = new Solution17point14().samllestK2(test, 3);
        Arrays.stream(leastNumbers).forEach(System.out::println);
    }

}
