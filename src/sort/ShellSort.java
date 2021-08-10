package sort;

import common.ArrayGenerator;
import common.SortingHelper;


import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ShellSort {

    private ShellSort(){

    }

    public static <E extends Comparable<E>> void sort2(E[] arr){

        int h = arr.length/2;
        while(h>=1){

            for (int start = 0; start < h; start++) {

                for (int i = start; i < arr.length; i+=h) {

                    E temp = arr[i];
                    int j;
                    for ( j= i; j -h>=0 && temp.compareTo(arr[j-h])<0 ; j-=h) {

                        arr[j] = arr[j-h];
                    }
                    arr[j] = temp;
                }
            }

            h/=2;
        }
    }


    public static <E extends Comparable<E>> void sort(E[] arr){

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
        int n = 1_000_000;
        Integer[] test = ArrayGenerator.generatorRandomArray(n,n);
        Integer[] test2 = Arrays.copyOf(test,test.length);
        Integer[] test3 = Arrays.copyOf(test,test.length);
        Integer[] test4 = Arrays.copyOf(test,test.length);
        SortingHelper.sortTest(ShellSort.class,"sort",test);
        SortingHelper.sortTest(ShellSort.class,"sort2",test2);
        SortingHelper.sortTest(QuickSort.class,"sort",test3);
        SortingHelper.sortTest(MergeSort.class,"sort",test4);

    }
}
