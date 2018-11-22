package com.moudle.disruptorDemo.multi;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;

public class Main {
	public static void main(String[] args) {
//		RingBuffer<Order> ringBuffer=RingBuffer.create(
//				ProducerType.MULTI, new EventFactory<Order>() {
//					@Override
//					public Order newInstance() {
//						return new Order();
//					}
//				}, 1024*1024, new YieldingWaitStrategy());
		RingBuffer<Order> ringBuffer=RingBuffer.createMultiProducer(new EventFactory<Order>() {

			@Override
			public Order newInstance() {
				return new Order();
			}
		}, 1024*1024, new YieldingWaitStrategy());
		SequenceBarrier barriers=ringBuffer.newBarrier();
		Consumer[] consumers=new Consumer[3];
		for (int i = 0; i < consumers.length; i++) {
			consumers[i]=new Consumer();			
		}
	}
}
