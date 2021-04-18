package com.s09.inter;

interface I {
	public abstract void play();// 추상메서드

}

//인터페이스를이용해서 표준화적용
class B implements I {

	// 인터페이스의 추상메서드 구현
	@Override
	public void play() {
		System.out.println("play in B class");
	}

	public void make() {
		System.out.println("make in B class");
	}
}

public class InterMain04 {
	public static void main(String[] args) {

		B bp = new B();
		bp.play();
		bp.make();

		// 클래스타입 -> 인터페이스타입 형변환
		// 자동적으로 형변환
		I im = bp;
		im.play();

		// 인터페이스타입으로 구현된 메소드만 호출가능
		// 호출 범위를 벗어나서 호출 불가능
		// im.make():
		
		//인터페이스타입 -> 클래스타입 형변환
		//명시적으로 형변환
		//범위가 늘어나 강제적으로 형변환
		B bp2 = (B)im;
		bp2.make();
		bp2.play();

	}
}
