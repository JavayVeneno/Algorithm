package sort;

import common.ArrayGenerator;
import common.SortingHelper;

import java.lang.reflect.InvocationTargetException;

public class SelectionSort {

    private SelectionSort(){}

    //需要多种数据排序,所以需要加泛型约束,比较型算法一般是需要类实现Comparable接口

    public static <E extends Comparable<E>> void sort(E[] arr ){

        for (int i =0;i<arr.length;i++){
            int minIndex = i;
            for(int j = i;j<arr.length;j++){
                if(arr[j].compareTo(arr[minIndex])<0){
                    minIndex = j;
                }
            }
// 可以将数组交换的方法单独提炼出来,代码更容易阅读
//            int temp = arr[i];
//            arr[i]=arr[minIndex];
//            arr[minIndex]=temp;
            swap(arr,i,minIndex);
        }
    }

    private static <E> void swap(E[] arr, int i, int minIndex) {
        E temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex]=temp;
    }




    // 换个角度去实现选择排序,循环不变量为[0,n]未排序的,(n,length]为排完序的
    public static <E extends Comparable<E>> void sort2(E[] arr){
        //假定每排完一次,数组就变短一次,所以将length减去1;
        int x = arr.length-1;
        // 循环的时候只循环未排序的部分
        for (int i = 0;i<x;x--){
            int maxIndex = i;
            // 因为循环不变量为[0,n],所以查找的范围就是0~length
            for(int j = 0;j<=x;j++){
                if(arr[j].compareTo(arr[maxIndex])>=0){
                    maxIndex=j;
                }
            }
            swap(arr,maxIndex,x);
        }
    }





    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        Integer[] arr = {2,5,6,7,1,3,4};
//        sort(arr,false);
//        Arrays.stream(arr).forEach(System.out::println);

//        Student s1 = new Student(1L,"老刘",30);
//        Student s2 = new Student(2L,"胜英",29);
//        Student s3 = new Student(3L,"云神",28);
//        Student s4 = new Student(4L,"海军",27);
//        Student s5 = new Student(5L,"王波",26);
//        Student s6 = new Student(6L,"徐源",28);
//        Student s7 = new Student(7L,"志林",27);
//        Student s8 = new Student(8L,"康力",29);
//        Student s9 = new Student(9L,"志伟",24);
//        Student[] ennovations = {s1,s2,s3,s4,s5,s6,s7,s8,s9};
//        sort(ennovations);
//        Arrays.stream(ennovations).forEach(System.out::println);

       int[] dataPool ={10_000,100_000};

        for (int n : dataPool) {
            Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
            SortingHelper.sortTest("SelectionSort",arr);
        }
        // 从打印结果来看,10倍数据耗费近似100倍时间,选择排序的时间复杂度O(n²)得到了验证
//        SelectionSort sort 10000 data  use 0.092385 s
//        SelectionSort sort 100000 data  use 12.312721 s

    }
}
