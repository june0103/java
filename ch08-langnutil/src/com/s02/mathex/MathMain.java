package com.s02.mathex;

public class MathMain {
	public static void main(String[] args) {
		int a = Math.abs(-10);
		System.out.println(a);

		// �ø�
		double b = Math.ceil(3.3);
		System.out.println(b);

		// ����
		double c = Math.floor(3.7);
		System.out.println(c);

		// �ݿø�
		int d = Math.round(3.7f);
		System.out.println(d);

		// �ִ밪���ϱ�
		int e = Math.max(3, 5);
		System.out.println(e);

		// �ּҰ����ϱ�
		int f = Math.min(3, 5);
		System.out.println(f);
	}
}
