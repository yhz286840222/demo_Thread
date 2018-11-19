package com.moudle.testMasterWorker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Master {
	
	//非阻塞高性能的队列承装所有的任务
	private ConcurrentLinkedDeque<Task> workQueue=new ConcurrentLinkedDeque<>();
	//承装每个线程work
	private Map<String, Thread> workers=new HashMap<>();
	//承装每个work返回的集合
	private ConcurrentHashMap<String, Object> resultMap=new ConcurrentHashMap<>();
	//封装workers和设置每个worker里面的workQueue、resultMap的引用
	public Master(Worker worker,int workerCount) {
		worker.setWorkQueue(workQueue);
		worker.setResultMap(resultMap);
		for (int i = 0; i < workerCount; i++) {
			workers.put("子节点"+i, new Thread(worker));
		}		
	}
	//提交任务
	public void submit(Task task) {
		workQueue.add(task);
	}
	//执行任务
	public void excute() {
		for (Map.Entry<String, Thread> me : workers.entrySet()) {
			me.getValue().start();
		}
	}
	//判断是否执行完毕
	public Boolean isComplete() {
		for (Map.Entry<String, Thread> me : workers.entrySet()) {
			if(me.getValue().getState()!=Thread.State.TERMINATED) {
				//terminated
				return false;
			}
		}
		return true;
	}
	
	//返回结果集
	public int getResult() {
		int ret=0;
		for (Map.Entry<String, Object> me : resultMap.entrySet()) {
			ret+=(Integer) me.getValue();
		}
		return ret;
	}
}
