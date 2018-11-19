package com.moudle;

public class TestSingleton {

	public static void main(String[] args) {
		SingletonThread st=new SingletonThread();
		Thread t1=new Thread(st);
		Thread t2=new Thread(st);
		t1.setName("线程1");
		t2.setName("线程2");
		t1.start();
		t2.start();
	}

}
class SingletonThread implements Runnable{

	int i=100;
	boolean flag = true;
	@Override
	public void run() {
		while (flag) {			
			if(i>0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				Singleton s1=Singleton.getInstance();
//				Singleton s2=Singleton.getInstance();			
				System.out.println(Thread.currentThread().getName()+":"+i--);
				//i--;
			}else {
				flag=false;
			}
			
						
		}
		
	}
	
}
//懒汉式单例模式
class Singleton{
	private Singleton() {
		
	}
	
	private volatile static Singleton instance=null;
	
	public static Singleton getInstance() {
		if(instance==null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}		
		return instance;
	}
}
