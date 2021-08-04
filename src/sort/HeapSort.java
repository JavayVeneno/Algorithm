package sort;

import DataStructure.heap.MaxHeap;
import common.ArrayGenerator;
import common.SortingHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class HeapSort<E extends Comparable<E>> {


    public HeapSort(){

    }

    public static <E extends Comparable<E>> void sort(E[] data){
        MaxHeap<E> heap = new MaxHeap<>();
        for (E e : data) {
            heap.add(e);
        }
        for (int i = data.length-1; i >=0 ; i--) {
            data[i] = heap.extractMax();
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generatorRandomArray(n,n);

        Integer[] arr2 = Arrays.copyOf(arr,arr.length);
        Integer[] arr3 = Arrays.copyOf(arr,arr.length);
        Integer[] arr4 = Arrays.copyOf(arr,arr.length);


        SortingHelper.sortTest(QuickSort.class,"sort",arr);
        SortingHelper.sortTest(QuickSort.class,"sort3Ways",arr2);
        SortingHelper.sortTest(MergeSort.class,"sort4",arr3);
        SortingHelper.sortTest(HeapSort.class,"sort",arr4);
    }

}
