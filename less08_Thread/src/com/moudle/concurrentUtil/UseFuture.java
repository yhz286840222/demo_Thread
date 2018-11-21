package com.moudle.concurrentUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class UseFuture implements Callable<String>{

	private String params;
	
	public UseFuture(String params) {
		this.params=params;
	}
	@Override
	public String call() throws Exception {
		Thread.sleep(5000);
		return "根据请求参数："+params+"返回结果！";
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String params="我要大美女";
		FutureTask<String> futureTask1=new FutureTask<>(new UseFuture(params+"1"));
		FutureTask<String> futureTask2=new FutureTask<>(new UseFuture(params+"2"));
		ExecutorService es=Executors.newFixedThreadPool(3);
		//submit和execute的区别：submit有返回值,execute无返回值
		Future f=es.submit(futureTask1);
		Future f1=es.submit(new UseFuture(params+"3"));
		es.execute(futureTask2);
		System.out.println("请求完毕！");
		//f.get()任务一处理完就返回一个NULL
		System.out.println(f1.get());
		while(true){
			if(f.get()==null){
				System.out.println("任务执行完毕");
				break;
			}
		}	
		
		try {
			Thread.sleep(4000);			
			System.out.println("返回结果1："+futureTask1.get());
			System.out.println("返回结果2："+futureTask2.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		es.shutdown();
	}
}
