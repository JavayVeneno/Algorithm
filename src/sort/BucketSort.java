package sort;


import common.ArrayGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BucketSort {
    private BucketSort(){}

    public static void sort(Integer[] arr,int B){
        if(B<=1){
            throw new IllegalArgumentException("B must be greater than 1");
        }
        Integer[] temp = new Integer[arr.length];

        sort(arr,0,arr.length-1,B,temp);
    }

    public static void sort2(Integer[] arr,int c){
        if(c<=0){
            throw new IllegalArgumentException("c must be greater than 0");
        }

        int maxv = Integer.MIN_VALUE;int minv = Integer.MAX_VALUE;
        //求数组最值
        for (int e:arr) {
            maxv = Math.max(maxv,e);
            minv = Math.min(minv,e);
        }

        // 根据c(每个桶最多装几个元素) 计算桶应该有多少个
        int B = (maxv-minv+1)/c + ((maxv-minv+1) % c>0?1:0);

        LinkedList<Integer>[] buckets = new LinkedList[B];

        // 每个桶都是一个链表
        for (int i = 0; i < B; i++) {
            buckets[i] = new LinkedList<>();
        }



        // 盘点元素属于哪一个桶
        for (int e:arr) {
            buckets[(e-minv)/c].add(e);
        }

        // 每个桶内部排序
        for (int i = 0; i < B; i++) {
        //冒泡排序
            Collections.sort(buckets[i]);
        }

        // 搬运
        int index = 0;
        for(int i =0;i<B;i++){
            for(int e:buckets[i]){
                arr[index ++] = e;
            }
        }


    }


    private static void sort(Integer[] arr, int left, int right, int B, Integer[] temp) {
        if(left>=right){
            return;
        }
        int maxv = Integer.MIN_VALUE;
        int minv = Integer.MAX_VALUE;
        for (int i = left; i <=right ; i++) {
            maxv = Math.max(maxv,arr[i]);
            minv = Math.min(minv,arr[i]);
        }
        if(maxv == minv){
            return;
        }
        // 计算每个桶中元素最大数量(上取整)
        int d = (maxv-minv+1)/B + ((maxv-minv+1)%B>0?1:0);

        int[] cnt = new int[B];
        int[] index = new int[B+1];

        //统计(从区间起点开始,统计每个元素应该存在那个桶中)
        for (int i = left; i <=right ; i++) {
            cnt[(arr[i]-minv)/d]++;
        }

        //计算index
        for (int i = 0; i+1 <= B; i++) {
            index[i+1] = index[i] +cnt[i];
        }

        //填充
        for (int i = left; i <= right; i++) {
            int p = index[(arr[i]-minv)/d];
            temp[p+left] = arr[i];
            index[(arr[i]-minv)/d]++;
        }

        //搬运
        for (int i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        // 单独排一下第0个桶

        sort(arr,left,left+index[0]-1,B,temp);
        // 递归
        for (int i = 0; i+1 < index.length; i++) {
            sort(arr,left+index[i],left+index[i+1]-1,B,temp);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Integer[] ints = {1,4,3,5,6,3,2,7,10,100};

        BucketSort.sort2(ints,5);
        System.out.println(ints);

        int n = 10000000;
        Integer[] test = ArrayGenerator.generatorRandomArray(n, n);
        Integer[] test2 = Arrays.copyOf(test,test.length);
        sortTest(BucketSort.class,"sort",test,5);
        sortTest(BucketSort.class,"sort2",test2,10);
    }


    public static  void sortTest(Class clazz,String sortName ,Integer[] arr,int B) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {


        Class<?> sortClass = Class.forName(clazz.getName());
        Method declaredMethod = sortClass.getMethod(sortName,Integer[].class,int.class);
        long start = System.nanoTime();
        declaredMethod.invoke(sortClass,arr,B);
        long end = System.nanoTime();
        if(!isSorted(arr)){
            throw new RuntimeException(sortName+" : sort faild ");
        }
        double use = (end-start)/1_000_000_000.0;
        System.out.printf("%s.%s %d data  use %f s %n",clazz.getName(),sortName,arr.length,use);
    }

    // 验证是否有序
    private static <E extends Comparable<E>> boolean isSorted(E[] arr){

        for (int i =1;i<arr.length;i++){
            if(arr[i-1].compareTo(arr[i])>0){
                return false;
            }
        }
        return true;
    }
}
