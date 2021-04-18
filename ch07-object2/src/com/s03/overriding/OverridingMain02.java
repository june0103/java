package com.s03.overriding;

//ºÎ¸ðÅ¬·¡½º
class Mather{
	public String getLunch() {
		return "¹ä";
	}
}

class Son extends Mather{
	
}

class Daughter extends Mather{
	
	@Override
	public String getLunch() {
		
		return "»§";
	}
}

public class OverridingMain02 {
	public static void main(String[] args) {
		Son s = new Son();
		System.out.println("¾ÆµéÀº " + s.getLunch() + "À» ¸Ô½À´Ï´Ù.");
		Daughter d = new Daughter();
		System.out.println("µþÀº " + d.getLunch() + "À» ¸Ô½À´Ï´Ù.");
	}
}
