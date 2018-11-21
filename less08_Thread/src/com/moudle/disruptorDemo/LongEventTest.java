package com.moudle.disruptorDemo;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class LongEventTest {

	public static void main(String[] args) {
		ExecutorService executor=Executors.newCachedThreadPool();
		LongEventFactory eventFactory=new LongEventFactory();
		//必须2的N次方
		int ringBufferSize = 1024*1024;
		/**
		//BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现
		WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
		//SleepingWaitStrategy 的性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景
		WaitStrategy SLEEPING_WAIT = new SleepingWaitStrategy();
		//YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性
		WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
		*/
		Disruptor<LongEvent> dis=new Disruptor<>(eventFactory, ringBufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());
		dis.handleEventsWith(new LongEventHandler());
		
		dis.start();
		RingBuffer<LongEvent> ringBuffer=dis.getRingBuffer();
		//LongEventProducer producer=new LongEventProducer(ringBuffer);
		LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
		ByteBuffer bb=ByteBuffer.allocate(8);
		for (int i = 0; i < 100; i++) {
			bb.putLong(0,i);
			producer.onData(bb);
		}
		dis.shutdown();
		executor.shutdown();		
	}
	
}
