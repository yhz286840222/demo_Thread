package com.moudle;

class Ticket extends Thread{
	static int ticket = 100;
	Object obj=new Object();
	@Override
	public void run() {
		while (true) {
			synchronized (Ticket.class) {
				if(ticket>0) {
					try {
						Thread.currentThread().sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"售票，票号为："+ ticket--);
				}
			}
			
		}
		
	}
}

public class TestTicket {
	public static void main(String[] args) {
		Ticket t1=new Ticket();
		Ticket t2=new Ticket();
		Ticket t3=new Ticket();
		
		t1.setName("t1窗口");
		t2.setName("t2窗口");
		t3.setName("t3窗口");
		
		t1.start();
		t2.start();
		t3.start();
	}
}
