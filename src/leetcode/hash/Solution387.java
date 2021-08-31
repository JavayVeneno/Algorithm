package leetcode.hash;

class Solution387 {
    public int firstUniqChar(String s) {
        //假如我们的hash函数是这样f(c) = c-'a';表示每一个字符从a开始对应到一个26数组上
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if(freq[s.charAt(i)-'a']==1){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution387 solution = new Solution387();
        System.out.println(solution.firstUniqChar("abbxhgfcac"));
    }
}