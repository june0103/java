package com.s01.extention;

//�θ�Ŭ����
class People {
	public void eat() {
		System.out.println("�Ļ��ϴ�");
	}
}

//�ڽ�Ŭ����
class Student extends People {
	public void study() {
		System.out.println("�����ϴ�");
	}
}

public class ExtentionMain02 {
	public static void main(String[] args) {
		Student st = new Student();
		
		st.eat(); //�θ�Ŭ����(People)�� �޼ҵ带 ��� �޾Ƽ� ȣ��
		st.study(); //�ڽ�Ŭ����(Student)�� �޼ҵ� ȣ��
		System.out.println(st.toString()); //Object�� �޼ҵ�. toString - 
	}
}
