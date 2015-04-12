package com.packtpub.java7.concurrency.chapter9.recipe09.task;

import java.util.concurrent.TimeUnit;

public class Task2 implements Runnable {

	@Override
	public void run() {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

}
