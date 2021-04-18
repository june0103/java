package com.s05.poly;

class Parent {
	int a = 100;
}

class Child extends Parent {
	int b = 200;
}

public class PloyMain01 {
	public static void main(String[] args) {

		// 참조자료형
		Child ch = new Child();
		System.out.println(ch.a);
		System.out.println(ch.b);

		// 자식클래스타입 -> 부모클래스타입으로 형변환(업캐스팅), 자동적으로 형변환된다
		Parent p = ch; // 주소복사
		System.out.println(p.a);
		// System.out.println(p.b);

		// 부모클래스타입 -> 자식클래스타입 형변환(다운캐스팅), 명시적으로 형변환
		Child ch2 = (Child) p;

	}

}
