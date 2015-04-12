package com.packtpub.java7.concurrency.chapter4.recipe3.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.packtpub.java7.concurrency.chapter4.recipe3.task.FactorialCalculator;
import com.packtpub.java7.concurrency.chapter4.recipe3.task.FibonacciTask;

/**
 * Main class of the example. Creates and execute ten FactorialCalculator tasks
 * in an executor controlling when they finish to write the results calculated
 *
 */
public class Main2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Create a ThreadPoolExecutor with fixed size. It has a maximun of two threads
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(2);
		// List to store the Future objects that control the execution of  the task and
		// are used to obtain the results
		List<Future<?>> resultList=new ArrayList<>();

		// Create a random number generator
		Random random=new Random();
		// Create and send to the executor the ten tasks
		for (int i=0; i<10; i++){
			Integer number=new Integer(random.nextInt(10));
			FibonacciTask calculator=new FibonacciTask(number);
			Future<?> result=executor.submit(calculator);
			resultList.add(result);
		}
		
		// Wait for the finalization of the ten tasks
		do {
			System.out.printf("Main: Number of Completed Tasks: %d\n",executor.getCompletedTaskCount());
			for (int i=0; i<resultList.size(); i++) {
				Future<?> result=resultList.get(i);
				System.out.printf("Main: Task %d: %s\n",i,result.isDone());
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (executor.getCompletedTaskCount()<resultList.size());
		
		// Write the results
		System.out.printf("Main: Results\n");
		for (int i=0; i<resultList.size(); i++) {
			Future<?> result=resultList.get(i);
			Object o = null;
			try {
				o=result.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.printf("Core: Task %d: %s\n",i,o);
		}
		
		// Shutdown the executor
		executor.shutdown();

	}

}
