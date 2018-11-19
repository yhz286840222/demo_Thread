package com.moudle.container;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 网名
 * @author Administrator
 *
 */
public class Wangmin implements Delayed{
	
	private String name;
	//身份证
	private String id;
	//截止时间
	private long endTime;
	//定义时间工具类
	private TimeUnit timeUnit=TimeUnit.SECONDS;	
	
	public Wangmin(String name, String id, long endTime) {
		this.name = name;
		this.id = id;
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	/**
	 * 相互比较排序用
	 */
	@Override
	public int compareTo(Delayed o) {
		Wangmin w=(Wangmin)o;
		return this.getDelay(timeUnit)-w.getDelay(timeUnit)>0?1:0;
	}

	/**
	 * 判断是否到了截止时间
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		return endTime-System.currentTimeMillis();
	}

}
