package com.s03.queue;

import java.util.LinkedList;

public class QueueMain {
	public static void main(String[] args) {
		// 큐(queue) : 선입선출 FIFO(First In First Out) 방식
		String array[] = { "서울", "부산", "대구", "광주", "인천" };

		LinkedList<String> list = new LinkedList<String>();

		for (int i = 0; i < array.length; i++) {
			list.offer(array[i]); // 객체 저장

		}

		// LinkedList에 저장된 요소의 목록
		System.out.println(list);

		// 큐에 저장된 첫번째 요솔르 검색
		while (list.peek() != null) {
			// 큐에 저장된 첫번째 요소를 반환하고 제거
			System.out.print(list.poll() + "\t");
		}

		System.out.println();
		System.out.println(list);
	}

}
