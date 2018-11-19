package com.moudle.signal;
/**
 * 共享资源对象
 * @author Administrator
 *
 */
class Res{
	private String name;
	private String sex;
	
	private boolean flag=false;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Res [name=" + name + ", sex=" + sex + "]";
	}
	
}

/**
 * 生产者(写入线程)
 * @author Administrator
 *
 */
class InputThread extends Thread{
	Res res;
	int count=0;
	public InputThread(Res res) {
		this.res=res;
	}
	@Override
	public void run() {
		while(true) {	
			synchronized (res) {
				if(res.isFlag()) {
					try {
						res.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(count==0) {
					res.setName("张三丰");
					res.setSex("男");
				}else {
					res.setName("李冰冰");
					res.setSex("女");
				}
				count=(count+1)%2;
				
				res.setFlag(true);
				res.notify();
			}			
		}
	}
}

/**
 * 消费者(输出线程)
 * @author Administrator
 *
 */
class OutThread extends Thread{
	Res res;
	public OutThread(Res res) {
		this.res=res;
	}
	@Override
	public void run() {
		while (true) {
			synchronized (res) {
				if(!res.isFlag()) {
					try {
						res.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(res);		
				res.setFlag(false);
				res.notify();
			}			
		}
	}
}

public class ThreadCommunication {
	public static void main(String[] args) {
		Res res=new Res();
		InputThread input=new InputThread(res);
		OutThread out=new OutThread(res);
		input.start();
		out.start();
	}
}


