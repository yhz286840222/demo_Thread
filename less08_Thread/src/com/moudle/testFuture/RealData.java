package com.moudle.testFuture;

public class RealData implements Data{
	
	private String result;

	public RealData(String params) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		result="根据参数["+params+"]加载数据返回结果！";
	}
	

	@Override
	public String getRequest() {
		return this.result;
	}

	

}
