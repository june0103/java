package com.s01.stringex;

public class StringMain02 {
	public static void main(String[] args) {
		String s1 = "Kwon Sun Ae";
		// 012345678910 , ���ڿ��� ���� 11

		int index = s1.indexOf('n'); // ù��° n�� ��ġ
		System.out.println(index);

		index = s1.indexOf("Sun"); // Sum�� ��ġ. S�� �ε��� ��ȯ
		System.out.println(index);

		index = s1.lastIndexOf('n'); // ������ n�� ��ġ
		System.out.println(index);

		char c = s1.charAt(index); // ���� ����
		System.out.println(c);

		index = s1.indexOf('S');
		// ������ �ε������� ������ �ε������� ���ڿ� ����
		String string = s1.substring(index);
		System.out.println(string);

		// �����ε������� �� �ε��� ������ ���ڿ� ����
		string = s1.substring(index, index + 3); // 5,8
		System.out.println(string);

		int length = s1.length();
		System.out.println(length);

		String array[] = s1.split(""); // ""����ڿ� - ���ڿ���ü�� ���������� �����Ͱ� ���ٶ�� ��
		for (int i = 0; i < array.length; i++) {
			System.out.println("array[" + i + "] : " + array[i]);
		}
	}

}
