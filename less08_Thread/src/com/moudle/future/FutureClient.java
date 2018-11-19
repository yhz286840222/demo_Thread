package com.moudle.future;

public class FutureClient {
	
	public Data request(final String params) {
		//1.代理对象（Data接口的实现类）先返回给发送请求的客户端，告诉他请求已经接收到了，可以做其他的事情。
		final FutureData futureData=new FutureData();
		//2.启动一个新的线程，去加载真是数据，传递给这个代理对象。
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				//3.这个新的线程可以去慢慢的加载真实对象，然后传递给代理对象
				RealData realData=new RealData(params);
				futureData.setRealData(realData);
			}
		},"t1");
		t1.start();
		return futureData;
	}
}
