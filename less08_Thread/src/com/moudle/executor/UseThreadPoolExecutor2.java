package com.moudle.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class UseThreadPoolExecutor2 implements Runnable{

	private static AtomicInteger count=new AtomicInteger(0);
	@Override
	public void run() {
		try {
			int temp=count.incrementAndGet();
			System.out.println("任务"+temp);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		//无界队列
		LinkedBlockingQueue<Runnable> queue=new LinkedBlockingQueue<>();
		ExecutorService exService=new ThreadPoolExecutor(
				5, 
				10, 
				120L, 
				TimeUnit.SECONDS, 
				queue);
		for(int i=0;i<20;i++) {
			exService.execute(new UseThreadPoolExecutor2());
		}
		Thread.sleep(2000);
		System.out.println("queue size:"+queue.size());		
	}
}
