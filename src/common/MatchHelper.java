package common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MatchHelper {

    private MatchHelper(){}


    public static void matchTest(Class<?> clazz,String methodName,String source,String target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method declaredMethod = clazz.getMethod(methodName,String.class,String.class);

        long start = System.nanoTime();
        int res = (int)declaredMethod.invoke(clazz, source, target);
        long end = System.nanoTime();

        if(source.indexOf(target)!=res){
            throw new RuntimeException("match faild !");
        }

        double use = (end-start)/1_000_000_000.0;
        System.out.printf("%s.%s  res = %d  use %f s %n",clazz.getName(),methodName,res,use);

    }
}

