package com.s01.extention;

//�θ�Ŭ����
class Parent { //extends object ����
			//Ŭ������ �����ϸ� �ڵ������� object�� ��ӵ�
	int a = 500;
}

//�ڽ�Ŭ����
class Child extends Parent {
	int b = 200;
}

public class ExtentionMain01 {
	public static void main(String[] args) {

		Child ch = new Child();
		Parent p = new Parent();
		
		System.out.println(ch.a);
		
		ch.a = 2;
		System.out.println(p.a);
		System.out.println(ch.a);
	}
}
