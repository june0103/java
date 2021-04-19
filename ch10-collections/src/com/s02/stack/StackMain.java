package com.s02.stack;

import java.util.Stack;

public class StackMain {
	public static void main(String[] args) {
		// 스택(stack) : 후입선출 LIFO(Last In First Out)방식
		String array[] = { "진달래", "백합", "개나리", "벚꽃", "장미" };
		Stack<String> st = new Stack<String>();

		for (int i = 0; i < array.length; i++) {
			st.push(array[i]); // 객체 저장

		}

		System.out.println(st);

		// 스택이 비어있는지 검증 후 반복문 실행
		while (!st.isEmpty()) {
			// 스택에 저장된 객체를 꺼내서 요소를 반환
			System.out.printf(st.pop() + "\t");

		}

		System.out.println();
		System.out.println(st);
	}

}
