package com.moudle;

class ResNumber{
	public int count;
	public static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		}
	};
	public String getNumber() {
		count=threadLocal.get()+1;
		threadLocal.set(count);
		return count+"";
	}
}
class Local extends Thread{
	ResNumber resNumber;
	public Local(ResNumber resNumber) {
		this.resNumber=resNumber;
	}
	@Override
	public void run() {
		for(int i=0;i<3;i++) {
			System.out.println(getName()+"=="+resNumber.getNumber());
		}
	}
}
public class ThreadLocalDemo {
	public static void main(String[] args) {
		ResNumber res=new ResNumber();
		Local t1=new Local(res);
		Local t2=new Local(res);
		Local t3=new Local(res);
		t1.start();
		t2.start();
		t3.start();
	}
}
