package match;

import java.util.Objects;

public class SubstringMatch {

    private SubstringMatch(){}

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

    public static void main(String[] args) {
        String source = "cloudStock";
        String target = "dS";

        int match = bruteForce( source,target);
        System.out.println(source.substring(match,match+target.length()));
    }
}
