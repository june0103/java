package com.s04.superex;

class People2{
	int a;
	//���� �ִ� ������
	public People2(int a) {
		this.a = a;
	}
}

//�ڽ�Ŭ����
class Student extends People2{
	public Student() {
		//�θ�Ŭ������ ���ڰ� �ִ� �����ڸ� ��������� ȣ���ؾ� ��ü ������ �����ϴ�.
		super(100); //�θ�Ŭ������ ���� �ִ� ������
	}
}

public class SuperMain04 {
	public static void main(String[] args) {
		Student st = new Student();
		System.out.println(st.a);
	}

}
