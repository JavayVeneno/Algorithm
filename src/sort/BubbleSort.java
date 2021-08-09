package sort;

import common.ArrayGenerator;
import common.SortingHelper;

import java.lang.reflect.InvocationTargetException;

public class BubbleSort<E extends Comparable<E>> {

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

    private static <E extends Comparable<E>> void swap(E[] arr, int j, int i) {

        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int n = 100_000;
        Integer[] test = ArrayGenerator.generatorRandomArray(n,n);
        SortingHelper.sortTest(BubbleSort.class,"sort",test);
    }
}
