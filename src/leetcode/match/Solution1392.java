package leetcode.match;

public class Solution1392 {


    private long M = (long)1e9+7;

    private int B = 26;

    private long[] pow26;

    public String longestPrefix(String s) {


        pow26 = new long[s.length()];

        pow26[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            pow26[i] = pow26[i-1]*B %M;
        }


        // 先预先算出每个前缀的递减字符的hash

        long[] preHashes = new long[s.length()];
        preHashes[0] = s.charAt(0)-'a';
        for (int i = 1; i < s.length(); i++) {
            preHashes[i] = (preHashes[i-1] * B + s.charAt(i)-'a')%M;
        }

        // 再预先算出每个后缀的递减字符的hash
        long[] postHashes = new long[s.length()];
        postHashes[postHashes.length-1] = s.charAt(s.length()-1)-'a';
        for (int i = postHashes.length-2; i >=0 ; i--) {
            postHashes[i] = (postHashes[i+1] + (s.charAt(i)-'a') * pow26[pow26.length-i-1])%M;
        }



        // 用len表示子串的长度,那么左(前缀)子串的左右端点即为[0,len-1] ,右(后缀)子串的左右端点即为[length()-len,length()-1] 从后往前数(length()-1 -len +1)
        // pre[0,len-1]  post[length-len,length-1] 比较的是这两个子字符串


        for (int len = s.length()-1; len>=1; len--) {



            if(preHashes[len-1]==postHashes[s.length()-len]&&equals(s,0,len-1,s.length()-len,s.length()-1)){
                return s.substring(0,len);
            }
        }
        return "";
    }


    private boolean equals(String s, int ll, int lr, int rl, int rr) {

        for (int i = ll,j = rl; i <=lr && j<=rr ; i++,j++) {

            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        String test = "leetcodeleet";
        System.out.println(new Solution1392().longestPrefix(test));
    }
}
