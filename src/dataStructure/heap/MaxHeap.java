package dataStructure.heap;

import dataStructure.array.Array;
import common.ArrayGenerator;

import java.util.Arrays;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(){
        data = new Array<>();
    }
    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }
    // heapify的方式来创建堆,对比(add()*n = O(logn)*n )可以优化到O(n)的时间复杂度

    public MaxHeap(E[] arr){
        data = new Array<E>(arr);
        int lastParent = parent(arr.length-1);
        for (int i = lastParent; i >=0 ; i--) {
            siftDown(i);
        }
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 获取指定索引的父亲节点索引

    public int parent(int index){
        if(index==0){
            throw new IllegalArgumentException("index 0 hasn't parent node!");
        }
        // 如果数组以1为起始，则i = index/2；
        return (index-1)/2;
    }

    public int leftChild(int index){
        // 如果数组以1为起始，则i = index*2；
        return index*2+1;
    }

    public int rightChild(int index){
        // 如果数组以1为起始，则i = index*2+1；
        return index*+2;
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    private void siftUp(int k) {

        while(k>0 && data.get(parent(k)).compareTo(data.get(k))<0){
            data.swap(k,parent(k));
            k = parent(k);
        }
    }

    public E extractMax(){
        //如何实现取出最大元素呢？
        // 首先我们找到最大元素暂存
        E res = findMax();

        // 如果我们直接删除最大元素，需要面临合并两颗二叉树的问题，
        // 所以其次我们需要将最末节点放到首位
        data.swap(size()-1,0);
        // 移除末尾元素
        data.removeLast();
        // 最后siftDown下沉该元素
        siftDown(0);

        return res;
    }

    private void siftDown(int k) {
        // 左还有子节点说明需要继续下沉元素
        while(leftChild(k)<size()){
            int j = leftChild(k);
            // 下沉时，发现左边有，右边也有，并且右边大于左边
            if(j+1<size()&&data.get(j+1).compareTo(data.get(j))>0){
                // j取右边，因为右子更大
                j ++;
            }
            // 否则j仍然取左边；

            // 得到二者之间较大者与当前比较
            if(data.get(j).compareTo(data.get(k))<=0){
                // 当前已经最大，跳出
                break;
            }
            // 否则，jk互换，k取下沉索引值
            data.swap(j,k);
            k = j;
        }
    }

    public E findMax() {

        if(isEmpty()){
            throw new IllegalArgumentException("can't find max from empty heap!");
        }
        return data.get(0);
    }
    // 将最大元素替换成输入元素，并且维持堆的特性。
    public E replace(E e){
        E res = findMax();
        data.set(0,e);
        siftDown(0);
        return res;
    }

    public static <E extends Comparable<E>> void testHeap(E[] data,boolean isHeapify) {

        long time1 = System.nanoTime();

        MaxHeap<E> heap ;
        if(isHeapify){
            heap = new MaxHeap<>(data);
        }else{
            heap = new MaxHeap<>();
            Arrays.stream(data).forEach(heap::add);
        }

        long time2 = System.nanoTime();
        double use  = (time2 - time1) / 1_000_000_000.000000000;
        System.out.printf("%d条数据, 构建heap 耗时 %fs %n",data.length,use);


        long time3 = System.nanoTime();
        // 验证正确性
        E[] ints = (E[]) new Comparable[data.length];
        for (int i = 0; i < data.length; i++) {
            ints[i] = heap.extractMax();
        }
        long time4 = System.nanoTime();
        double use2  = (time4 - time3) / 1_000_000_000.000000000;
        System.out.printf("%d条数据,run   %d次 extractMax 耗时 %fs %n",data.length,data.length,use2);
        for (int i = 1; i <data.length ; i++) {
            if(ints[i-1].compareTo(ints[i])<0){
                throw new IllegalArgumentException("error");
            }
        }


    }


    public static void main(String[] args) {

        int n = 1_000_000;
//        Random random = new Random();
//        MaxHeap<Integer> heap  = new MaxHeap<>();
//        long time1 = System.nanoTime();
//        for (int i = 0 ;i< n;i++) {
//            heap.add(random.nextInt(Integer.MAX_VALUE));
//        }
//        long time2 = System.nanoTime();
//        double use  = (time2 - time1) / 1_000_000_000.000000000;
//        System.out.printf("%d条数据,run  %d次 add 耗时 %fs %n",n,n,use);
//        int[] ints = new int[n];
//        long time3 = System.nanoTime();
//        for (int i = 0; i < n; i++) {
//            ints[i] = heap.extractMax();
//        }
//        long time4 = System.nanoTime();
//        double use2  = (time4 - time3) / 1_000_000_000.000000000;
//        System.out.printf("%d条数据,run   %d次 extractMax 耗时 %fs %n",n,n,use2);
//        for (int i = 1; i <ints.length ; i++) {
//            if(ints[i-1]<ints[i]){
//                throw new IllegalArgumentException("error");
//            }
//        }
//1000000条数据, 构建heap 耗时 0.108041s
//1000000条数据,run   1000000次 extractMax 耗时 0.808992s
//1000000条数据, 构建heap 耗时 0.043333s
//1000000条数据,run   1000000次 extractMax 耗时 0.791310s

        Integer[] integers = ArrayGenerator.generatorRandomArray(n, Integer.MAX_VALUE);
        Integer[] integers2 = Arrays.copyOf(integers,integers.length);
        testHeap(integers,true);
        System.out.println();
//        testHeap(integers2,true);
        System.out.println("heap run  add and extractMax complete!");
    }
}
