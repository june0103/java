package com.s08.abs;
//추상클랫느느 클래스에 abstract명시하면 추상클래스가 되고 일반적으로 추상메서드를 하나 이상 갖는다.

abstract class A2 {
	// 추상메서드. 중괄호로를 열지않고 세미콜론으로 막는다
	public abstract void make();

	// 일반메서드
	public void play() {
		System.out.println("A의 play");
	}

}

class B2 extends A2 {

	// 부모클래스의 추상메서드는 반드시 구현
	@Override
	public void make() {
		System.out.println("B2의 make");

	}

}

public class AbsMain02 {
	public static void main(String[] args) {
		B2 bp = new B2();
		bp.make();
		bp.play();

	}
}
