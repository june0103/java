package com.s01.extention;

//부모클래스
class People {
	public void eat() {
		System.out.println("식사하다");
	}
}

//자식클래스
class Student extends People {
	public void study() {
		System.out.println("공부하다");
	}
}

public class ExtentionMain02 {
	public static void main(String[] args) {
		Student st = new Student();
		
		st.eat(); //부모클래스(People)의 메소드를 상속 받아서 호출
		st.study(); //자식클래스(Student)의 메소드 호출
		System.out.println(st.toString()); //Object의 메소드. toString - 
	}
}
