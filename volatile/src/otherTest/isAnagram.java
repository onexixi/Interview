package otherTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class isAnagram {
    //给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
    public static void main(String[] args) {
    System.out.println(isAnagram("aacc","ccac"));
    }

    public static boolean isAnagram(String s, String t) {

        List<Character> setS=new ArrayList<>();
        List<Character> setT=new ArrayList<>();

        for(int i=0;i<s.length();i++){
            setS.add(s.charAt(i));
        }
        for(int i=0;i<t.length();i++){
            setT.add(t.charAt(i));
        }
        if (s.length()!=t.length()){
            return false;
        }
        for(int i=0;i<t.length();i++){
            if (!setS.contains(s.charAt(i))){
                return false;
            }
        }
        setS=setS.stream().sorted().collect(Collectors.toList());
        setT=setT.stream().sorted().collect(Collectors.toList());
        for(int i=0;i<t.length();i++){
            if (setT.get(i).charValue()!=setS.get(i).charValue()){
                return false;
            }

        }


        return true;
    }

}
