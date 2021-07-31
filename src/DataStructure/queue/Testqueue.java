package DataStructure.queue;

import DataStructure.queue.ArrayQueue;
import DataStructure.queue.LoopQueue;
import DataStructure.queue.LoopQueueNotUseSize;
import DataStructure.queue.Queue;

import java.util.Random;

public class Testqueue {

    private  static double testQueue(Queue<Integer> q, int optCount ){


        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < optCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i <optCount ; i++) {
            q.dequeue();
        }

        long end = System.nanoTime();

        return (end-start)/1000000000.0;
    }
    public static void main(String[] args) {

        int optCount = 500000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        LoopQueueNotUseSize<Integer> loopQueueNotUseSpace = new LoopQueueNotUseSize<>();
        double loopTime = testQueue(loopQueue, optCount);
        double arrayTime = testQueue(loopQueueNotUseSpace, optCount);

        //LoopQueue use 0.015788 s
        //ArrayQueue use 3.408632 s
        // 从多次测试打印结果来看,LoopQue的出队操作O(1)*test的n次=O(n)是远快于ArrayQueue的出队操作O(n)*test的n次循环=O(n²)的
        System.out.printf("LoopQueue use %f s %n",loopTime);
        System.out.printf("ArrayQueue use %f s %n",arrayTime);

    }
}
