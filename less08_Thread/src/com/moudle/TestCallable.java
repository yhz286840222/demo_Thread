package com.moudle;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class TestCallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Integer> call=new MyCallable();
		FutureTask<Integer> ft=new FutureTask<>(call);
		Thread t=new Thread(ft);
		t.setName("线程1");
		t.start();
		System.out.println("sum="+ft.get());
		Callable<Integer> call1=new MyCallable();
//		FutureTask<Integer> ft1=new FutureTask<>(call1);
//		Thread t1=new Thread(ft1);
//		t1.setName("线程2");
//		t1.start();		
//		System.out.println("线程2sum="+ft1.get());
		ExecutorService executor=Executors.newFixedThreadPool(1);
		Future f=executor.submit(call1);
		Thread.sleep(5000);
		System.out.println("------------"+f.get());
		executor.shutdown();
	}
	
}
class MyCallable implements Callable<Integer>{
	private int i;
	
	@Override
	public Integer call() throws Exception {
		int sum=0;
		System.out.println("当前线程："+Thread.currentThread().getName());
		for (;i<100;i++) {
			System.out.println(Thread.currentThread().getName()+"=="+i);
			sum+=i;
		}
		return sum;
	}
	
}