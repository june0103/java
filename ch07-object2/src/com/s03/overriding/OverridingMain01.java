package com.s03.overriding;

//�θ�Ŭ����
class GrandParent {
	public String getCar() {
		return "���� �ڵ���";
	}
}

//�ڽ�Ŭ����
class Father extends GrandParent {

	// Method Overriding (�޼ҵ� ������)
	// ��� �޾Ƽ� ����ϴ� �θ�Ŭ������ �޼��带 ���� �״�� ����ϴ� ���� �ƴ϶� �����ؼ� ����ϱ� ���� ����
	
	//@Override ������̼��� �޼��� ������ ������ �°� �����ǰ� �Ǿ������� ����
	//������ �°� ������ ���� ������ ������ ������ �߻��Ѵ�
	
	@Override
	public String getCar() {
		return "���� �ڵ���";
	}
}

class Oncle extends GrandParent{
	
}

public class OverridingMain01 {
	public static void main(String[] args) {

		Father ft = new Father();
		System.out.println(ft.getCar());
		
		Oncle on = new Oncle();
		System.out.println(on.getCar());
	}
}
