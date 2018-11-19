package com.moudle.masterWorker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

	//1.承装任务的容器
	private ConcurrentLinkedQueue<Task> workQueue=new ConcurrentLinkedQueue<>();
	//2.使用HashMap承装所有的worker对象
	private HashMap<String, Thread> workers=new HashMap<>();
	//3.使用一个容器承装每一个worker并行执行任务的结果集
	private ConcurrentHashMap<String, Object> resultMap=new ConcurrentHashMap<>();
	//4.构造方法（执行任务的worker对象，和创建worker的线程数）
	public Master(Worker worker,int workerCount) {
		//每一个worker对象都需要有Master的引用workerQueue用于任务的领取，resultMap用于任务的提交
		worker.setWorkerQueue(this.workQueue);
		worker.setResultMap(this.resultMap);
		for (int i = 0; i < workerCount; i++) {
			//key代表每个worker的名称，value表示每个worker
			workers.put("子节点"+i, new Thread(worker));
		}
	}
	//5.提交方法
	public void submit(Task task) {
		this.workQueue.add(task);
	}
	//6.需要一个执行方法（启动应用程序 让所有worker工作）
	public void execute() {
		for (Map.Entry<String, Thread> mt : workers.entrySet()) {
			mt.getValue().start();
		}
	}
	//7.判断线程是否执行完毕
	public boolean isComplete() {
		for (Map.Entry<String, Thread> mt : workers.entrySet()) {
			if(mt.getValue().getState()!=Thread.State.TERMINATED) {
				return false;
			}
		}
		return true;
	}
	//8.返回结果集数据
	public int getResult() {
		int ret=0;
		for (Map.Entry<String, Object> mt : resultMap.entrySet()) {
			ret+=(Integer)mt.getValue();
		}
		return ret;
	}
}
