package com.moudle.disruptorDemo.multi;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.ExceptionHandlerWrapper;
import com.lmax.disruptor.dsl.ProducerType;

public class Main {
	public static void main(String[] args) throws Exception{
//		RingBuffer<Order> ringBuffer=RingBuffer.create(
//				ProducerType.MULTI, new EventFactory<Order>() {
//					@Override
//					public Order newInstance() {
//						return new Order();
//					}
//				}, 1024*1024, new YieldingWaitStrategy());
		//创建ringBuffer
		RingBuffer<Order> ringBuffer=RingBuffer.createMultiProducer(new EventFactory<Order>() {

			@Override
			public Order newInstance() {
				return new Order();
			}
		}, 1024*1024, new YieldingWaitStrategy());
		//创建SequenceBarrier
		SequenceBarrier barriers=ringBuffer.newBarrier();
		//创建3个消费者实例
		Consumer[] consumers=new Consumer[3];
		for (int i = 0; i < consumers.length; i++) {
			consumers[i]=new Consumer("c"+i);			
		}
		WorkerPool<Order> workerPool=new WorkerPool<>(
				ringBuffer, barriers, new IntEventExceptionHandler(), consumers);
		//这一步的目的是把消费者的位置信息引用注入到生产者 如果只有一个消费者的情况可以省略。
		//workerPool.getWorkerSequences()获取Sequence集合
		ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
		//创建线程池
		ExecutorService executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		workerPool.start(executorService);		
		final CountDownLatch latch=new CountDownLatch(1);
		for (int i = 0; i < 100; i++) {
			final Producer producer=new Producer(ringBuffer);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						//等待生产者100个线程启动
						latch.await();
						for (int j = 0; j < 100; j++) {
							//生产数据 
							producer.onData(UUID.randomUUID().toString());
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		//等待两秒，等生产者的100个线程启动
		Thread.sleep(2000);
		System.out.println("---------------开始生产-----------------");
		latch.countDown();
		Thread.sleep(5000);
		System.out.println("总数:"+consumers[0].getCount());
		executorService.shutdown();
	}
	static class IntEventExceptionHandler implements ExceptionHandler<Order>{

		@Override
		public void handleEventException(Throwable arg0, long arg1, Order arg2) {			
		}

		@Override
		public void handleOnShutdownException(Throwable arg0) {	
		}

		@Override
		public void handleOnStartException(Throwable arg0) {
		}
		
	}
}
