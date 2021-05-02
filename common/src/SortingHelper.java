import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SortingHelper {

    private SortingHelper(){}

    public static  <E extends Comparable<E>>  void sortTest(String sortName,E[] arr) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        Class<?> sortClass = Class.forName(sortName);
        //在这遇到了一点小问题,直接getMethod(String name)拿去不到方法需要加入参类型,我们的入参类型是实现比较器接口的参数,所以需要比较器数组
        Method declaredMethod = sortClass.getMethod("sort",Comparable[].class);

        long start = System.nanoTime();
        // 因为invoke方法的入参是(object和一个参数的Object[],所以我们的目标参数Integer[]是一个参数需要转成object(而不是invoke的多个参数)
        declaredMethod.invoke(sortClass,(Object)arr);
        long end = System.nanoTime();
        if(!isSorted(arr)){
            throw new RuntimeException(sortName+" : sort faild ");
        }
        double use = (end-start)/1_000_000_000.0;
        System.out.printf("%s sort %d data  use %f s %n",sortName,arr.length,use);

    }

    // 验证是否有虚
    private static <E extends Comparable<E>> boolean isSorted(E[] arr){

        for (int i =1;i<arr.length;i++){
            if(arr[i-1].compareTo(arr[i])>0){
                return false;
            }
        }
        return true;
    }
}
