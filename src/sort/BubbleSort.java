package sort;

import common.ArrayGenerator;
import common.SortingHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class BubbleSort{

    private BubbleSort(){

    }

    public static <E extends Comparable<E>> void sort(E[] arr){

        for (int i = 0; i+1 < arr.length; i++) {

            for(int j = 1;j<arr.length-i;j++){
                if(arr[j-1].compareTo(arr[j])>0){
                    swap(arr,j,j-1);
                }
            }
        }
    }

    // 某次循环比较时一次swap都没有发生,说明此时已经有序

    public static <E extends Comparable<E>> void sort2(E[] arr){

        for (int i = 0; i+1 < arr.length; i++) {
            boolean isSwaped = false;
            for(int j = 1;j<arr.length-i;j++){
                if(arr[j-1].compareTo(arr[j])>0){
                    swap(arr,j,j-1);
                    isSwaped =true;
                }
            }
            if(!isSwaped){
                break;
            }

        }
    }

    //这轮优化,是在循环时记录最后交换位置的元素索引
    //试想最后交换元素的索引有可能就是最大的元素,也有可能最后交换的元素在最中间,说明[middle,length-1]已经有序
    // 我们理解外层循环i变量意思,可以理解为已经有几个元素已经有序
    // 即i不一定是++操作因为有可能(length-middle)>1;所以我们直接让i= arr.length-lastSwapIindex;

    public static <E extends Comparable<E>> void sort3(E[] arr){

        for (int i = 0; i+1 < arr.length;) {
            int lastSwapIindex = 0;
            for(int j = 1;j<arr.length-i;j++){
                if(arr[j-1].compareTo(arr[j])>0){
                    swap(arr,j,j-1);
                    lastSwapIindex =j;
                }
            }
            i =  arr.length-lastSwapIindex;

        }
    }

    private static <E extends Comparable<E>> void swap(E[] arr, int j, int i) {

        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int n = 100_000;
        System.out.println("random...");
        Integer[] test = ArrayGenerator.generatorRandomArray(n,n);
        Integer[] test2 = Arrays.copyOf(test,test.length);
        Integer[] test3 = Arrays.copyOf(test,test.length);
        SortingHelper.sortTest(BubbleSort.class,"sort",test);
        SortingHelper.sortTest(BubbleSort.class,"sort2",test2);
        SortingHelper.sortTest(BubbleSort.class,"sort3",test3);
        System.out.println("order...");
        test = ArrayGenerator.generatorOrderArray(n);
        test2 = Arrays.copyOf(test,test.length);
        test3 = Arrays.copyOf(test,test.length);
        SortingHelper.sortTest(BubbleSort.class,"sort",test);
        SortingHelper.sortTest(BubbleSort.class,"sort2",test2);
        SortingHelper.sortTest(BubbleSort.class,"sort3",test3);


    }
}
