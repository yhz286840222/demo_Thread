package com.moudle;

public class StopThread {

	public static void main(String[] args) {
		StopThreadDemo st=new StopThreadDemo();
		st.start();
		
		
		for(int i=0;i<10;i++) {
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(i==6) {
				//st.setFlag(false);
				try {
					st.interrupt();
				} catch (Exception e) {
				}
				
			}
			System.out.println(Thread.currentThread().getName()+"=="+i);
		}
	}
}
class StopThreadDemo extends Thread{
	private volatile boolean flag=true;
	@Override
	public synchronized void run() {
		System.out.println("子线程开始。。。。");
		while(flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				setFlag(false);
			}
		}
		System.out.println("子线程结束。。。。");
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}