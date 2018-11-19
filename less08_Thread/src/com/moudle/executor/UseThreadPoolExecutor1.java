package com.moudle.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;

public class UseThreadPoolExecutor1 {
	
	public static void main(String[] args) {
		/**
		 * 有界的任务队列时：若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，
		 * 则优先创建线程，若大于corePoolSize，则会将任务加入队列，
		 * 若队列已满，则在总线程数不大于maximumPoolSize的前提下，
		 * 创建新的线程，若线程数大于maximumPoolSize，则执行拒绝策略。或其他自定义方式。
		 */
		ThreadPoolExecutor pool=new ThreadPoolExecutor(
				1,
				2, 
				60, 
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(3),
				new MyRejected());
		
		MyTask mt1=new MyTask(1, "任务1");
		MyTask mt2=new MyTask(2, "任务2");
		MyTask mt3=new MyTask(3, "任务3");
		MyTask mt4=new MyTask(4, "任务4");
		MyTask mt5=new MyTask(5, "任务5");
		MyTask mt6=new MyTask(6, "任务6");
		
		pool.execute(mt1);
		pool.execute(mt2);
		pool.execute(mt3);
		pool.execute(mt4);
		pool.execute(mt5);
		pool.execute(mt6);
		
		pool.shutdown();
	}
}
class MyTask implements Runnable{
	
	private int id;
	private String name;

	
	public MyTask(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "MyTask [id=" + id + ", name=" + name + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	@Override
	public void run() {		
		try {
			System.out.println("taskid:"+this.id);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

