package com.moudle.future;

import java.util.concurrent.TimeUnit;

public class RealData implements Data{
	
	private String result;
	public RealData(String params) {
		System.out.println("根据参数["+params+"]加载数据！");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		result="这是根据"+params+"查询的结果！";
	}	
	@Override
	public String getRequest() {
		return result;
	}
}
