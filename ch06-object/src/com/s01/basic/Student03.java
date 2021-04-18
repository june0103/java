package com.s01.basic;
public class Student03 {

	//멤버변수, 객체를 생성시 객체에 포함된다
	String name;
	int korean;
	int math;
	int english;
	int sum;
	double average;

	//배열같은 경우 같은 자료형의 데이터만 저장할 수 있지만,
	//객체는 여러가지 자료형의 데이터를 저장할 수 있다. 하지만
	//변수가 많아지고 연산이 필요하면 불편한 점이 있고 코드가 길어진다.
	//이때, 메소드를 만들어 사용 할 수있다.
	//메소드 - 객체안에 만든 함수
	
	public static void main(String[] args) {
		
		Student03 s = new Student03();
		s.name = "홍길동";
		s.korean = 98;
		s.english = 97;
		s.math = 96;
		s.sum = s.korean + s.english + s.math;
		s.average = s.sum / 3.0 ;
		
		System.out.println("이름 : " + s.name);
		System.out.println("국어 : " + s.korean);
		System.out.println("영어 : " + s.english);
		System.out.println("수학 : " + s.math);
		System.out.println("총점 : " + s.sum);
		System.out.println("평균 : " + s.average);
		
	}
}
