package otherTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class addNumber {

        public final static int[] SUMS_ARRAY = new int[5];


        public static class Number implements Callable<Integer> {

            private int[] array;

            private int start;

            private int end;

            private int batch;

            public Number(int[] array, int start, int end, int batch) {
                this.array = array;
                this.start = start;
                this.end = end;
                this.batch = batch;
            }

            @Override
            public Integer call(){
                int sums = 0;
                for (int i = start; i < end; i++) {
                    sums += array[i];
                }
                SUMS_ARRAY[batch] = sums;
                System.out.println(Thread.currentThread().getName() + ",SUMS_ARRAY:" + sums);
                return sums;
            }
        }

        public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
            int[] array = new int[50000];
            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }

            int corePoolSize = 5;
            int maximumPoolSize = 5;
            long keepAliveTime = 0l;
            final RejectedExecutionHandler defaultHandler = new ThreadPoolExecutor.AbortPolicy();

            ExecutorService executorService = new ThreadPoolExecutor(corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(),
                    Executors.defaultThreadFactory(),
                    defaultHandler);
            List<Future<Integer>> futureList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Number number = new Number(array, (i) * 10000, (i + 1) * 10000 - 1, i);
                Future<Integer> future = executorService.submit(number);
                futureList.add(future);
            }

            Integer sums = 0;
            for(Future<Integer> future : futureList){
                Integer amount = future.get(2000,TimeUnit.MILLISECONDS);
                sums+= amount;
            }
            System.out.println("sums为:" + sums);
        }
    }


