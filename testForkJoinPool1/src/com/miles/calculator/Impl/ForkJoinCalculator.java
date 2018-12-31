package com.miles.calculator.Impl;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import com.miles.calculator.Calculator;

public class ForkJoinCalculator implements Calculator {

	private ForkJoinPool pool;
	public ForkJoinCalculator() {
		super();
		this.pool = new ForkJoinPool(8);
	}

	private static class SumTask extends RecursiveTask<Long>{

		private long[] numbers;
		private int from ;
		private int to;
		public SumTask(long[] numbers, int from, int to) {
			super();
			this.numbers = numbers;
			this.from = from;
			this.to = to;
		}


		@Override
		protected Long compute() {
			//当需要计算的个数小于6时，直接计算结果
			if(to-from<12500000) {
				long total=0;
				for(int i=from; i<=to; i++) {
					total += numbers[i];
				}
				System.out.println(Thread.currentThread().getName());
				return total;
			}else {
				int middle=(from+to)/2;
				SumTask taskLeft = new SumTask(numbers, from, middle);
				SumTask taskRight = new SumTask(numbers, middle+1, to);
				taskLeft.fork();
				taskRight.fork();
				return taskLeft.join()+taskRight.join();
			}
		}
	}
	

	@Override
	public long sumUp(long[] numbers) {
		long sum = pool.invoke(new SumTask(numbers, 0, numbers.length-1));
		System.out.println(pool.getPoolSize());
		return sum;
	}

}
