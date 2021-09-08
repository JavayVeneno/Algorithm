package dataStructure.heap;

import dataStructure.array.Array;
import common.ArrayGenerator;

import java.util.Arrays;

public class MinHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MinHeap(){
        data = new Array<>();
    }
    public MinHeap(int initCapacity){
        data = new Array<>(initCapacity);
    }
    public MinHeap(E[] arr){
        data = new Array<>(arr);
        int lastNotLeaf = parent(arr.length-1);
        for (int i =lastNotLeaf ; i >=0 ; i--) {
            siftDown(i);
        }
    }


    public boolean isEmpty(){
        return data.isEmpty();
    }

    public int size(){
        return data.getSize();
    }

    // 查找索引k的父索引

    private int parent(int k){
        if(k<=0){
            throw new IllegalArgumentException("index-0 doesn't has parent!");
        }
        return (k-1)/2;
    }

    private int leftChild(int k){
        return 2*k+1;
    }

    private int rightChild(int k){
        return 2*k+2;
    }
    public E findMin(){
        if(data.isEmpty()){
            throw new IllegalArgumentException("MinHeap doesn't has any element !");
        }
        return data.get(0);
    }
    public void add(E e){
        data.addLast(e);
        siftUp(size()-1);
    }
    public E extractMin(){
        E res = findMin();
        data.swap(0,size()-1);
        data.removeLast();
        siftDown(0);
        return res;
    }

    private void siftDown(int k) {

        while(leftChild(k)<size()){
            int j = leftChild(k);
            if(j+1<size() && data.get(j).compareTo(data.get(j+1))>0){
                j++;
            }
            if(data.get(k).compareTo(data.get(j))<0){
                break;
            }
            data.swap(k,j);
            k = j;
        }
    }

    private void siftUp(int k) {

        while(k>0){
            int p = parent(k);
            if(data.get(k).compareTo(data.get(p))>=0){
                break;
            }
            data.swap(k,p);
            k = p;
        }
    }

    public E replace(E e){
        E res = findMin();
        data.set(0,e);
        siftDown(0);
        return res;
    }
    public static <E extends Comparable<E>> void testHeap(E[] data,boolean isHeapify) {

        long time1 = System.nanoTime();

        MinHeap<E> heap ;
        if(isHeapify){
            heap = new MinHeap<>(data);
        }else{
            heap = new MinHeap<>();
            Arrays.stream(data).forEach(heap::add);
        }

            long time2 = System.nanoTime();
            double use  = (time2 - time1) / 1_000_000_000.000000000;
            System.out.printf("%d条数据, 构建heap 耗时 %fs %n",data.length,use);


            long time3 = System.nanoTime();
        // 验证正确性
        E[] ints = (E[]) new Comparable[data.length];
        for (int i = 0; i < data.length; i++) {
            ints[i] = heap.extractMin();
        }
            long time4 = System.nanoTime();
            double use2  = (time4 - time3) / 1_000_000_000.000000000;
            System.out.printf("%d条数据,run   %d次 extractMin 耗时 %fs %n",data.length,data.length,use2);
        for (int i = 1; i <data.length ; i++) {
            if(ints[i-1].compareTo(ints[i])>0){
                throw new IllegalArgumentException("error");
            }
        }


    }
    public static void main(String[] args) {
        int n = 1_000_000;
//        Random random = new Random();
//        MinHeap<Integer> heap  = new MinHeap<>();
//         long time1 = System.nanoTime();
//        for (int i = 0 ;i< n;i++) {
//            heap.add(random.nextInt(Integer.MAX_VALUE));
//        }
//        long time2 = System.nanoTime();
//        double use  = (time2 - time1) / 1_000_000_000.000000000;
//        System.out.printf("%d条数据,run  %d次 add 耗时 %fs %n",n,n,use);
//        int[] ints = new int[n];
//        long time3 = System.nanoTime();
//        for (int i = 0; i < n; i++) {
//            ints[i] = heap.extractMin();
//        }
//        long time4 = System.nanoTime();
//        double use2  = (time4 - time3) / 1_000_000_000.000000000;
//        System.out.printf("%d条数据,run   %d次 extractMin 耗时 %fs %n",n,n,use2);
//        for (int i = 1; i <ints.length ; i++) {
//            if(ints[i-1]>ints[i]){
//                throw new IllegalArgumentException("error");
//            }
//        }

        Integer[] integers = ArrayGenerator.generatorRandomArray(n, Integer.MAX_VALUE);
        Integer[] integers2 = Arrays.copyOf(integers,integers.length);
        testHeap(integers,false);
        testHeap(integers2,true);
        System.out.println();
//        testHeap(integers2,true);
        System.out.println("heap run  add and extractMin complete!");
    }
}
