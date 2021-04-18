package com.s08.array;

public class CarMain {
	public static void main(String[] args) {
		
		/*
		 * ��ü�� �迭�� ����
		 * �ݺ����� �̿��ؼ� �迭�� ����� ��ü�� ȣ��
		 * �ݺ��� ������ ������ ����
		 * �ݺ����� �������� �ڵ��� �� ���� �ݾ� ���
		 */
		
		Car carArray[] = new Car[4];
		int total = 0;
		
		carArray[0] = new Car("�ƹݶ�", 2000, "�Ͼ��");
		carArray[1] = new Car("�ҳ�Ÿ", 3000, "ȸ��");
		carArray[2] = new Car("�׷���", 4000, "������");
		carArray[3] = new Car("���׽ý�", 5000, "������");
		
		for(int i = 0; i<carArray.length; i++) {
			System.out.println("�� �̸� : " + carArray[i].getName());
			System.out.printf("�� ���� : %,d%n",carArray[i].getPrice());
			System.out.println("�� ���� : " + carArray[i].getColor());
			System.out.println("--------------------");
			total += carArray[i].getPrice();
			
		}
		
		System.out.printf("�� �Ѱ��� : %,d%n",total);
		
		for(Car car : carArray) {
			System.out.println("�̸� : "+ car.getName());
			System.out.printf("���� : %,d%n", car.getPrice());
			System.out.println("���� : "+ car.getColor());
			System.out.println("---------------------");
		}
		
		
		
		
		
	}

}
