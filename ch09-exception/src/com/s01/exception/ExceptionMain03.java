package com.s01.exception;

public class ExceptionMain03 {
	public static void main(String[] args) {
		int var = 50;
		// ���� catch�����
		// ���ܰ� �߻��ϸ� ���� ��ü�� ���޵Ǵ� catch������ �̵��ؼ� ���๮�� ����
		// ���� catch���� ����� �� Exception�� ���� ����Ŭ������ ���ÿ� ����� ���� ���� ����Ŭ������ ���� ����ϰ�
		// ���� �ڿ� Exception�� ����ؾ� ���ۻ��� ������ �߻����� �ʴ´�
		try {
			// String -> int
			int data = Integer.parseInt(args[0]);

			System.out.println(var / data);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("�Է��� �����Ͱ� �����ϴ�.");
		} catch (NumberFormatException e) {
			System.out.println("������ �ƴմϴ�.");
		} catch (ArithmeticException e) {
			System.out.println("0���� ���� �� �����ϴ�.");
		} catch (Exception e) {
			System.out.println("������ ���ܵ�");
			// TODO: handle exception
		}
		System.out.println("���α׷� ����!!");
	}
}
