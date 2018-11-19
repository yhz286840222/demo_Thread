package com.moudle;

class demo extends Thread{
	public volatile boolean flag=true;
	@Override
	public void run() {
		System.out.println("开始执行");
		while (flag) {
			
		}
		System.out.println("结束执行");
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}

public class TestVolatile {
	
	public static void main(String[] args) throws InterruptedException {
		demo d=new demo();
		d.start();
		Thread.sleep(1000);
		d.flag=false;
	}

}

