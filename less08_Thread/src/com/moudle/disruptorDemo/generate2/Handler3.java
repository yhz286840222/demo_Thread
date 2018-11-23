package com.moudle.disruptorDemo.generate2;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.moudle.disruptorDemo.generate1.Trade;

public class Handler3 implements EventHandler<Trade>,WorkHandler<Trade>{

	@Override
	public void onEvent(Trade event) throws Exception {
		System.out.println("handler3: name: " + event.getName() + " , price: " + event.getPrice() + ";  instance: " + event.toString());
	}

	@Override
	public void onEvent(Trade arg0, long arg1, boolean arg2) throws Exception {
		this.onEvent(arg0);
	}

}
