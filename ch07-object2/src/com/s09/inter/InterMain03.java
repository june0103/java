package com.s09.inter;

//인터페이스는 표준화할때 많이사용. 인터페이스로 뼈대를 만들고 클래스로 구현
//클래스는 다중상속이 아닌 단일상속
//인터페이스끼리 상속, 인터페이스와 클래스는 구현
//인터페이스를 클래스로 구현할때 다중구현가능
interface Inter1 {
	// 추상메서드
	public abstract int getA();

}

interface Inter2 extends Inter1 {
	// 추성메서드 getA()를 가지고있는거로 인식
	public int getB(); // abstract이 없어소 인터페이스에는 추상메서드로 인식. abstract생략가능

}

interface Inter3 {
	// 추상메서드
	public int getC();
}

//인터페이스를 클래스에 구현
class InterTest implements Inter2, Inter3 {
	// 구현되는 모든 인터페이스 추상메서드 구현

	@Override
	public int getA() {

		return 10;
	}

	@Override
	public int getB() {

		return 20;
	}

	@Override
	public int getC() {

		return 30;
	}
}

public class InterMain03 {

	public static void main(String[] args) {

		InterTest it = new InterTest();
		System.out.println(it.getA());
		System.out.println(it.getB());
		System.out.println(it.getC());

	}

}
