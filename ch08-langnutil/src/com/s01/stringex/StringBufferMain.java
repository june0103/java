package com.s01.stringex;

public class StringBufferMain {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("���� ����!");
		System.out.println(sb);

		// ������ �򵦽��� ���� ����
		sb.insert(2, '��');
		System.out.println(sb);

		// �������ڿ� �ڿ� ������ ���ڿ��� ����
		sb.append("������ ");
		System.out.println(sb);

		sb.append("�ÿ��ϴ�");
		System.out.println(sb);

		// �����ε������� ���ε��������� ������ ���ڿ��� ��ü
		sb.replace(0, 3, "���డ��!!");
		System.out.println(sb);

		// ������ �ε����� ���� ����
		sb.deleteCharAt(0);
		System.out.println(sb);

		// �����ε������� ���ε��������� ����
		sb.delete(0, 3);
		System.out.println(sb);

		// StringBuffer -> String
		String str = sb.toString();
		System.out.println(str);
	}
}
