package com.s03.statictest;

public class StaticMain {

	// static 내부클래스
	// static 변수와 static 메서드를 포함한 내부클래스는 반드시 static 내부클래스로 지정해야 함.
	public static class Inner {
		int iv = 300;
		static int cv = 400;

		static void make() {
			System.out.println("하하");
		}

	}

	public static void main(String[] args) {

		// static 내부클래스 객체 생성
		StaticMain.Inner i = new StaticMain.Inner();

		System.out.println(i.iv);
		// static 변수 호출
		System.out.println(StaticMain.Inner.cv);

		// static 메서드 호출
		StaticMain.Inner.make();
	}

}
