package leetcode.match;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution187 {

    public List<String> findRepeatedDnaSequences(String s) {

        HashSet<String> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        for (int i = 0; i +10 <= s.length() ; i++) {
            String key = s.substring(i,i+10);
            if(seen.contains(key)){
                res.add(key);
            }else{
                seen.add(key);
            }
        }
        return new ArrayList<>(res);
    }



    public List<String>  findRepeatedDnaSequences2(String dna ){

        if(dna.length()<=10){
            return new ArrayList<>();
        }

        // 将四个字段映射成1234
        int[] map = new int[256];
        String word = "ACGT";

        for (int i = 0; i < word.length(); i++) {
            map[word.charAt(i)] = i+1;
        }



        // 先算出9个字符的子串的hash
        long hash = 0;
        for (int i = 0; i < 9; i++) {
            hash = hash *10  +map[dna.charAt(i)];
        }


        HashSet<Long> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();

        for (int i = 9; i < dna.length(); i++) {
            hash = hash * 10 +  map[dna.charAt(i)];
            if(seen.contains(hash)){
                // Sting的subString[)是这样的
                res.add(dna.substring(i-9,i+1));
            }else{
                seen.add(hash);
            }
            // 这里容易出现整形溢出,所以写魔法值的坏处就体现在这里,如果显示声明为一个long型的就不会有这个问题。
            // hash = hash - map[dna.charAt(i-9)] * 1000000000;
            //显示声明
            long pwo10e9 =  1000000000;
            hash = hash - map[dna.charAt(i-9)] * pwo10e9;
        }

        return new ArrayList<>(res);


    }

    public static void main(String[] args) {
        String a = "GAGAGAGAGAGA";
        System.out.println(new Solution187().findRepeatedDnaSequences2(a));
    }
}
