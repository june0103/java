package com.s08.array;

public class BookMain {
	
	public static void main(String[] args) {
		//�迭����
		Book bookArray[] = new Book[3];
		int total = 0;
		
		//Book ��ü�� �����ؼ� ������ ����
		bookArray[0] = new Book("IT", "�ڹ��� ����", 10000, 5.0);
		bookArray[1] = new Book("��ȭ", "�ѱ� ��ȭ ����", 20000 , 0.03);
		bookArray[2] = new Book("�̼�", "�������� �׸� �����ϱ�", 30000, 0.06);
		
		//�迭�� ��ü�� �ݺ����� �̿��ؼ� ȣ��
		for(int i = 0; i<bookArray.length; i++) {
			System.out.println("ī�װ� : " + bookArray[i].getCategory());
			System.out.println("å���� : " + bookArray[i].getName());
			System.out.printf("���� : %,d%n", bookArray[i].getPrice());
			System.out.printf("������ : %.2f%n", bookArray[i].getDiscount());
			System.out.println("-----------------------------");
			total += bookArray[i].getPrice();
		}
		
		System.out.printf("å ������ �� : %,d��",total);
		
		
		
	}

}
