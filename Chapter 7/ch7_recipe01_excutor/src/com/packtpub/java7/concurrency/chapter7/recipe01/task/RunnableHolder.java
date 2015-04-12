package com.packtpub.java7.concurrency.chapter7.recipe01.task;

public class RunnableHolder {
	private static final ThreadLocal<Boolean> toRun = new ThreadLocal<Boolean>(){
		@Override
		protected Boolean initialValue() {
			return true;
		}
	};

	public static boolean getTorun() {
		return toRun.get();
	}

	public static void setTorun(boolean value){
		toRun.set(value);
	}
	
}
