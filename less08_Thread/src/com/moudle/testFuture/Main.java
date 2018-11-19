package com.moudle.testFuture;

public class Main {
	
	public static void main(String[] args) {
		Futureclient fc=new Futureclient();
		Data data=fc.getRequest("宝宝");
		System.out.println("请求完成！");
		System.out.println("执行其他操作！");
		String result=data.getRequest();
		System.out.println("返回结果："+result);
	}

}
