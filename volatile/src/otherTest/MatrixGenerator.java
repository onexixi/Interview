package otherTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

//矩阵求积
public class MatrixGenerator {
    public static double[][] generate(int rows, int columns) {
        double[][] ret = new double[rows][columns];
        Random random = new Random();
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++) {
                ret[i][j] = random.nextDouble() * 10;
            }
        }
        return ret;
    }

    public static class SerialMultiplier {
        public static void multiply (double[][] matrix1, double[][] matrix2,
                                     double[][] result) {
            int rows1=matrix1.length;
            int columns1=matrix1[0].length;
            int columns2=matrix2[0].length;
            for (int i=0; i<rows1; i++) {
                for (int j=0; j<columns2; j++) {
                    result[i][j]=0;
                    for (int k=0; k<columns1; k++) {
                        result[i][j]+=matrix1[i][k]*matrix2[k][j];
                        //printMatrix(result,i,j);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        double[][] matrix1 = MatrixGenerator.generate(100, 100);
        double[][] matrix2 = MatrixGenerator.generate(100, 100);


        double[][] resultSerial = new double[matrix1.length]
                [matrix2[0].length];
        Date start=new Date();
//        串行 50768  并行 306797
       SerialMultiplier.multiply(matrix1, matrix2, resultSerial);
        //ParallelIndividualMultiplier.multiply(matrix1, matrix2, resultSerial);
        Date end=new Date();
        System.out.printf("Serial: %d%n",end.getTime()-start.getTime());
    }



    public static class ParallelIndividualMultiplier {
        public static void multiply(double[][] matrix1, double[][] matrix2,
                                    double[][] result) {
            List<Thread> threads=new ArrayList<>();
            int rows1=matrix1.length;
            int rows2=matrix2.length;
            for (int i=0; i<rows1; i++) {
                for (int j=0; j<rows2; j++) {
                    IndividualMultiplierTask task=new IndividualMultiplierTask
                            (result, matrix1, matrix2, i, j);
                    Thread thread=new Thread(task);
                    thread.start();
                    threads.add(thread);
                    if (threads.size() % 32 == 0) {
                        waitForThreads(threads);
                    }
                }
            }
        }
        private static void waitForThreads(List<Thread> threads){
            for (Thread thread: threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            threads.clear();
        }
    }

    private static void  printMatrix(double[][] result,int indxi,int indxj){
        int rows1=result.length;
        int rows2=result[0].length;
        System.out.println(result);
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < rows2; j++) {
                if (indxi==i&&indxj==j){
                    System.out.print("\033[32;4m" + "result[i][j]" + " "+ "\033[0m");
                }else {
                    System.out.print(result[i][j] + " ");
                }
            }
            System.out.println();
        }

    }



}
