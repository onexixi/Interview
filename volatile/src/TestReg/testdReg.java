package TestReg;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class testdReg {
    public static void main(String[] args) {
        String s = "11111111111111111";
        String a = "111@#x";
        String b = "%%%@#x";

        //只有数字字母和（中英文）划线的
        Predicate<String> sampleIdReg = Pattern.compile("^[a-zA-Z0-9_——-]{1,18}+$").asPredicate();

        System.out.println(sampleIdReg.test(s));
        System.out.println(sampleIdReg.test(a));
        System.out.println(sampleIdReg.test(b));
    }
}
