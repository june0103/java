package com.s03.overriding;

//�θ�Ŭ����
class Mather{
	public String getLunch() {
		return "��";
	}
}

class Son extends Mather{
	
}

class Daughter extends Mather{
	
	@Override
	public String getLunch() {
		
		return "��";
	}
}

public class OverridingMain02 {
	public static void main(String[] args) {
		Son s = new Son();
		System.out.println("�Ƶ��� " + s.getLunch() + "�� �Խ��ϴ�.");
		Daughter d = new Daughter();
		System.out.println("���� " + d.getLunch() + "�� �Խ��ϴ�.");
	}
}
