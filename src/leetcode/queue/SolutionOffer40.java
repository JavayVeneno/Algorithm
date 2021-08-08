package leetcode.queue;


import java.util.Arrays;
import java.util.PriorityQueue;

class SolutionOffer40 {



    public int[] smallestK(int[] arr, int k) {

//        注释掉自己实现的优先队列,改用jdk的尝试
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }

        for (int i = k; i <arr.length ; i++) {
            if(!pq.isEmpty() && arr[i]< pq.peek()){
                pq.remove();
                pq.add(arr[i]);
            }
        }

        int[] res = new int[k];

        for (int i = 0; i < k; i++) {
            res[i] = pq.remove();
        }


        return res;
    }







    public static void main(String[] args) {
        int[] test = {4,5,1,6,2,7,3,8};
        int[] leastNumbers = new SolutionOffer40().smallestK(test, 3);
        Arrays.stream(leastNumbers).forEach(System.out::println);
    }

}
