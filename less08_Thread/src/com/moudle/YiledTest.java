package com.moudle;

public class YiledTest {

	public static void main(String[] args) throws InterruptedException {
		YiledDemo d=new YiledDemo();
		Thread t=new Thread(d);
		Thread t1=new Thread(d);
		
		t.setName("子线程1");
		t.start();
		t1.setName("子线程2");
		t1.start();
		
		for(int i=0;i<100;i++) {
			if(i%10==0) {
				Thread.yield();
			}
			System.out.println(Thread.currentThread().getName()+"=="+i);
			
		}
	}
}

class YiledDemo implements Runnable{
	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println(Thread.currentThread().getName()+"=="+i);
			
		}
	}
}
