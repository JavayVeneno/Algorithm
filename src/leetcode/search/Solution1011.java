package leetcode.search;

import java.util.Arrays;

class Solution1011 {
    public int shipWithinDays(int[] weights, int days) {

        int l = Arrays.stream(weights).max().getAsInt(),r  = Arrays.stream(weights).sum();
        while (l<r){
            int m = l+(r-l)/2;
            if(getDays(weights,m)<=days){
                r = m;
            }else{
                l = m+1;
            }
        }
        return l;
    }

    public int getDays(int[] weights, int m) {

        int days = 1 ;
        int cur = 0 ;
        for (int weight : weights) {
            if (cur + weight <= m) {
                cur += weight;
            } else {
                cur = weight;
                days++;
            }
        }
        return days;
    }

    public static void main(String[] args) {
        Solution1011 solution = new Solution1011();
        int[] test = {1,2,3,1,1};
        int wight = 3;
        int days = solution.getDays(test, wight);
        int wt = solution.shipWithinDays(test, 4);
        System.out.println(days);
        System.out.println(wt);
    }
}