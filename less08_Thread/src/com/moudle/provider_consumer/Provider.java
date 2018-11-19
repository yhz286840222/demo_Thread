package com.moudle.provider_consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
  *  生产者 
 * @author Administrator
 *
 */
public class Provider implements Runnable{
	//共享缓存区
	private BlockingQueue<Data> queue;
	//标识线程是否运行的变量，volatile外部主线程可见性
	private volatile boolean isRunning=true;
	//原子类 id生成器
	private static AtomicInteger count=new AtomicInteger(0);
	//随机对象
	private Random r=new Random();
	public Provider(BlockingQueue<Data> queue) {
		this.queue=queue;
	}

	@Override
	public void run() {
		while(isRunning) {
			try {
				//随机休眠0-1000毫秒 表示获取数据（产生数据的耗时）
				Thread.sleep(r.nextInt(1000));
				//获取id数据累计
				int id=count.incrementAndGet();
				Data data=new Data();		
				data.setId(id);
				data.setName("数据"+id);				
				System.out.println(Thread.currentThread().getName()+"生产数据："+data+"，进行装载到缓冲区");
				if(!this.queue.offer(data,2,TimeUnit.SECONDS)) {
					System.out.println("提交缓冲区数据失败。。。。");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		this.isRunning=false;
	}
}
