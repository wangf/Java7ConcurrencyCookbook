package com.packtpub.java7.concurrency.chapter4.recipe3.task;

public class FibonacciTask implements Runnable {
	private long num;
	
	
	public FibonacciTask(long num) {
		super();
		this.num = num;
	}


	@Override
	public void run() {
		long result=fibonacci(num);
		System.out.printf("%s: %d\n",Thread.currentThread().getName(),result);
	}
	
	public static long fibonacci(long n){
		if (n <= 1) {
			return n;
		} else {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}

}
