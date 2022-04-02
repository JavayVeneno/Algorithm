package match;

import common.FileOperation;
import common.MatchHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubstringMatch {

    private SubstringMatch(){}

    private static final long B = 256;

    private static final  long MOD = (long)1e9+7;


    public static int bruteForce(String source, String target)  {

        if(source == null || target == null){
            throw new IllegalArgumentException("source and target must be not null ");
        }
        if(Objects.equals(source,target)){
            return 0;
        }

        // 第一个优化点:当剩余源串长度小于子串时,可以终止匹配,并且可以处理target的length大于source的length的索引越界bug
        for (int i = 0; i < source.length()-target.length()+1; i++) {

            // 第二个优化点:内层循环中每次都得判断j是不是已经到了末尾,可以提出来在外层循环中判断,减少判断次数
            int j = 0;
            for ( ;j < target.length(); j++) {
                if(source.charAt(i+j) != target.charAt(j)){
                    break;
                }
//                if(j==target.length()-1){
//                    return i;
//                }
            }
            if(j==target.length()){
                return i;
            }
        }
        return -1;

    }

    public static int rabinKarp(String source,String target){

        if(source == null || target == null){
            throw new IllegalArgumentException("source and target must be not null");
        }
        if("".equals(target)){
            return 0;
        }

        long powB = 1;
        for (int i = 1; i < target.length(); i++) {
            powB  = powB * B % MOD;
        }


        long tHash = 0;
        for (int i = 0; i < target.length(); i++) {
            tHash = (tHash * B + target.charAt(i))%MOD;
        }

        long currentHash = 0;
        for (int i = 0; i < source.length() && i<target.length()-1; i++) {
            currentHash = (currentHash* B + source.charAt(i)  )% MOD;
        }

        for (int i = target.length()-1; i <source.length() ; i++) {
           currentHash = ( currentHash * B + source.charAt(i) )%MOD;
           if(currentHash == tHash && equals(source,i-target.length()+1,i,target)){
               return i-target.length()+1;
           }
           currentHash = ((currentHash - source.charAt(i-target.length()+1) * powB % MOD ) + MOD ) % MOD;
        }
        return -1;

    }

    public static int kmp(String source,String target){

        int[] next = getNext(target,new int[target.length()+1]);

        int i = 0,j = 0;
        while(i<source.length()&&j<target.length()){
            if(j == -1|| source.charAt(i)==target.charAt(j)){
                i++;
                j++;
            }else{
                // 如果不相等,那么j需要取next数组中的对应的值
                j = next[j];
            }
        }
        //循环完之后如存在j越界target数组则取i-j作为起始索引返回
        if(j==target.length()){
            return i-j;
        }
        return -1;
    }
//获取next数组是避免回溯的核心，这里内容久而久之还是容易忘
    private static int[] getNext(String target, int[] next) {

        int i = 0,j = -1;
        // next数组的表示:next[x] 字符串[0,x)的子串 即[0,x-1] 的最长相同真前后缀
        // 由此next[0]表示[0,-1]的位置是不存在的,所以填
        next[0] = -1;
//        int x = 1;
        while(i<target.length()){
//            System.out.printf("第%d次循环开始 i = %d ,j = %d,\t",x,i,j);
           if(j==-1 || target.charAt(i)==target.charAt(j)){
//               System.out.printf("(j == -1 || target[%d] = target[%d]), 成立 \t,",i,j);
               i++;
               j++;
               next[i] = j;
//               System.out.printf("i = %d,j = %d, next[%d] = %d,\n",i,j,i,j);
           }else{
//               System.out.printf("(j == -1 || target[%d] = target[%d]), 不成立 \t,",i,j);
               j = next[j];
//               System.out.printf("j = %d,\n",j);
           }
//           x++;

        }
        return next;
    }


    private static boolean equals(String source, int left, int right, String target) {

        for (int i = left; i <=right ; i++) {
            if(source.charAt(i)!=target.charAt(i-left)){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String source = "cloudStock";
        String target = "dS";

        MatchHelper.matchTest(SubstringMatch.class,"bruteForce",source,target);
        MatchHelper.matchTest(SubstringMatch.class,"rabinKarp",source,target);
        MatchHelper.matchTest(SubstringMatch.class,"kmp",source,target);

        String words = FileOperation.readFile("src/PrideAndPrejudice.txt");

        MatchHelper.matchTest(SubstringMatch.class,"bruteForce",words,"china");
        MatchHelper.matchTest(SubstringMatch.class,"rabinKarp",words,"china");
        MatchHelper.matchTest(SubstringMatch.class,"kmp",words,"china");


        int n = 1000000; int m = 5000;

        String aaa = IntStream.range(0, n).mapToObj(i -> "a").collect(Collectors.joining());

        String aab = IntStream.range(0, m - 1).mapToObj(i -> "a").collect(Collectors.joining("", "", "b"));

        MatchHelper.matchTest(SubstringMatch.class,"bruteForce",aaa,aab);
        MatchHelper.matchTest(SubstringMatch.class,"rabinKarp",aaa,aab);
        MatchHelper.matchTest(SubstringMatch.class,"kmp",aaa,aab);

//        根据以上测试得出一个结论:在暴力匹配子串时,常规场景下,我们的匹配大多数都是提前终止的,且子串的长度都不会太长,所有性能是非常高的
//        但是在最后一个测试用例中可以看到在特殊的场景下,是会退化到 O(n * m)的



//        String source = "cloudStock";
//        String target = "dS";

        int[] next = getNext("ABABC", new int[6]);
        System.out.println(next);

    }
}
