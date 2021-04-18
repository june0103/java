package com.s03.overriding;

//부모클래스
class GrandParent {
	public String getCar() {
		return "구형 자동차";
	}
}

//자식클래스
class Father extends GrandParent {

	// Method Overriding (메소드 재정의)
	// 상속 받아서 사용하는 부모클래스의 메서드를 원형 그대로 사용하는 것이 아니라 변경해서 사용하기 위한 문법
	
	//@Override 어노테이션은 메서드 재정의 문법에 맞게 재정의가 되었는지를 검증
	//문법에 맞게 재정의 되지 않으면 컴파일 오류가 발생한다
	
	@Override
	public String getCar() {
		return "신형 자동차";
	}
}

class Oncle extends GrandParent{
	
}

public class OverridingMain01 {
	public static void main(String[] args) {

		Father ft = new Father();
		System.out.println(ft.getCar());
		
		Oncle on = new Oncle();
		System.out.println(on.getCar());
	}
}
