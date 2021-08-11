package sort;

import common.ArrayGenerator;
import common.SortingHelper;


import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ShellSort {

    private ShellSort(){

    }


    public static <E extends Comparable<E>> void sort(E[] arr){
        int h = 1;
        int length = arr.length;
        while(h< length){
            h= h*3+1;
        }
        while(h>=1){

            for (int i = h; i < length; i++) {

                E temp = arr[i];
                int j;
                for (j = i; j-h >=0 && temp.compareTo(arr[j-h])<0; j-=h) {

                    arr[j]  = arr[j-h];
                }
                arr[j] = temp;
            }

            h/=3;
        }

    }


    //基于sort3的基础上进行一次代码的优化,性能并没有得到优化

    public static <E extends Comparable<E>> void sort2(E[] arr){
        int length = arr.length;
        int h = length /2;
        while(h>=1){

            for (int start = h; start < length; start++) {

                E temp = arr[start];
                int j;
                for (j = start; j-h >=0 && temp.compareTo(arr[j-h])<0 ; j-=h) {
                    arr[j] = arr[j-h];
                }
                arr[j] =temp;
            }
            h/=2;
        }
    }


    //第二次实现,性能并没有优化

    public static <E extends Comparable<E>> void sort3(E[] arr){

        int h = arr.length/2;
        while(h>=1){

            for (int start = 0; start < h; start++) {

                for (int i = start; i < arr.length; i+=h) {

                    E temp = arr[i];
                    int j;
                    for ( j= i; j-h>=0 && temp.compareTo(arr[j-h])<0 ; j-=h) {

                        arr[j] = arr[j-h];
                    }
                    arr[j] = temp;
                }
            }

            h/=2;
        }
    }


    //第一次自我实现

    public static <E extends Comparable<E>> void sort4(E[] arr){

        int n = arr.length;

        // step表示间距(步长)
        for (int step = n>>1; step >0 ; step>>=1) {

            for (int start = 0; start < step; start++) {
                insertionSort(arr,start,step);
            }
        }
    }

    private static <E extends Comparable<E>> void insertionSort(E[] arr,int start ,int step){

        for (int i = start; i <arr.length; i+=step) {

            E temp = arr[i];
            int j;
            for ( j = i; j > start && temp.compareTo(arr[j-step])<0 ; j-=step) {
                arr[j]=arr[j-step];
            }
            arr[j] = temp;

        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int n = 5_00_000;
        Integer[] test = ArrayGenerator.generatorRandomArray(n,n);
        Integer[] test2 = Arrays.copyOf(test,test.length);
        Integer[] test3 = Arrays.copyOf(test,test.length);
        Integer[] test4 = Arrays.copyOf(test,test.length);
        SortingHelper.sortTest(ShellSort.class,"sort4",test4);
        SortingHelper.sortTest(ShellSort.class,"sort3",test3);
        SortingHelper.sortTest(ShellSort.class,"sort2",test2);
        SortingHelper.sortTest(ShellSort.class,"sort",test);

    }
}
