package com.s05.constructor;

//같은 패키지안에 클래스명이 같으면 오류발생, 클래스명은 다르게 해야한다
class Car2 {
	String color;
	String gearType;
	int door;

	// 인자가 있는 생성자
	public Car2(String c, String g, int d) {
		color = c;
		gearType = g;
		door = d;
	}

	// 인자가 없는 생성자, 기본생성자는 생성자가 없을때 생략가능하지만 생성자가 명시되어있을경우 생성되지 않는다. 그래서 사용필요시 선언해줘야
	// 한다
	public Car2() {

	}

}

public class CarMain02 {
	public static void main(String[] args) {
		Car2 C1 = new Car2("Blue", "auto", 4);

		System.out.println(C1.color);
		System.out.println(C1.gearType);
		System.out.println(C1.door);

		System.out.println("----------------");

		Car2 c2 = new Car2();
		c2.color = "black";
		c2.gearType = "maneal";
		c2.door = 4;

		System.out.println(c2.color);
		System.out.println(c2.gearType);
		System.out.println(c2.door);

	}

}
