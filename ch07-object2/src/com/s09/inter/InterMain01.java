package com.s09.inter;

interface A1 {
	// 상수
	//인터페이스에 선언된 상수는 변경불가능
	//접근제어자 static final이 생략되도 다 똑같다
	public static final int W = 10;
	int X = 20;
	static int Y = 30;
	final int Z = 40;
}

public class InterMain01 {
	public static void main(String[] args) {
		//인터페이스는 객체 생성 불가능
		//A1 ap = new A1();
		
		//상수는 변경 불가능
		//A1.Y = 100;
	
		System.out.println("W = "+A1.W);
		System.out.println("X = "+A1.X);
		System.out.println("Y = "+A1.Y);
		System.out.println("Z = "+A1.Z);
	}
}
