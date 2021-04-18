package com.s04.superex;

class Mother {
	public String getLunch() {
		return "¹ä";
	}
}

class Son extends Mother {

}

class Daughter extends Mother {

	@Override
	public String getLunch() {

		return "»§";
	}

	public String getRice() {

		return super.getLunch();
	}

}

public class SuperMain01 {
	public static void main(String[] args) {
		Daughter d = new Daughter();
		System.out.println("µşÀº " + d.getLunch() + "À» ¸Ô½À´Ï´Ù.");
		System.out.println("µşÀº ¿À´ÃÀº ¿ØÁö " + d.getRice() + "ÀÌ ¸Ô°í½Í¾î¿ä.");
	}

}
