package sort;


import java.util.Arrays;

public class LSDSort {

    private LSDSort(){}

    private static final int MAX = 256;

    public static void sort(String[] words ,int W){

        for (String word:words){
            if(word.length()!=W){
                throw new IllegalArgumentException("all elements's length must be same.");
            }
        }

        int[] count = new int[MAX];
        int[] index = new int[MAX+1];
        String[] copy = new String[words.length];
        // O(W)
        for (int i = W-1; i >=0; i--) {

            //从尾部开始每一位都执行一次计数排序

            // 为了重复利用空间,并且使每次count数组的都是从0开始的
            Arrays.fill(count,0);
            // 统计每个字母出现的次数 O(n)
            for (String word : words) {
                count[word.charAt(i)]++;
            }

            // 计算每个字母的起始索引O(r)
            for (int x = 0; x+1 < index.length ; x++) {
                //下一个元素的起始索引= 当前元素的起始索引+当前元素的数量
                index[x+1] = index[x]+count[x];
            }
            // 放置元素O(n)
            for (String word : words) {
                // 根据该word的所排序的字母去找到他所对应的index
                int pos = index[word.charAt(i)];
                // 将其放入对应的区间
                copy[pos] = word;
                // 起始位置挪动一位
                index[word.charAt(i)]++;
            }

            System.arraycopy(copy,0,words,0,words.length);
        }
    }

    public static void main(String[] args) {

        String[] students = new String[26*26*26];

        for (int i = 0; i < students.length; i++) {
            students[i]  = ""+ (char)(Math.random()*26+'a')+(char)(Math.random()*26+'a')+(char)(Math.random()*26+'a');
        }



        sort(students,3);
        for (int i=0 ;i+1<students.length;i++){
            if(students[i].compareTo(students[i+1])>0){
                throw new RuntimeException("sort failed");
            }
//            if(students[i].getGrade()==students[i+1].getGrade()){
//                if(students[i].getName().compareTo(students[i+1].getName())>0){
//                    throw new RuntimeException("No stable counting sort");
//                }
//            }

        }
        System.out.println(students);
    }
}
