import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Parallel {
    public static long sum(int n, long N, int numThreads) throws InterruptedException {
        long chunkSize = N / numThreads;
        long result = 0;

        class SumTask implements Runnable {
            final int n;
            long start;
            long end;
            long localSum;

            SumTask(int n, long start, long end) {
                this.n = n;
                this.start = start;
                this.end = end;
            }

            @Override
            public void run() {
                localSum = 0;
                for (long i = start; i <= end; i++) {
                    localSum += i * n;
                }
            }

            public long getLocalSum() {
                return localSum;
            }
        }

        SumTask[] tasks = new SumTask[numThreads];
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            long start = i * chunkSize + 1;
            long end = (i == numThreads - 1) ? N : start + chunkSize - 1;
            SumTask task = new SumTask(n, start, end);
            tasks[i] = task;
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        for (SumTask task : tasks) {
            result += task.getLocalSum();
        }

        return result;
    }
}
