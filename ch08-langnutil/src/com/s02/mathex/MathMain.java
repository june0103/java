package com.s02.mathex;

public class MathMain {
	public static void main(String[] args) {
		int a = Math.abs(-10);
		System.out.println(a);

		// 올림
		double b = Math.ceil(3.3);
		System.out.println(b);

		// 버림
		double c = Math.floor(3.7);
		System.out.println(c);

		// 반올림
		int d = Math.round(3.7f);
		System.out.println(d);

		// 최대값구하기
		int e = Math.max(3, 5);
		System.out.println(e);

		// 최소값구하기
		int f = Math.min(3, 5);
		System.out.println(f);
	}
}
