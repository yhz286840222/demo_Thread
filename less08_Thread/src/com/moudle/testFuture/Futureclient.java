package com.moudle.testFuture;

public class Futureclient {

	public Data getRequest(final String params) {
		final FutureData futureData=new FutureData();
		
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				RealData realData = new RealData(params);
				futureData.setRealData(realData);
			}
		},"t");
		t.start();
		return futureData;
	}
	
}
