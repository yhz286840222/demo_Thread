package com.moudle.disruptorDemo.generate2;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.moudle.disruptorDemo.generate1.Trade;

public class Handler2 implements EventHandler<Trade>,WorkHandler<Trade>{

	@Override
	public void onEvent(Trade trade) throws Exception {
		System.out.println("handler2 set price:");
		trade.setPrice(17);
		Thread.sleep(1000);
	}

	@Override
	public void onEvent(Trade arg0, long arg1, boolean arg2) throws Exception {
		this.onEvent(arg0);
	}

}
