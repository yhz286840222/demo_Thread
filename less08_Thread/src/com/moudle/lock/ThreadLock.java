package com.moudle.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 共享资源对象
 * @author Administrator
 *
 */
class Res{
	private String name;
	private String sex;
	
	private boolean flag=false;
	private Lock lock=new ReentrantLock();
	private Condition condition=lock.newCondition();
	
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
	
	public Lock getLock() {
		return lock;
	}
	public void setLock(Lock lock) {
		this.lock = lock;
	}
	
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
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
			try {
				//获取锁
				res.getLock().lock();
				if(res.isFlag()) {
					res.getCondition().await();
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
				//唤醒对方相当于synchronized中用到的notify();
				res.getCondition().signal();
			}catch (Exception e) {
				
			}finally {
				//释放锁
				res.getLock().unlock();
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
			try {
				//获取锁
				res.getLock().lock();;			
				if(!res.isFlag()) {
					res.getCondition().await();
				}
				System.out.println(res);		
				res.setFlag(false);	
				res.getCondition().signal();
			}catch (Exception e) {
				
			}finally {
				//释放锁
				res.getLock().unlock();
			}
		}
	}
}

public class ThreadLock {
	public static void main(String[] args) {
		Res res=new Res();
		InputThread input=new InputThread(res);
		OutThread out=new OutThread(res);
		input.start();
		out.start();
	}
}


