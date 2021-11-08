package match;

import common.FileOperation;
import common.MatchHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String source = "cloudStock";
        String target = "dS";

        MatchHelper.matchTest(SubstringMatch.class,"bruteForce",source,target);

        String words = FileOperation.readFile("src/PrideAndPrejudice.txt");

        MatchHelper.matchTest(SubstringMatch.class,"bruteForce",words,"china");
        MatchHelper.matchTest(SubstringMatch.class,"bruteForce",words,"spring");


        int n = 100000; int m = 1000;

        String aaa = IntStream.range(0, n).mapToObj(i -> "a").collect(Collectors.joining());

        String aab = IntStream.range(0, m - 1).mapToObj(i -> "a").collect(Collectors.joining("", "", "b"));

        MatchHelper.matchTest(SubstringMatch.class,"bruteForce",aaa,aab);

        //根据以上测试得出一个结论:在暴力匹配子串时,常规场景下,我们的匹配大多数都是提前终止的,且子串的长度都不会太长,所有性能是非常高的
        //但是在最后一个测试用例中可以看到在特殊的场景下,是会退化到 O(n * m)的

    }
}
