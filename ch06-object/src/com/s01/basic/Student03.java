package com.s01.basic;
public class Student03 {

	//�������, ��ü�� ������ ��ü�� ���Եȴ�
	String name;
	int korean;
	int math;
	int english;
	int sum;
	double average;

	//�迭���� ��� ���� �ڷ����� �����͸� ������ �� ������,
	//��ü�� �������� �ڷ����� �����͸� ������ �� �ִ�. ������
	//������ �������� ������ �ʿ��ϸ� ������ ���� �ְ� �ڵ尡 �������.
	//�̶�, �޼ҵ带 ����� ��� �� ���ִ�.
	//�޼ҵ� - ��ü�ȿ� ���� �Լ�
	
	public static void main(String[] args) {
		
		Student03 s = new Student03();
		s.name = "ȫ�浿";
		s.korean = 98;
		s.english = 97;
		s.math = 96;
		s.sum = s.korean + s.english + s.math;
		s.average = s.sum / 3.0 ;
		
		System.out.println("�̸� : " + s.name);
		System.out.println("���� : " + s.korean);
		System.out.println("���� : " + s.english);
		System.out.println("���� : " + s.math);
		System.out.println("���� : " + s.sum);
		System.out.println("��� : " + s.average);
		
	}
}
