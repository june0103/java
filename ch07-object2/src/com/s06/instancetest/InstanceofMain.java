package com.s06.instancetest;

class Parent {

	@Override
	public String toString() {
		return "Parent Class";

	}
}

class Child extends Parent {

	@Override
	public String toString() {
		return "child Class";

	}

}

public class InstanceofMain {
	public static void main(String[] args) {
		Parent p = new Parent();

		// 컴파일시 오류는 없지만 실행시 오류발생
		// parent 객체를 생성해서 객세 생성시 child클래스 영역이 만들어지지 않아서
		// 형변환, 호출 불가능하다
		// Child ch = (Child) p;
		// System.out.println(ch);

		// 객체p 형변환 가능 자료형child
		if (p instanceof Child) { // 해당 객체가 연산자 오른쪽에 표시된 자료형으로 형변관 가능하면 true
			Child ch2 = (Child) p;
			System.out.println(ch2);
		} else {
			// 형변환 불가능하면 false
			System.out.println(p);
		}
	}
}
