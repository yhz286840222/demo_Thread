package com.moudle.masterWorker;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Worker worker=new Worker();
		Master master=new Master(worker, 20);
		Random r=new Random();
		for (int i = 1; i <= 100; i++) {
			Task task=new Task();
			task.setId(i);
			task.setName("任务"+i);
			task.setPrice(r.nextInt(1000));
			master.submit(task);
		}
		master.execute();
		
		long start=System.currentTimeMillis();
		while(true) {
			if(master.isComplete()) {
				long end=System.currentTimeMillis()-start;
				int ret=master.getResult();
				System.out.println("最终结果："+ret+"执行耗时："+end);
				break;
			}
		}
	}
}
