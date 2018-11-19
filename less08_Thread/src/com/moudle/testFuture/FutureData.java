package com.moudle.testFuture;

public class FutureData implements Data {
	
	private RealData realData;
	private boolean isReady=false;

	public synchronized void setRealData(RealData realData) {
		if(isReady) {//是否装载，已装载直接返回
			return;
		}
		this.realData=realData;
		notify();
		isReady=true;
	}

	@Override
	public synchronized String getRequest() {
		if(!isReady) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return this.realData.getRequest();
	}

}
