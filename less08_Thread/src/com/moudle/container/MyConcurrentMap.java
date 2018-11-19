package com.moudle.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MyConcurrentMap {
	public static void main(String[] args) {
		
		ConcurrentMap<String, Object> map=new ConcurrentHashMap<>();
		map.put("a", "a");
		map.put("b", "b");
//		map.put("a","a1");
		map.putIfAbsent("c","a");
		
		for (Map.Entry<String, Object> m : map.entrySet()) {
			System.out.println(m.getKey()+"==="+m.getValue());
		}
		
	}

}
