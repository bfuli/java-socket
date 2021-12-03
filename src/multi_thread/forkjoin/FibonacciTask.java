package multi_thread.forkjoin;

import java.util.concurrent.*;

/**
 * 通过fork/join计算斐波那契数列
 */
public class FibonacciTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("CPU核心数："+Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        Fibonacci fibonacci = new Fibonacci(40);

//        Future<Integer> future = forkJoinPool.submit(fibonacci);
//
//        System.out.println(future.get());
        System.out.println(forkJoinPool.invoke(fibonacci));
        long end = System.currentTimeMillis();
        System.out.println(String.format("耗时：%d millis", end-start));


    }

    static class Fibonacci extends RecursiveTask<Integer>{

        int n;
        public Fibonacci(int n){
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) return n;

            Fibonacci f1 = new Fibonacci(n-1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n-2);
            f2.fork();

            return f1.join() + f2.join();

        }
    }
}
