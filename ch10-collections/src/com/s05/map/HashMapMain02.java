package com.s05.map;

import java.util.HashMap;
import java.util.Iterator;

public class HashMapMain02 {
	public static void main(String[] args) {

		String msg[] = { "Berlin", "Paris", "Seoul", "New Yoerk", "London" };

		HashMap<Integer, String> map = new HashMap<Integer, String>();

		for (int i = 0; i < msg.length; i++) {
			map.put(i, msg[i]);
		}

//		Set<Integer> s = map.keySet();
//		Iterator<Integer> keys = s.iterator();
		Iterator<Integer> keys = map.keySet().iterator();
		while (keys.hasNext()) {
			Integer key = keys.next();
			System.out.println(key + "," + map.get(key));
		}
	}

}
