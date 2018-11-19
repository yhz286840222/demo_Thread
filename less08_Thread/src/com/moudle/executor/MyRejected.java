package com.moudle.executor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejected implements RejectedExecutionHandler{

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		//执行log记录等操作
		System.out.println("自定义处理。。。");
		System.out.println("当前被拒绝的任务："+r.toString());
	}

}
