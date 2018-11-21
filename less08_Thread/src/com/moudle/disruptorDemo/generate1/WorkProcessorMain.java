package com.moudle.disruptorDemo.generate1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.WorkerPool;

public class WorkProcessorMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int BUFFER_SIZE = 1024;
		int THREAD_NUMBERS = 4;
		EventFactory<Trade> eventFactory = new EventFactory<Trade>() {

			@Override
			public Trade newInstance() {
				return new Trade();
			}
		};
		final RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(eventFactory, BUFFER_SIZE);
		SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
		ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);
		WorkHandler<Trade> handler = new TradeHandler();
		WorkerPool<Trade> workerPool = new WorkerPool<>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(),
				handler);
		workerPool.start(executors);
		// 如果存在多个消费者，那么重复执行上面三行代码，把TradeHandler换成其他消费者类
		Future<?> future = executors.submit(new Callable<Trade>() {
			@Override
			public Trade call() throws Exception {
				long seq;
				for (int i = 0; i < 10; i++) {
					seq = ringBuffer.next();// 占一个坑-----ringBuffer一个可用区块
					ringBuffer.get(seq).setPrice(Math.random() * 9999);// 给这个区块放入数据
					ringBuffer.publish(seq);// 发布这个区块的数据使handler(consumer)可见
				}
				return null;
			}
		});

		future.get();// 等待生成者结束
		Thread.sleep(1000);// 等待一秒，等消费者处理完成
		workerPool.halt();// 通知事件（或者说消息）处理器，可以结束了（并不是马上结束）
		executors.shutdown();// 终止线程
	}

}
