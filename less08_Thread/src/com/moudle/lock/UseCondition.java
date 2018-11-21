package com.moudle.lock;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock的通知和等待用法
 * @author yanghz
 * @createDate 2018年11月19日
 */
public class UseCondition {
	
	private Lock lock=new ReentrantLock();
	private Condition condition=lock.newCondition();
	
	public void method1(){		
		try {
			lock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入等待状态。。。。");
			Thread.sleep(3000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"释放锁。。。。");
			condition.await();
			System.out.println("当前线程："+Thread.currentThread().getName()+"继续执行。。。。");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void method2(){		
		try {
			lock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入等待状态。。。。");
			Thread.sleep(3000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"发起唤醒锁。。。。");
			condition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final UseCondition uc=new UseCondition();
		Thread t1=new Thread(new Runnable() {
			public void run() {
				uc.method1();
			}
		},"t1");
		
		Thread t2=new Thread(new Runnable() {
			public void run() {
				uc.method2();
			}
		},"t2");
		
		t1.start();
		t2.start();
	}

}
