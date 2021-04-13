package otherTest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ListLearn {
    public static void main(String[] args) {
        System.out.println ();
        int a=100;
        Integer ab=Integer.valueOf(100);
        ArrayList arrayList=new ArrayList();
        LinkedList linkedList=new LinkedList();
        Vector vector=new Vector();

        HashMap hashMap=new HashMap();
        HashSet set=new HashSet();
        HashSet set1=new LinkedHashSet();
        TreeSet treeSet=new TreeSet();
        ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();

      //Z  System.out.println( Collections.binarySearch(arrayList,"AA"));

        //既定顺序
        List<String> sortStrings = Arrays.asList("香蕉", "苹果", "梨子", "芒果", "橙子");
        //需要排序
        List<String> needToSort = Arrays.asList("苹果", "香蕉", "苹果", "橙子", "芒果","梨子");
        //通过对比 需要比较元素在ArrayList的index 就可以得到比较方法
        //比for循环简洁
        List<String> stringList=needToSort.stream().sorted(Comparator.comparingInt(sortStrings::indexOf)).collect(Collectors.toList());

        sortStrings.forEach(System.out::print);
        System.out.println("");
        needToSort.forEach(System.out::print);
        System.out.println("");
        stringList.forEach(System.out::print);




    }
}
