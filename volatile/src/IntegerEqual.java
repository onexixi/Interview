import java.util.HashMap;
import java.util.Map;

public class IntegerEqual {




    public static void main(String[] args) {
//        for (int i = 0; i < 150; i++) {
//            Integer a = i;
//            Integer b = i;
//            System.out.println(i + " " + (a.equals(b)));
//        }



        Map<Integer, Integer> mapA = new HashMap<>();
        Map<Integer, Integer> mapB = new HashMap<>();
        for (int i = 0; i < 150; i++) {
            mapA.put(i, i);
            mapB.put(i, i);
        }
        for (int i = 0; i < 150; i++) {
            System.out.println(i + " " + (mapA.get(i).equals(mapB.get(i))));
        }
    }
}
