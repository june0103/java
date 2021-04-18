package com.s01.basic;
public class MethodMain {
	//멤버 메서드
	//반환하는 값이 있는 경우 return
	//int 는 반환 타입
	 public int add(int a, int b) {
		 return a + b;
	 }
	 
	 //반환하는 값이 없는 경우
	 public void print(String str) {
		 System.out.println(str);
		 //일반적을 반환할 값이 없는 경우는 return이 생량된다
		 
	 }
	 
	 
	 public static void main(String[] args) {
		
		 MethodMain m = new MethodMain();
		 
		 
		 m.print(""+m.add(1, 99));
		 m.print("ㅎㅎ");
	}
	
}
