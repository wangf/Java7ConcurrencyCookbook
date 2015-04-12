package com.packtpub.java7.concurrency.chapter2.recipe6.core;

import com.packtpub.java7.concurrency.chapter2.recipe6.task.Buffer;
import com.packtpub.java7.concurrency.chapter2.recipe6.task.Consumer;
import com.packtpub.java7.concurrency.chapter2.recipe6.task.Producer;
import com.packtpub.java7.concurrency.chapter2.recipe6.utils.FileMock;

/**
 * Main class of the example
 *
 */
public class Main {

	/**
	 * Main method of the example
	 * @param args
	 */
	public static void main(String[] args) {
		long start= System.currentTimeMillis();
		/**
		 * Creates a simulated file with 100 lines
		 */
		FileMock mock=new FileMock(101, 10);
		
		/**
		 * Creates a buffer with a maximum of 20 lines
		 */
		Buffer buffer=new Buffer(20);
		
		/**
		 * Creates a producer and a thread to run it
		 */
		Producer producer=new Producer(mock, buffer);
		Thread threadProducer=new Thread(producer,"Producer 1");
		Thread threadProducer2=new Thread(new Producer(new FileMock(101, 10), buffer),"Producer2");
		/**
		 * Creates three consumers and threads to run them
		 */
		Consumer consumers[]=new Consumer[3];
		Thread threadConsumers[]=new Thread[3];
		
		for (int i=0; i<3; i++){
			consumers[i]=new Consumer(buffer);
			threadConsumers[i]=new Thread(consumers[i],"Consumer "+i);
		}
		
		/**
		 * Strats the producer and the consumers
		 */
		threadProducer.start();
		threadProducer2.start();
		for (int i=0; i<3; i++){
			threadConsumers[i].start();
		}
		try {
			threadProducer.join();
			threadProducer2.join();
			for (int i=0; i<3; i++){
				threadConsumers[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("used %d ms\n", (System.currentTimeMillis() - start));
	}

}
