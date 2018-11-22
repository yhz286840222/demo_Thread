package com.moudle.concurrentUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 * @author yanghz
 * @createDate 2018年11月19日
 */
public class UseSemaphore {
	public static void main(String[] args) {
		//线程池
		ExecutorService es=Executors.newCachedThreadPool();
		//只能5个线程同时访问
		final Semaphore semaphore=new Semaphore(5);
		//模拟20个线程同时访问
		for (int i = 0; i < 20; i++) {
			final int NO=i;
			Runnable r=new Runnable() {				
				@Override
				public void run() {					
					try {
						//拿到许可
						semaphore.acquire();
						System.out.println("Accessing: "+NO);
						//模拟实际业务逻辑
						Thread.sleep((long) (Math.random()*10000));
						//访问完毕，释放
						semaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			es.submit(r);
		}
		es.shutdown();
	}

}
