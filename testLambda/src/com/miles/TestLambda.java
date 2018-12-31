package com.miles;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

public class TestLambda {

	@Test
	public void test1() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello world!");
			}
		}).start();
		//使用Lambda表达式
		new Thread(()->System.out.println("Hello Lambda!")).start();
	}
	@Test
	public void test2() {
		List<String> features = Arrays.asList("lambdas","Default Method","Stream API","Date and Time API");
		for (String object : features) {
			//System.out.println(object);
		}
		List<String> features2 = Arrays.asList("lambdas","Default Method","Stream API","Date and Time API");
		//features2.forEach(n -> System.out.println(n));
		features2.forEach(System.out::println);
	}
	@Test
	public void test3() {
		 List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		 
		    System.out.println("Languages which starts with J :");
		    filter(languages, (str)->((String) str).startsWith("J"));
		 
		    System.out.println("Languages which ends with a ");
		    filter(languages, (str)->((String) str).endsWith("a"));
		 
		    System.out.println("Print all languages :");
		    filter(languages, (str)->true);
		 
		    System.out.println("Print no language : ");
		    filter(languages, (str)->false);
		 
		    System.out.println("Print language whose length greater than 4:");
		    filter(languages, (str)->((String) str).length() > 4);
	}
	//Predicate 是一个函数式接口，它的函数式方法是boolean test(Object obj)，那么它的lambda表达式就只能接收一个参数，返回的是lanbda表达式的值
	@SuppressWarnings("rawtypes")
	public void filter(List<String> names, Predicate condition) {
		/*for(String name:names) {
			if(condition.test(name)) {
				System.out.println(name+", ");
			}
		}*/
		//filter(Predicate p),forEach(Consumer c)都是流的方法，它们都需要一个lambda表达式作为参数传递给它们
		//names.stream().filter(name->(condition.test(name))).forEach(name->{if(name.contains("v")) {System.out.println(name+", ");}});
		//lambda表达式就是一个方法，只不过这个方法要借助一个函数式接口才能使用，它可以代表任意函数式接口，只要这个lambda表达式的格式符合那个接口的函数式方法的规范
		//写出一个lambda表达式，系统会帮我们实现它对应的函数式接口，然后根据lambda表达式重写函数式方法，如果函数式方法有返回值，那就返回lambda表达式的返回值，没有就运行lambda表达式
		Predicate condition1 = (name)->((String)name).contains("v");
		names.stream().filter(condition.and(condition1)).forEach(name->System.out.println(name));
	}
	@Test
	public void test4() {
		List<Integer> costBeforeTax = Arrays.asList(100,200,300,400,500);
		for(Integer cost:costBeforeTax) {
			//double price = cost+.12*cost;
			//System.out.println(price);
		}
		//使用lambda表达式
		costBeforeTax.stream().map(a->a*(1.2)).forEach(a->System.out.println(a));
	}
}
















