package com.moudle.signal;
/**
 * 队列
 * 一个放入元素put，一个获取元素take
 * 当put时队列中的元素达到上限阻塞
 * 当take时队列中的元素为空的时候阻塞
 * @author Administrator
 *
 */

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {
	//1.申明一个集合承放元素
	private LinkedList<Object> list=new LinkedList<Object>();
	//2.count计数器
	private AtomicInteger count =new AtomicInteger(0);
	
	//3.最小/最大个数（final修饰的变量必须初始化）
	private final int minSize=0;
	private final int maxSize;
	//4.申明一把锁
	private final Object lock=new Object();
	
	public MyQueue(int len) {
		this.maxSize=len;
	}
	/**
	 * 放入值
	 * @param obj
	 */
	public void put(Object obj) {
		synchronized (lock) {
			while (count.get()==this.maxSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.add(obj);
			count.incrementAndGet();			
			lock.notify();
			System.out.println("新加入的元素为："+obj);
		}
	}
	/**
	 * 获取值
	 * @return
	 */
	public Object take() {
		Object ret=null;
		synchronized (lock) {
			while(count.get()==this.minSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}		
			ret=list.removeFirst();
			count.decrementAndGet();			
			lock.notify();			
		}
		return ret;
	}
	
	public int getSize() {
		return this.count.get();
	}
	
	public static void main(String[] args) throws InterruptedException {
		MyQueue mq=new MyQueue(5);
		mq.put("a");
		mq.put("b");
		mq.put("c");
		mq.put("d");
		mq.put("e");
		
		System.out.println("当前容器的个数："+mq.getSize());
		
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				mq.put("f");
				mq.put("g");
			}
		},"t1");
		t1.start();
		
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				Object o1=mq.take();
				System.out.println("移出的元素值为："+o1);
				Object o2=mq.take();
				System.out.println("移出的元素值为："+o2);
			}
		},"t2");
		
		TimeUnit.SECONDS.sleep(2);
		
		t2.start();
	}
}
