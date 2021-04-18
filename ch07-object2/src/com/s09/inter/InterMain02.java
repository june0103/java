package com.s09.inter;

//인터페이스 : 형식만 있고 내용이 없음
interface A2 {
	// 추상메서드
	public abstract void make(); // 원형

	void play();
}

//인터페이스를 클래스에 구현
class B2 implements A2 {

	// 인터페이스의 추상메서드를 구현
	@Override
	public void make() {
		System.out.println("B2의 make");
	}

	@Override
	public void play() {

		System.out.println("B2의 play");
	}

}

public class InterMain02 {
	public static void main(String[] args) {
		B2 bp = new B2();
		bp.make();
		bp.play();
	}
}
//클래스는 다중상속이 아닌 단일상속
//인터페이스끼리 상속, 인터페이스와 클래스는 구현
//인터페이스를 클래스로 구현할때 다중구현가능