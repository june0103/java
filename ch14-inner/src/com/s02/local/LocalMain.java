package com.s02.local;

public class LocalMain {

	public void innerTest() {
		class Inner {
			public void getData() {
				System.out.println("Local 내부클래스");
			}
		}
		// 로컬 내부클래스 객체 생성해줘야 한다
		Inner i = new Inner();
		i.getData();

	}

	public static void main(String[] args) {
		LocalMain m = new LocalMain();
		m.innerTest();

	}
}
