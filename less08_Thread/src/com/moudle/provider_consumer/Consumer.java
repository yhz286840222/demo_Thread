package com.moudle.provider_consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 * @author Administrator
 *
 */
public class Consumer implements Runnable{

	private BlockingQueue<Data> queue;
	private static Random r=new Random();
	public Consumer(BlockingQueue<Data> queue) {
		this.queue=queue;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				//获取数据
				Data data=this.queue.take();
				//随机休眠0-1000毫秒 表示数据处理（消费耗时）
				Thread.sleep(r.nextInt(1000));				
				System.out.println(Thread.currentThread().getName()+"消费："+data);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}

}
