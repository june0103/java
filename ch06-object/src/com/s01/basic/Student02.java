package com.s01.basic;

public class Student02 {

	String name;
	int age;
	String hobby;
	
	public static void main(String[] args) {
		
		Student02 s = new Student02();
		
		//��ü ������ �ʱⰪ ���
		System.out.println(s.name);
		System.out.println(s.age);
		System.out.println(s.hobby);
		System.out.println("====================");
		
		s.name = "ȫ�浿";
		s.age = 30;
		s.hobby = "�౸";
		
		System.out.println(s.name);
		System.out.println(s.age);
		System.out.println(s.hobby);
		System.out.println("====================");
		
		Student02 s2 = new Student02();
		
		s2.name = "�ڹ���";
		s2.age = 40;
		s2.hobby = "����";
		
		System.out.println(s2.name);
		System.out.println(s2.age);
		System.out.println(s2.hobby);
	
		
	}
}
