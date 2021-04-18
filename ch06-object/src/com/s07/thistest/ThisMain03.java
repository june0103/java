package com.s07.thistest;

public class ThisMain03 {
	
	//생성자 내부에서 또다른 생성자를 호출 할 때 this()를 표시함
	//this()를 이용해서 또다른 생성자를 호출하는 경우는 생성자 내부에 반복적인 코드가 있어서
	//반복적인 코드를 제거하고 코드를 재사용하고자 할 때 사용한다

	//this()를 통해서 생성자 내부에서 또다른 생성자를 호출할 때는 this()를 생성자의 가장 윗부분에
	//명시해야하고 this()를 호출하기 전에 수행문이 명시되면 오류가 발생한다
	public ThisMain03() {
		this("바람~~~~");
		System.out.println("++++++");
	}

	public ThisMain03(String msg) {
		System.out.println(msg);
	}

	public ThisMain03(int a) {
						//int -> String변환
		this(String.valueOf(a));
	}

	
	public static void main(String[] args) {
		ThisMain03 tm = new ThisMain03();
		ThisMain03 tm2 = new ThisMain03("겨울");
		ThisMain03 tm3 = new ThisMain03(123);

	}
}
