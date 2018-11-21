package com.moudle.disruptorDemo;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class LongEventProducer {

	private final RingBuffer<LongEvent> ringBuffer;
	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer=ringBuffer;
	}
	
	public void onData(ByteBuffer bb) {
		//可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
		long sequence=ringBuffer.next();
		try {			
			 //用上面的索引取出一个空的事件用于填充 
			LongEvent l=ringBuffer.get(sequence);
			l.setValue(bb.getLong(0));
		}catch (Exception e) {
			
		}finally {
			ringBuffer.publish(sequence);
		}
	}
}
