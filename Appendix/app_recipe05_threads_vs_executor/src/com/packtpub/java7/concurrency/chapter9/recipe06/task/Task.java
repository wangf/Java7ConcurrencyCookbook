package com.packtpub.java7.concurrency.chapter9.recipe06.task;

public class Task implements Runnable {

	@Override
	public void run() {
		int r=0;
		for (int i=0; i<1000000; i++) {
			r++;
			r++;
			r*=r;
		}
		System.out.println(r);
	}

}
