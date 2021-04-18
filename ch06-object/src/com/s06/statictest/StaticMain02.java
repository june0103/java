package com.s06.statictest;

public class StaticMain02 {
	// 인스턴스변수
	int a;
	// static 변수
	static String s;

	public static void main(String[] args) {

		// static 변수는 호출하면 메모리에 만들어짐
		s = "겨울";
		// 인스턴스 변수는 객체 생성 후 호출해야 함
		// a = 10;

		StaticMain02 sm = new StaticMain02();
		sm.a = 20;

	}

}
