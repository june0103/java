package com.s04.superex;

class People2{
	int a;
	//인자 있는 생성자
	public People2(int a) {
		this.a = a;
	}
}

//자식클래스
class Student extends People2{
	public Student() {
		//부모클래스의 인자가 있는 생성자를 명시적으로 호출해야 객체 생성이 가능하다.
		super(100); //부모클래스의 인자 있는 생성자
	}
}

public class SuperMain04 {
	public static void main(String[] args) {
		Student st = new Student();
		System.out.println(st.a);
	}

}
