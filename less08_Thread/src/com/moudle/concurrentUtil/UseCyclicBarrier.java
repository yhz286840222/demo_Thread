package com.moudle.concurrentUtil;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//CyclicBarrier示例：

public class UseCyclicBarrier {	
	public static void main(String[] args) {
		//参数表示3个线程一起准备OK继续执行，少一个线程所有线程一直阻塞。多一个线程多出来的线程会一直阻塞不执行只有参数个数的线程会继续执行。
		CyclicBarrier barrier=new CyclicBarrier(3);
		Runner zs=new Runner(barrier, "张三");
		Runner ls=new Runner(barrier, "李四");
		Runner ww=new Runner(barrier, "王五");
		Runner ll=new Runner(barrier, "丽丽");
		ExecutorService executor=Executors.newFixedThreadPool(3);
		executor.execute(zs);
		executor.execute(ls);
		executor.execute(ww);
//		executor.execute(ll);
		executor.shutdown();
	}
	static class Runner implements Runnable{
		private CyclicBarrier barrier;
		private String name;
		public Runner(CyclicBarrier barrier, String name) {
			this.barrier = barrier;
			this.name = name;
		}
		@Override
		public void run() {
			try {
				int time=1000*(new Random()).nextInt(5);
				System.out.println(name+"睡眠时间："+time);
				Thread.sleep(time);
				System.out.println(name+"准备OK！");
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(name+"--gogogogo");
		}
	}
}
