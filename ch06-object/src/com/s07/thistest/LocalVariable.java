package com.s07.thistest;

public class LocalVariable {
	// 멤버변수 : 클래스 블럭 내에서정의된 변수 , 클래스 블럭(클래스의 { })
	int b = 200;

	public void make() {
		// 지역변수 : 메서드, 생성자, 제어문 블럭 내에서 생성된 변수, 해당 블럭이 종료되면 변수는 소멸

		int a = 100;
		System.out.println(a); // 지역변수
		System.out.println(b); // 멤버변수

	}

	public void fun() {
		// fun메서드 안에 a변수가정의되지 않아서 호출 불가능
		System.out.println(b); // 멤버변수는 호출가능
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) { // 제어문, 반복문 블럭 안의 지역변수
			System.out.println(i);
		}
		// 제어문, 반복문 블럭을 빠져나와 지역변수 i는 소면되어 호출이 불가능

	}
}
