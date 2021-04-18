package com.s07.finalex;

class Me {
	// 메서드에 파이널을을 붙이는 경우는 오버라이드.재정의 필요가 없이 고유한 메소드를 사용 할 경우 파이널 사용
	public final void make() {
		System.out.println("부모클래스의 make");
	}
}

class MeTest extends Me {

	// 부모클래스의 메서드 final이 명시되면 자식글래스에서 해당 메서드를 재정의 할 수 없음

//	@Override
//	public void make() {
//		System.out.println("자식클래스의 make");
//	}
}

public class FinalMain02 {

}
