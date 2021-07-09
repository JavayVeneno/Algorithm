import java.util.Random;

public class ArrayGenerator {

    private ArrayGenerator(){}

    public static Integer[] generatorOrderArray(int n){

        Integer[] arr = new Integer[n];
        for (int i = 0; i <n ; i++) {
            arr[i]=i;
        }
        return arr;
    }

    // 指定随机数组的大小与随机数最大边界

    public static Integer[] generatorRandomArray(int n,int border){
        Integer[] arr = new Integer[n];
        Random random = new Random();
        for (int i = 0; i <n ; i++) {
            arr[i] = random.nextInt(border);
        }
        return arr;
    }

    // 生成一组数据在quick sort每次选用最中间的标定点都为此时的最小值

    public static Integer[] generatorArrayPartition(int n){

        Integer[] arr = new Integer[n];

        partitionArray(arr,0,0,arr.length-1);

        return arr;
    }

    private static void partitionArray(Integer[] arr, int value, int l, int r) {
        if(l>r){
            return;
        }
        int mid = l+(r-l)/2;
        arr[mid] = value;
        swap(arr,mid,l);
        partitionArray(arr,value+1,l+1,r);
        swap(arr,mid,l);
    }

    private static <E extends Comparable<E>> void swap(E[] arr, int x, int y) {
        E temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        Integer[] integers = generatorArrayPartition(10);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}
