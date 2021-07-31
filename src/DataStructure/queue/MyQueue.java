package DataStructure.queue;

import java.util.Random;
import java.util.Stack;

class MyQueue<E> {

    private Stack<E> enStack;
    private Stack<E> deStack;

    /** Initialize your data structure here. */
    public MyQueue() {
        enStack = new Stack<>();
        deStack = new Stack<>();
    }

    
    /** Push element x to the back of queue. */
    public void push(E x) {
        enStack.push(x);

    }
    
    /** Removes the element from in front of queue and returns that element. */
    public E pop() {
        //这个操作,如果每次放一个取一个,那么时间复杂度是O(1)的,但是如果先一直放入,后面在一次取出,那么时间复杂度可以均摊
        // 即 有一次会是O(n)+1的操作,将这个n+1的操作均摊到所有元素(n)取操作上

        if(enStack.isEmpty() && deStack.isEmpty()){
            throw new RuntimeException("can not pop from empty stack");
        }
        if (deStack.isEmpty()){
            int size = enStack.size();
            for (int i = 0; i < size; i++) {
                deStack.push(enStack.pop());
            }
        }
        return  deStack.pop();
    }

    public int getSize(){
        return deStack.size()+enStack.size();
    }
    
    /** Get the front element. */
    public E peek() {
        if(enStack.isEmpty() && deStack.isEmpty()){
            throw new RuntimeException("can not peek from empty stack");
        }
        if (deStack.isEmpty()){
            int size = enStack.size();
            for (int i = 0; i <size ; i++) {
                deStack.push(enStack.pop());
            }
        }
        return  deStack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return enStack.isEmpty() && deStack.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue queue =  new MyQueue();
//        for (int i = 0; i < 10 ; i++) {
//            queue.push(i);
//            System.out.println(queue);
//            if(i%3 == 2){
//                queue.pop();
//                System.out.println(queue);
//            }
//        }
//
//        System.out.println(queue.peek());
//        int size = queue.getSize();
//        for (int i = 0; i< size; i++) {
//            System.out.println(queue.pop());
//        }
        int[] opp = {100_000,1_000_000};
        for (int n : opp) {
            Random random = new Random();
            long start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                queue.push(random.nextInt(Integer.MAX_VALUE));
            }
            for (int i = 0; i <n ; i++) {
                queue.pop();
            }

            long end = System.nanoTime();

            double use = (end-start)/1000000000.0;
            System.out.printf("push %d 条 data  use %f s %n",n,use);

        }

    }

}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */