package com.moudle.future;

public class FutureData implements Data{
	
	private RealData realData;
	private boolean isReady=false;
	
	public synchronized void setRealData(RealData realData) {
		//如果已经装载完毕了，就直接返回
		if(isReady) {
			return;
		}
		//如果没有装载，装载真实对象。
		this.realData=realData;
		isReady=true;
		//进行通知
		notify();
	}
	@Override
	public synchronized String getRequest() {
		//如果没有装载好，程序一直处于阻塞状态
		if(!isReady) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		//装载好直接获取数据
		return this.realData.getRequest();
	}
}
