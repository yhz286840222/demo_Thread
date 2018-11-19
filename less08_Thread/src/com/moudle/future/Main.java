package com.moudle.future;

public class Main {

	public static void main(String[] args) {
		FutureClient futureClient=new FutureClient();
		Data data=futureClient.request("张三");
		System.out.println("请求结束！");
		System.out.println("执行其他操作！");
		String result=data.getRequest();
		System.out.println("请求结果："+result);
	}
}
