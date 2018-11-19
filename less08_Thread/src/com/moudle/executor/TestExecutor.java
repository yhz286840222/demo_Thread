package com.moudle.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutor {
	public static void main(String[] args) {
		ExecutorService pool1=Executors.newFixedThreadPool(10);
		ExecutorService pool2=Executors.newSingleThreadExecutor();
		ExecutorService pool3=Executors.newCachedThreadPool();
		ExecutorService pool4=Executors.newScheduledThreadPool(5);
	}
}
