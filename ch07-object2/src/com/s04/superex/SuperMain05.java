package com.s04.superex;

class Parent2{
	int a;
	int b;
	
	//�����ִ� �κ�Ŭ���� ������
	public Parent2(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
}

class Child3 extends Parent2{
	public Child3(int a, int b) {//�θ�Ŭ������ ���ڰ� �ִ� �����ڸ� ��������� ȣ���ؾ� ��ü ������ �����ϴ�.
	
		super(a,b); //�տ��� �ٸ��� ���� ���� �ʱ�ȭ��Ű���ʰ� �ڽ�Ŭ������ �����ڸ� ���ڰ� �ִ� �����ڷ� ����� ����� �ִ�.
	}
}

public class SuperMain05 {
	public static void main(String[] args) {
		
		Child3 c = new Child3(300, 400);
		System.out.println(c.a + " " + c.b);
		
	}

}
