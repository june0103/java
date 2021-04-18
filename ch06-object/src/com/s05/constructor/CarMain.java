package com.s05.constructor;

class Car {
	String color; // 색상
	String gearType; // 변속기종류
	int door; // 문의 갯수
//기본 생성자, 생략가능

	public Car() {
	}
}

public class CarMain {
	public static void main(String[] args) {
		// 객체 선언 및 생성
		Car c1 = new Car(); // 생성자는 멤버변수 초기화
		// 생성자가 초기화한 멤버변수의 값 출력
		System.out.println(c1.color);
		System.out.println(c1.gearType);
		System.out.println(c1.door);

		System.out.println(c1);
		System.out.println("-----------------");

		c1.color = "white";
		c1.gearType = "auto";
		c1.door = 4;
		System.out.println(c1.color);
		System.out.println(c1.gearType);
		System.out.println(c1.door);

		System.out.println("-----------------");

		Car c2 = new Car();
		c2.color = "Black";
		c2.gearType = "maneal";
		c2.door = 5;
		System.out.println(c2.color);
		System.out.println(c2.gearType);
		System.out.println(c2.door);

		System.out.println(c2);

	}

}
