package com.packtpub.java7.concurrency.chapter2.recipe5.core;

import com.packtpub.java7.concurrency.chapter2.recipe5.task.Job;
import com.packtpub.java7.concurrency.chapter2.recipe5.task.PrintQueue;

/**
 * Main class of the example
 *
 */
public class Main {
	
	/**
	 * Main method of the example
	 * @param args
	 */
	public static void main (String args[]){
		long start=System.currentTimeMillis();
		// Creates the print queue
		PrintQueue printQueue=new PrintQueue();
		
		// Cretes ten jobs and the Threads to run them
		Thread thread[]=new Thread[1000];
		for (int i=0; i<thread.length; i++){
			thread[i]=new Thread(new Job(printQueue),"Thread "+i);
		}
		
		// Launch a thread ever 0.1 seconds
		for (int i=0; i<thread.length; i++){
			thread[i].start();
		}
		for (int i=0; i<thread.length; i++){
			try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("used %d ms\n",(System.currentTimeMillis() - start));
	}

}
