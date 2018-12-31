package com.miles.calculator.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.miles.calculator.Calculator;

public class ExecutorServiceCalculator implements Calculator{
	//核心：sumUp方法负责切分任务，subTask对象的call方法负责计算结果，生成的线程数基于处理器数量
	//基于处理器数量生成线程数，这样能最大限度地使用电脑处理器
	private int parallism;
	private ExecutorService pool;
	
	public ExecutorServiceCalculator() {
		super();
		this.parallism = Runtime.getRuntime().availableProcessors();
		this.pool = Executors.newFixedThreadPool(parallism);
		System.out.println(parallism);
	}
	private static class SumTask implements Callable<Long>{
		private long[] numbers;
		private int from;
		private int to;
		public SumTask(long[] numbers, int from, int to) {
			super();
			this.numbers = numbers;
			this.from = from;
			this.to = to;
		}
		@Override
		public Long call() throws Exception {
			long total = 0;
			for(int i=from; i<=to; i++) {
				total += numbers[i];
			}
			System.out.println(Thread.currentThread().getName());
			return total;
		}
	}
	
	@Override
	public long sumUp(long[] numbers) {
		List<Future<Long>> results = new ArrayList<>();
		//把任务分解成n份，交给n个线程处理
		int part = numbers.length/parallism;
		for(int i=0; i<parallism; i++) {
			int from =i*part;
			int to = (i+1)*part-1;
			results.add(pool.submit(new SumTask(numbers, from, to)));
		}
		//把每个线程的结果相加，得到最终的结果
		long total = 0l;
		for(Future<Long> f:results) {
			try {
				total += f.get();
			}catch (Exception ignore) {}
		}
		pool.shutdown();
		return total;
	}
}
