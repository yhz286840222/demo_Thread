package com.moudle.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledJob {
	public static void main(String[] args) {
		Temp t=new Temp();
		ScheduledExecutorService scheduled=Executors.newScheduledThreadPool(1);
		//public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
		//        long initialDelay,
		//        long delay,
		//        TimeUnit unit);command:线程任务，initialDelay:初始运行时间，delay:间隔运行时间，unit：时间单位
		ScheduledFuture<?> scheduledTask=scheduled.scheduleWithFixedDelay(t, 5, 3, TimeUnit.SECONDS);
	}
}
class Temp implements Runnable{

	@Override
	public void run() {
		System.out.println("gogogogo");
	}
	
}
