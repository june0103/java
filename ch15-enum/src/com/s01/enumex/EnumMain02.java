package com.s01.enumex;

//열거형은 상수를 가지고 생성되는 객체
enum Lesson {
	JAVA, XML, JSP
}

public class EnumMain02 {
	public static void main(String[] args) {
		System.out.println(Lesson.JAVA);
		System.out.println(Lesson.XML);
		System.out.println(Lesson.JSP);
	}
}
