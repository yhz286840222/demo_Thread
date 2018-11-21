package com.moudle.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
/**
 * 读写锁
 * @author yanghz
 * @createDate 2018年11月19日
 */
public class UseReentrantReadWriteLock {

	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private ReadLock readLock = readWriteLock.readLock();
	private WriteLock writeLock = readWriteLock.writeLock();

	public void read() {
		try {
			readLock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入。。。");
			Thread.sleep(3000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "退出。。。");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
	}

	public void write() {
		try {
			writeLock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入。。。");
			Thread.sleep(3000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "退出。。。");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
	}

	public static void main(String[] args) {
		final UseReentrantReadWriteLock urr = new UseReentrantReadWriteLock();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				urr.read();
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				urr.read();
			}
		}, "t2");
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				urr.write();
			}
		}, "t3");

		Thread t4 = new Thread(new Runnable() {

			@Override
			public void run() {
				urr.write();
			}
		}, "t4");
		
//		t1.start();//R
//		t2.start();//R
		t3.start();//w
		t4.start();//w
		//RR
//		当前线程：t1进入。。。
//		当前线程：t2进入。。。
//		当前线程：t1退出。。。
//		当前线程：t2退出。。。
		//RW
//		当前线程：t3进入。。。
//		当前线程：t3退出。。。
//		当前线程：t2进入。。。
//		当前线程：t2退出。。。
		//RR
//		当前线程：t4进入。。。
//		当前线程：t4退出。。。
//		当前线程：t3进入。。。
//		当前线程：t3退出。。。
	}
}
