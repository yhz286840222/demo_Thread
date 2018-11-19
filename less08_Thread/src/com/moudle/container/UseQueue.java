package com.moudle.container;

import java.util.concurrent.SynchronousQueue;

public class UseQueue {

	volatile static boolean flag=false;
	public static void main(String[] args) throws Exception {
		//高性能无阻塞无界队列
		/*ConcurrentLinkedQueue<String> cl=new ConcurrentLinkedQueue<>();
		cl.add("a");
		cl.offer("b");
		cl.add("c");
		cl.offer("d");
		cl.add("e");
		
		System.out.println("队列原始大小："+cl.size());
		System.out.println(cl.poll());
		System.out.println("poll取值后的大小："+cl.size());
		System.out.println(cl.peek());
		System.out.println("peek取值后的大小："+cl.size());*/
		
		//有界阻塞队列
		/*ArrayBlockingQueue<String> array=new ArrayBlockingQueue<>(5);
		array.put("a");
		array.put("b");
		array.put("c");
		array.add("d");
		array.add("e");
		//put()当元素达到队列的最大值当前阻塞
		//add()会抛异常java.lang.IllegalStateException: Queue full
//		array.add("f");
		System.out.println(array.offer("a", 2, TimeUnit.SECONDS));//两秒过后返回true：添加成功，false：添加失败
		for (String string : array) {
			System.out.println(string);
		}*/
		//无界阻塞队列,初始化可以不给定长度，也可以给定长度
		/*LinkedBlockingQueue<String> linked=new LinkedBlockingQueue<>(); 
		linked.put("a");
		linked.put("b");
		linked.put("c");
		linked.add("d");
		linked.add("e");
		linked.offer("f");
		System.out.println(linked.offer("a", 2, TimeUnit.SECONDS));//两秒过后返回true：添加成功，false：添加失败
		for (String string : linked) {
			System.out.println(string);
		}
		System.out.println("----------------");
		List<String> list=new ArrayList<>();
		linked.drainTo(list, 3);//表示从队列中获取3个元素放入集合中
		System.out.println("list.size="+list.size());
		for (String str : list) {
			System.out.println(str);
		}*/
		SynchronousQueue<String> q=new SynchronousQueue<>();
		Thread t1=new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					System.out.println(q.take());	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					
			}
		},"t1");
		t1.start();
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {	
				q.add("abcd");
			}
		},"t2");
		t2.start();
	}
}
