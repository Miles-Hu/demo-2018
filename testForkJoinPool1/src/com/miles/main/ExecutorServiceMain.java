package com.miles.main;

import java.util.stream.LongStream;

import com.miles.calculator.Calculator;
import com.miles.calculator.Impl.ExecutorServiceCalculator;

public class ExecutorServiceMain {

	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		long[] numbers = LongStream.rangeClosed(0, 100000000).toArray();
		Calculator cal = new ExecutorServiceCalculator();
		System.out.println(cal.sumUp(numbers));
		System.out.println(System.currentTimeMillis()-begin);
	}

}
