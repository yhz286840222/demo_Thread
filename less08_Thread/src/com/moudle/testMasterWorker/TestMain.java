package com.moudle.testMasterWorker;

import java.util.Random;

public class TestMain {

	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
		Master master=new Master(new Worker(), Runtime.getRuntime().availableProcessors());
		Random r=new Random();
		for (int i = 1; i <=100; i++) {
			Task task=new Task();
			task.setId(i);
			task.setName("任务"+i);
			task.setPrice(r.nextInt(1000));
			master.submit(task);
		}
		
		master.excute();
		
		long start=System.currentTimeMillis();
		while(true) {
			if(master.isComplete()) {
				long end=System.currentTimeMillis()-start;
				int result=master.getResult();
				System.out.println("任务结果集："+result+"执行时间："+end);
				break;
			}
		}
	}
}
