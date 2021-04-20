package com.s01.member;

class Outer {
	// 멤버 변수
	int x = 100;

	// 멤버 내부 클래스
	class Inner {
		int y = 200;
	}
}

public class MemberMain {
	public static void main(String[] args) {
		Outer ot = new Outer();

		System.out.println(ot.x);

		// 멤버 내부클래스 객체 생성
		Outer.Inner oi = ot.new Inner();
		System.out.println(oi.y);

		System.out.println(ot);
		System.out.println(oi);
	}
}
