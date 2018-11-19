package com.moudle.container;

import java.util.concurrent.DelayQueue;

public class Wangba implements Runnable{

	//用于存放网名的队列
	private DelayQueue<Wangmin> queue=new DelayQueue<>();
	//营业标识
	public boolean yingye=true;
	
	/**
	 * 上机方法
	 * @param name
	 * @param id
	 * @param money
	 */
	public void shangji(String name,String id,int money) {
		Wangmin wm=new Wangmin(name, id, 1000*money+System.currentTimeMillis());
		System.out.println("网名"+wm.getName()+"身份证"+wm.getId()+"交钱"+money+"元，开始上机...");
		this.queue.put(wm);
	}
	
	public void xiaji(Wangmin wm) {
		System.out.println("网名"+wm.getName()+"身份证"+wm.getId()+"时间到下机");
	}
	@Override
	public void run() {
		while (yingye) {
			try {
				Wangmin wm=queue.take();
				xiaji(wm);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("开始营业");
		Wangba wb=new Wangba();
		Thread shangwang=new Thread(wb);
		shangwang.start();
		
		wb.shangji("路人甲", "123", 1);
		wb.shangji("路人乙", "234", 5);
		wb.shangji("路人丙", "345", 3);
	}
	
}
