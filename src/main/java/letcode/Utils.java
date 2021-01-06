package letcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;

/**
 * @Author: yangxixi
 * @Date: 2020/5/25 23:38
 */
public class Utils {

    //程序计时
    public static <T> T printTime(Callable<T> task) {
        T call = null;
        try {
            long startTime = System.currentTimeMillis ();
            call = task.call();
            System.out.println((System.currentTimeMillis() - startTime)   + "    ms");
        } catch (Exception e) {
            //...
        }
        return call;
    }

    //获取随机数组
    public static int[] getRandomList(int length) {
        int[] array=new int[length];
        for(int i=0;i<length;i++){
            array[i]=(int) (Math.random() * 1000 + 1);
        }
        return array ;
    }
}
