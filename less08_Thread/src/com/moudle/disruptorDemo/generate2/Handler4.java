package com.moudle.disruptorDemo.generate2;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.moudle.disruptorDemo.generate1.Trade;

public class Handler4 implements EventHandler<Trade>,WorkHandler<Trade>{

	@Override
	public void onEvent(Trade trade) throws Exception {
		System.out.println("handler4 set addName:");
		trade.setName(trade.getName()+"h4");
	}

	@Override
	public void onEvent(Trade arg0, long arg1, boolean arg2) throws Exception {
		this.onEvent(arg0);
	}

}
