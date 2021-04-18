package com.s05.poly;

class Parent2 {
	public void make() {
		System.out.println("부모클래스의 make");
	}
}

class Child2 extends Parent2 {
	// 메서드 오버라이딩
	@Override
	public void make() {
		System.out.println("자식클래스의 make");
	}
}

public class PolyMain03 {
	public static void main(String[] args) {

		Child2 ch = new Child2();
		ch.make();

		Parent2 p = ch; // 자식클래스타입->부모클래스타입 형변환. 업캐스팅. 자동적으로 형변환
		// 부모클래스타입으로 형변환이 되어도 재정의된 메서드는 부모클래스의 메서드가 호출되는 것이 아니라
		// 자식클래스의 재정의된 메서드가 호출된다
		p.make();
	}

}
