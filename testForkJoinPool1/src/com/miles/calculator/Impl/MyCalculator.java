package com.miles.calculator.Impl;

import com.miles.calculator.Calculator;

public class MyCalculator implements Calculator{

	@Override
	public long sumUp(long[] numbers) {
		long total = 0;
		for(long i:numbers) {
			total+=i;
		}
		System.out.println(Thread.currentThread().getName());
		return total;
	}
	
}
