package com.s04.anonymous;

//부모글래스
class Inner6 {
	public void disp() {
		System.out.println("disp 메서드");
	}
}

public class AnonymousMain {

	public void make() {
//		// 로컬 내부클래스
//		class Inner extends Inner6 {
//			@Override
//			public void disp() {
//				System.out.println("로컬 내부클래스의 disp");
//			}
//		}
//		Inner i = new Inner();
//		i.disp();

		// 익명 내부 클래스
		// 클래스 정의 + 객체 생성
		Inner6 i = new Inner6() {
			@Override
			public void disp() {
				System.out.println("익명 내부클래스의 disp");
			}
		};
		i.disp();

	}

	public static void main(String[] args) {

		AnonymousMain am = new AnonymousMain();
		am.make();
	}
}
