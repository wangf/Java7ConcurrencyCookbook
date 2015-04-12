package com.packtpub.java7.concurrency.chapter4.recipe10.core;

import java.util.concurrent.FutureTask;


public class FutureCallableDemo {
	public static long fibonacci(long n) {
		if (n <= 1) {
			return n;
		} else {
			return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		class Result {
			private long result;
		}
		final Result r = new Result();
		FutureTask<Result> fib30 = new FutureTask<>(new Runnable() {
			@Override
			public void run() {
				r.result = fibonacci(30);
			}

		}, r);
		System.out.println("老闆,我要第30個費式數,待會來拿...");
		new Thread(fib30).start();
		System.out.println("忙別的事去...");
		try {
			Thread.sleep(5000);
			System.out.printf("第30個費式數:%d%n", fib30.get().result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
