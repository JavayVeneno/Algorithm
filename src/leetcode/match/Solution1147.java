package leetcode.match;

class Solution1147 {

    private static int B = 26;

    private static long M = (long)1e9+7;

    private long[] pow26;

    // 正版
    public int longestDecomposition(String text) {

        pow26 = new long[text.length()];
        pow26[0] = 1;

        for (int i = 1; i < text.length(); i++) {
            pow26[i] = pow26[i-1]*B %M ;
        }
        return slove(text,0,text.length()-1);
    }

    private int slove(String text,int left,int right){
        if(right<left){
            return 0;
        }



        long preHash = 0,postHash = 0;
        for (int i = left,j = right; i < j; i++,j--) {

            // 可以算hash值来进行短路判断
            preHash = (preHash * B  + text.charAt(i)-'a')%M;

            postHash = ((text.charAt(j)-'a') * pow26[right-j]  + postHash)%M;

            if(preHash==postHash&&equals(text,left,i,j,right)){
                return 2+slove(text,i+1,j-1);
            }
        }
        return 1;

    }

    // text 原串 ll左的左起点 lr左的右端点 rl右的左起点,rr右的右起点
    private boolean equals(String text, int ll, int lr,int rl,int rr) {

        for (int i = ll,j = rl; i <=lr && j<=rr ; i++,j++) {
            if(text.charAt(i)!=text.charAt(j)){
                return false;
            }
        }
        return true;
    }


    //简版 不使用hash
    public int longestDecompositionNotUseHash(String text) {

        return sloveNotUseHash(text,0,text.length()-1);
    }

    private int sloveNotUseHash(String text,int left,int right){
        if(right<left){
            return 0;
        }

        for (int i = left,j = right; i < j; i++,j--) {

            if(equals(text,left,i,j,right)){
                return 2+sloveNotUseHash(text,i+1,j-1);
            }
        }
        return 1;

    }


    public static void main(String[] args) {

        String  test = "qkosrybjochsmjfvfxllbuqwmttnpeodqiiesdywtxjdssyoznntxjdssyoznqiiesdywqkosrybjochsmjfvfxllbuqwmttnpeod";
        System.out.println(new Solution1147().longestDecomposition(test));
    }



}