package search;

import java.util.Arrays;

class Solution875 {


    public int minEatingSpeed(int[] piles, int h) {
        if(h<piles.length){
            return -1;
        }
        int l = 1,r = Arrays.stream(piles).max().getAsInt();
        while(l<r){
            int k = l+(r-l)/2;
            if(eatingTime(piles,k)<=h){
                r = k;
            }else{
                l = k +1;
            }
        }
        return l;
    }

    public int eatingTime(int[] arr,int k){
        int time = 0;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i]%k==0){
                time += arr[i]/k;
            }else{
                time += arr[i]/k+1;
            }
        }
        return time;
    }

    public static void main(String[] args) {
        int[] test = {3,6,7,11} ;
        Solution875 solution = new Solution875();
        int eatingTime1 = solution.eatingTime(test, 3);
        int eatingTime2 = solution.eatingTime(test,4);
        int lasted = solution.minEatingSpeed(test, 8);
        System.out.println(lasted);
        System.out.println(eatingTime1);
        System.out.println(eatingTime2);
    }
}