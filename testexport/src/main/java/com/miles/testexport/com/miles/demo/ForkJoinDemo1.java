package com.miles.testexport.com.miles.demo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @since 2018/8/28 0:52
 */
public class ForkJoinDemo1 extends RecursiveTask<Long> {

    private static final long MAX = 2000L;
    private static final long THRESHOLD = 1000L;
    private long start;
    private long end;

    public ForkJoinDemo1(long start, long end) {
        this.start = start;
        this.end = end;
    }


    public static void main(String[] args) {
        test();
        System.out.println("--------------------");
        testForkJoin();
    }

    private static void test() {
        System.out.println("test");
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 0L; i <= MAX; i++) {
            sum += i;
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    private static void testForkJoin() {
        System.out.println("testForkJoin");
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        Long sum = forkJoinPool.invoke(new ForkJoinDemo1(1, MAX));
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if (end - start <= THRESHOLD) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            System.out.println(Thread.currentThread().getName());
            return sum;
        } else {
            long mid = (start + end) / 2;
            System.out.println(Thread.currentThread().getName());

            ForkJoinDemo1 task1 = new ForkJoinDemo1(start, mid);
            task1.fork();

            ForkJoinDemo1 task2 = new ForkJoinDemo1(mid + 1, end);
            task2.fork();

            return task1.join() + task2.join();
        }
    }


}
