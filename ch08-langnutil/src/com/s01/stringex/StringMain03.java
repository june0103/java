package com.s01.stringex;

public class StringMain03 {
	public static void main(String[] args) {
		String s1 = " aBa ";
		String s2 = "abc";
		int a = 100;
		String msg = null;

		// �ҹ��� -> �빮��
		msg = s1.toUpperCase();
		System.out.println(msg);

		// �빮�� -> �ҹ���
		msg = s1.toLowerCase();
		System.out.println(msg);

		// old���� -> new���ڷ� ��ü
		msg = s1.replace("aB", "b");
		System.out.println(msg);

		// �յ� ���� ����
		msg = s1.trim();
		System.out.println(msg);

		// ���ڷ� ���޵Ǵ� ���ڿ��� ���ԵǾ� �ִ��� ����
		boolean f = s1.contains("aB");
		System.out.println(f);

		// ���ڷ� ���޵Ǵ� ���ڿ��� �����ϴ� ����
		f = s2.startsWith("ab");
		System.out.println(f);

		// ���ڷ� ���޵Ǵ� ���ڿ��� �������� ����
		f = s2.endsWith("bc");
		System.out.println(f);

		// int->string
		msg = String.valueOf(a);
		System.out.println(msg);

		msg = a + "";
		System.out.println(msg);
	}
}
