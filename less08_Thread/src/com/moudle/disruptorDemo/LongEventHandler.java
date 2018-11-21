package com.moudle.disruptorDemo;

import com.lmax.disruptor.EventHandler;
/**
 * 消费者，事件监听
 * @author Administrator
 *
 */
public class LongEventHandler implements EventHandler<LongEvent>{

	@Override
	public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
		//消费
		System.out.println(longEvent.getValue());
	}

}
