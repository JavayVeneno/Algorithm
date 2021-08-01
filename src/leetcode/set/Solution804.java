package leetcode.set;

import java.util.TreeSet;

class Solution804 {

    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        TreeSet<String> set  = new TreeSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(codes[(word.charAt(i)-'a')]);
            }
            set.add(sb.toString());
        }
        return set.size();
    }

    public static void main(String[] args) {
        String[] words = {"hello","world","hello"};
        Solution804 solution = new Solution804();
        System.out.println(solution.uniqueMorseRepresentations(words));
    }
}