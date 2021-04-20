package com.s02.local;

public class LocalMain02 {

	public void innerTest() {
		int a = 100; // 지역변수

		// 로컬 내부클래스
		class Inner {
			public void getData() {
				// JDK8.0부터는 내부클래스가 포함된 메스드의 지역변서를 호출하면 자동으로 상수로 변환된다

				// a = 200;
				System.out.println("a : " + a);
			}
		}
		// 로컬 내부클래스 객체 생성
		Inner i = new Inner();
		i.getData();
	}

	public static void main(String[] args) {
		LocalMain02 m = new LocalMain02();
		m.innerTest();
	}
}
