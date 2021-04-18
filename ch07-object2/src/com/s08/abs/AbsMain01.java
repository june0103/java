package com.s08.abs;

//추상클래스
//미완성된 개념으로 단독으로 객체 생성이 불가능하고 일반적으로 자식클래스에 상속되어 사용

abstract class A {

	private int x;

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

}

class B extends A {
	int b = 200;
}

public class AbsMain01 {
	public static void main(String[] args) {
		// 추상클래슨느 객체 생성 불가능
		// A ap = new A();

		B bp = new B();

		bp.setX(100);
		System.out.println(bp.getX());
	}

}
