package com.moudle.concurrentUtil;

import java.util.concurrent.CountDownLatch;

public class UseCountDownLatch {
	
	public static void main(String[] args) {
		//初始化参数2表示需要等待两个子线程的countDown()方法的通知
		final CountDownLatch countDown=new CountDownLatch(2);
		Thread t1=new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					System.out.println("进入线程t1等待其他线程处理完成...");
					countDown.await();
					System.out.println("t1线程继续执行...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1");
		Thread t2=new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					System.out.println("t2线程进入初始化操作...");
					Thread.sleep(3000);
					System.out.println("t2线程初始化完毕，通知t1线程继续执行...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t2");
		Thread t3=new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					System.out.println("t3线程进入初始化操作...");
					Thread.sleep(5000);
					System.out.println("t3线程初始化完毕，通知t1线程继续执行...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t3");
		t1.start();
		t2.start();
		t3.start();
	}

}
