package com.s02.stack;

import java.util.Stack;

public class StackMain {
	public static void main(String[] args) {
		// ����(stack) : ���Լ��� LIFO(Last In First Out)���
		String array[] = { "���޷�", "����", "������", "����", "���" };
		Stack<String> st = new Stack<String>();

		for (int i = 0; i < array.length; i++) {
			st.push(array[i]); // ��ü ����

		}

		System.out.println(st);

		// ������ ����ִ��� ���� �� �ݺ��� ����
		while (!st.isEmpty()) {
			// ���ÿ� ����� ��ü�� ������ ��Ҹ� ��ȯ
			System.out.printf(st.pop() + "\t");

		}

		System.out.println();
		System.out.println(st);
	}

}
