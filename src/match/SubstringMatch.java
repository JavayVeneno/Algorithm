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

        for (int i = 0; i < source.length(); i++) {

            for (int j = 0; j < target.length(); j++) {
                if(source.charAt(i+j) != target.charAt(j)){
                    break;
                }
                if(j==target.length()-1){
                    return i;
                }
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        String source = "cloudStock";
        String target = "dS";

        int match = bruteForce(source, target);
        System.out.println(source.substring(match,match+target.length()));
    }
}
