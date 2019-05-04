import java.util.List;

/**
 * @Author: yangxixi
 * @Date: 2019/5/4 15:17
 */
public class SpiralMatrix {

    //螺旋矩阵 将输入矩阵按逆时针输出

    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            int a = matrix.length;

            return null;
        }
    }

    //不含AAA BBB的输出
    public static String strWithout3a3b(int A, int B) {
        String S = "";
        String a = "A";
        String b = "B";
        if (A>B){
            for (int i = 0; i < A; i++) {
                S = String.format ("%s%s", S, a);
                if(i<B){
                    S = S + b;
                }
            }
        }else {
            for (int i = 0; i < B; i++) {
                S = String.format ("%s%s", S, a);
                if(i<A){
                    S = S + b;
                }
            }

        }


        return S;
    }

    public static void main(String[] args) {
        System.out.println (strWithout3a3b (2, 2));
    }
}
