package com.s06.statictest;

class StaticMethod {

	// 인스턴스 메소드
	public void print(String str) {
		System.out.println(str);
	}

	// 스태틱 메소드
	public static String getString() {
		return "겨울";
	}
}

public class StaticMain03 {
	public static void main(String[] args) {
		// 호출하면 메모리에 만드어지고 반환 값을 사용할 수 있다
		System.out.println(StaticMethod.getString());

		// 인스턴스 메서드는 객체 성성 후 호출해야한다.
		StaticMethod sm = new StaticMethod();
		sm.print("가을");
	}

}
