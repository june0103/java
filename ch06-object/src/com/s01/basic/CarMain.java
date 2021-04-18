package com.s01.basic;
public class CarMain {

	String company = "현대자동차";
	String model = "소나타";
	String color = "검정";
	int maxSpeed = 350;
	int speed = 60;

	public static void main(String[] args) {
		// 객체 선언 및 생성
		CarMain myCar = new CarMain();
		// 객체의 멤버 변수의 값 출력
		System.out.println("제작회사 : " + myCar.company);
		System.out.println("모델명 : " + myCar.model);
		System.out.println("색상 : " + myCar.color);
		System.out.println("최고속도 : " + myCar.maxSpeed);
		System.out.println("현재속도 : " + myCar.speed);

		// 멤버변수의 값 변경
		myCar.speed = 100;

		System.out.println("변경된 속도 : " + myCar.speed);

	}
}
