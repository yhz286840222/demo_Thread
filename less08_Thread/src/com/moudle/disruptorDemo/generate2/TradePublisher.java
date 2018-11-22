package com.moudle.disruptorDemo.generate2;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import com.moudle.disruptorDemo.generate1.Trade;

public class TradePublisher implements Runnable{
	Disruptor<Trade> disruptor;
	private CountDownLatch latch;
	private static int count =1;//模拟百万次交易的发生
	
	public TradePublisher(Disruptor<Trade> disruptor,CountDownLatch latch){
		this.disruptor=disruptor;
		this.latch=latch;
	}

	@Override
	public void run() {
		TradeEventTranslator translator=new TradeEventTranslator();
		for(int i=0;i<count;i++){
			disruptor.publishEvent(translator);
		}
		latch.countDown();
	}

}
class TradeEventTranslator implements EventTranslator<Trade>{

	private Random random=new Random();
	
	@Override
	public void translateTo(Trade trade, long arg1) {
		this.generateTrade(trade);
	}
	private Trade generateTrade(Trade trade){
		trade.setPrice(random.nextDouble()*9999);
		return trade;
	}
}