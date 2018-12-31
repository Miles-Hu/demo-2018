package com.miles.main;

import java.util.stream.LongStream;

import com.miles.calculator.Calculator;
import com.miles.calculator.Impl.MyCalculator;

public class MyMain {
	
	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		long[] numbers = LongStream.rangeClosed(1,100000000).toArray();
		Calculator cal = new MyCalculator();
		System.out.println(cal.sumUp(numbers));
		System.out.println(System.currentTimeMillis()-begin);
	}
}
