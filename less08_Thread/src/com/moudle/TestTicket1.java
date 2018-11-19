package com.moudle;

class Ticket1 implements Runnable{

	int ticket=100;
	
	Object obj = new Object();
	public volatile boolean flag=true;
	@Override
	public void run() {
		while (flag) {
//			synchronized (this) {
//				if(ticket>0) {
//					try {
//						Thread.currentThread().sleep(10);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println(Thread.currentThread().getName()+"售票，票号为："+ticket--);
//				}
//			}
			show();
			
		}
	}
	
	public synchronized void show() {
		if(ticket>0) {
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"售票，票号为："+ticket--);
		}else {
			//this.flag=false;
		}
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}

public class TestTicket1 {

	public static void main(String[] args) throws InterruptedException {
		Ticket1 t=new Ticket1();
		Thread t1=new Thread(t);
		Thread t2=new Thread(t);
		Thread t3=new Thread(t);
		
		t1.setName("t1窗口");
		t2.setName("t2窗口");
		t3.setName("t3窗口");
		
		t1.start();
		t2.start();
		t3.start();
		Thread.sleep(2000);
		if(t.ticket<0) {
			t.setFlag(false);
			System.out.println("设置flag"+t.flag);
		}
		
		
	}
}
