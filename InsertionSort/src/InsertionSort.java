import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class InsertionSort {
    private InsertionSort(){}

    // 如果这个排序有一定的优化,个人猜想是每次比较不要一一比较,
    // 而是前一个插入的数据,比前一个大插入前一个插入的后面,比他小插入前一个的前面 为了验证我的猜想,我决定实现sort2试试
    // 写了代码发现逻辑其实是不对,例如 先插入 2 ,再来一个3 ,3在2后面没问题,再来一个1,1在3前面,同时也在2后面所以不成立
    // 那么真正的优化是什么呢?是减少操作频次,每次内层循环可以减少交换次数

    // 这种实现方式,虽然有一定的优化,但是仍然是常数的优化,时间复杂度并没有改变。这个优化是jvm每次交换寻址耗费时间,改成平移数组,最后只做一次赋值

    public static <E extends Comparable<E>> void sort(E[] arr){

        for (int i = 0; i <arr.length ; i++) {

            E temp = arr[i];
            int j;
            // 1 2 3 5 6  4
            // 1 2 3   5 6  4  不需要每次都交换,只需要找到最终位置,先找一个临时变量暂存,在临时变量之前的data依次向后面平移
            for ( j = i; j > 0 && temp.compareTo(arr[j-1])<0 ; j--) {
                arr[j]=arr[j-1];
            }
            arr[j] = temp;

        }
    }

    public static <E extends Comparable<E>> void sort2(E[] arr){
        //[0,i)已经排好序,[i,n]为排序
        for(int i = 0;i<arr.length;i++) {
            //内层循环是在已排好序的数组里找合适的位置并插入
            for(int j = i; j > 0 && arr[j].compareTo(arr[j-1])<0 ;j--){
                swap(arr,j,j-1);
            }
        }
    }

    private static <E> void swap(E[] arr, int j, int i) {
        E temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int[] dataPool = {10_000,100_000};
        //InsertionSort sort 10000 data  use 0.153274 s
        //InsertionSort sort 100000 data  use 16.280516 s  看起来插入排序的时间复杂度仍然是O(n²);


        //InsertionSort.sort 10000 data  use 0.113448 s
        //InsertionSort.sort2 sort 10000 data  use 0.144966 s
        //InsertionSort.sort 100000 data  use 11.936033 s
        //InsertionSort.sort2 sort 100000 data  use 16.798068 s
        // 两种实现方式,虽然有一定的优化,但是仍然是常数的优化,时间复杂度并没有改变。这个优化是jvm每次交换寻址耗费时间,改成平移数组,最后只做一次赋值
        for (int n : dataPool) {
            Integer[] arr = ArrayGenerator.generatorRandomArray(n,n);
            Integer[] arr2 = Arrays.copyOf(arr,arr.length);
            SortingHelper.sortTest("InsertionSort",arr);
            SortingHelper.sortTest(InsertionSort.class,"sort2",arr2);
        }




    }


    // 这是预习时错误的实现,通过递归调用在数据规模上升到一定值时会导致栈溢出,做一个反面教材
    public static <E extends Comparable<E>> void sortError(E[] arr){
        //[0,i)已经排好序,[i,n]为排序
        for(int i = 0;i<arr.length;i++){
            if (i-1<0){
                continue;
            }
            if(arr[i].compareTo(arr[i-1])<0){
                swapError(arr,i,i-1);
            }
        }
    }

    // 即使是递归调用,代码逻辑也很杂乱
    private static <E extends Comparable<E>>void swapError(E[] arr, int j, int i) {
        E temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
        if( i-1>=0 && arr[i].compareTo(arr[i-1])<0){
            swapError(arr,i,i-1);
        }
    }
}
