package com.moudle.testMasterWorker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Worker implements Runnable{

	private ConcurrentLinkedDeque<Task> workQueue;
	private ConcurrentHashMap<String, Object> resultMap;
	

	public void setWorkQueue(ConcurrentLinkedDeque<Task> workQueue) {
		this.workQueue=workQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap=resultMap;		
	}
	
	@Override
	public void run() {
		while(true) {
			Task input=workQueue.poll();
			if(input==null) break;
			Object output=handle(input);
			resultMap.put(Integer.toString(input.getId()), output);
		}
	}

	private Object handle(Task input) {
		Object output=null;
		try {
			Thread.sleep(500);
			output=input.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		return output;
	}

}
