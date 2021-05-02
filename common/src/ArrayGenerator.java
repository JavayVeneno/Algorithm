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

}
