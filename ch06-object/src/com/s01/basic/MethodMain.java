package com.s01.basic;
public class MethodMain {
	//��� �޼���
	//��ȯ�ϴ� ���� �ִ� ��� return
	//int �� ��ȯ Ÿ��
	 public int add(int a, int b) {
		 return a + b;
	 }
	 
	 //��ȯ�ϴ� ���� ���� ���
	 public void print(String str) {
		 System.out.println(str);
		 //�Ϲ����� ��ȯ�� ���� ���� ���� return�� �����ȴ�
		 
	 }
	 
	 
	 public static void main(String[] args) {
		
		 MethodMain m = new MethodMain();
		 
		 
		 m.print(""+m.add(1, 99));
		 m.print("����");
	}
	
}
