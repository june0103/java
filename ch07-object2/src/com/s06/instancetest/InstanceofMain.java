package com.s06.instancetest;

class Parent {

	@Override
	public String toString() {
		return "Parent Class";

	}
}

class Child extends Parent {

	@Override
	public String toString() {
		return "child Class";

	}

}

public class InstanceofMain {
	public static void main(String[] args) {
		Parent p = new Parent();

		// �����Ͻ� ������ ������ ����� �����߻�
		// parent ��ü�� �����ؼ� ���� ������ childŬ���� ������ ��������� �ʾƼ�
		// ����ȯ, ȣ�� �Ұ����ϴ�
		// Child ch = (Child) p;
		// System.out.println(ch);

		// ��üp ����ȯ ���� �ڷ���child
		if (p instanceof Child) { // �ش� ��ü�� ������ �����ʿ� ǥ�õ� �ڷ������� ������ �����ϸ� true
			Child ch2 = (Child) p;
			System.out.println(ch2);
		} else {
			// ����ȯ �Ұ����ϸ� false
			System.out.println(p);
		}
	}
}
