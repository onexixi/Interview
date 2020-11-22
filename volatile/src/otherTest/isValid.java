package otherTest;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class isValid {
    //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
    //压栈出栈， 获取第一个反括号和提他对应得正括号消除法 去处理

    public static void main(String[] args) {
        System.out.println(isValid("{[]}"));
    }
    public static boolean isValid(String s) {
        if (s.length()%2==1){
            return false;
        }
        HashMap<Character, Character> fistChr=new HashMap<>();
        fistChr.put(')','(');
        fistChr.put('}','{');
        fistChr.put(']','[');
        Deque<Character> stack = new LinkedList<Character>();

        for(int i=0;i<s.length();i++){
            char snap=s.charAt(i);
            if (fistChr.containsKey(snap)){
                if (null==stack||stack.peek() != fistChr.get(snap)){
                    return false;
                }else {
                    stack.pop();
                }
            }else {
                stack.addFirst(snap);
            }
        }

        return stack.isEmpty();
    }

    private static Character getNexchr(char snap) {
        HashMap<Character, Character> fistChr=new HashMap<>();
        fistChr.put('(',')');
        fistChr.put('{','}');
        fistChr.put('[',']');
        return fistChr.getOrDefault(snap, Character.MAX_VALUE);
    }
}
