package com.moudle.container;

import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityBlockingQueue {
	
	public static void main(String[] args) throws InterruptedException {
		PriorityBlockingQueue<Task> p=new PriorityBlockingQueue<Task>();
		Task t1=new Task(5,"任务1");
		Task t2=new Task(7,"任务2");
		Task t3=new Task(3,"任务3");
		Task t4=new Task(6,"任务4");
		Task t5=new Task(10,"任务5");
		Task t6=new Task(1,"任务6");
		Task t7=new Task(9,"任务7");
		p.put(t1);
		System.out.println(p);
		p.put(t2);
		System.out.println(p);
		p.put(t3);
		System.out.println(p);
		p.put(t4);
		System.out.println(p);
		p.put(t5);
		System.out.println(p);
		p.put(t6);
		System.out.println(p);
		p.put(t7);
		System.out.println(p);
//		System.out.println("------------------------------");
//		for (Task task : p) {
//			System.out.println(task);
//		}
		System.out.println("------------------------------");
		p.poll();
		System.out.println(p);
		p.take();
		System.out.println(p);
		p.take();
		System.out.println(p);
		p.take();
		System.out.println(p);		
	}

}
