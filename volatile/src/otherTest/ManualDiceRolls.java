package otherTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author: yangxixi
 * @Date: 2020/6/14 22:50
 */


public class ManualDiceRolls {
    //使用蒙特卡洛模拟法并行化模拟掷骰子事件
    private static final int N = 100000000;
    private final double fraction;
    private final Map<Integer, Double> results;
    private final int numberOfThreads;
    private final ExecutorService executor;
    private final int workPerThread;

    public ManualDiceRolls(double fraction, Map<Integer, Double> results, int numberOfThreads, ExecutorService executor, int workPerThread) {
        this.fraction = fraction;
        this.results = results;
        this.numberOfThreads = numberOfThreads;
        this.executor = executor;
        this.workPerThread = workPerThread;
    }


    public static void main(String[] args) {
        ManualDiceRolls roles = new ManualDiceRolls ();
        roles.simulateDiceRoles ();
    }

    public ManualDiceRolls() {
        fraction = 1.0 / N;
        results = new ConcurrentHashMap<> ();
        //获取当前系统可用线程数
        numberOfThreads = Runtime.getRuntime ().availableProcessors ();
        executor = Executors.newFixedThreadPool (numberOfThreads);
        workPerThread = N / numberOfThreads;
    }

    public void simulateDiceRoles() {
        List<Future<?>> futures = submitJobs ();
        awaitCompletion (futures);
        printResults ();
    }

    private void printResults() {
        results.entrySet ()
                .forEach (System.out::println);
    }

    private List<Future<?>> submitJobs() {
        List<Future<?>> futures = new ArrayList<> ();
        for (int i = 0; i < numberOfThreads; i++) {
            futures.add (executor.submit (makeJob ()));
        }
        return futures;
    }

    private Runnable makeJob() {
        return () -> {
            ThreadLocalRandom random = ThreadLocalRandom.current ();
            for (int i = 0; i < workPerThread; i++) {
                int entry = twoDiceThrows (random);
                accumulateResult (entry);
            }
        };
    }

    private void accumulateResult(int entry) {
        results.compute (entry, (key, previous) ->
                previous == null ? fraction
                        : previous + fraction
        );
    }

    private int twoDiceThrows(ThreadLocalRandom random) {
        int firstThrow = random.nextInt (1, 7);
        int secondThrow = random.nextInt (1, 7);
        return firstThrow + secondThrow;
    }

    private void awaitCompletion(List<Future<?>> futures) {
        futures.forEach ((future) -> {
            try {
                future.get ();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace ();
            }
        });
        executor.shutdown ();
    }
}
