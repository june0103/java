package com.s05.poly;

class Parent {
	int a = 100;
}

class Child extends Parent {
	int b = 200;
}

public class PloyMain01 {
	public static void main(String[] args) {

		// �����ڷ���
		Child ch = new Child();
		System.out.println(ch.a);
		System.out.println(ch.b);

		// �ڽ�Ŭ����Ÿ�� -> �θ�Ŭ����Ÿ������ ����ȯ(��ĳ����), �ڵ������� ����ȯ�ȴ�
		Parent p = ch; // �ּҺ���
		System.out.println(p.a);
		// System.out.println(p.b);

		// �θ�Ŭ����Ÿ�� -> �ڽ�Ŭ����Ÿ�� ����ȯ(�ٿ�ĳ����), ��������� ����ȯ
		Child ch2 = (Child) p;

	}

}
