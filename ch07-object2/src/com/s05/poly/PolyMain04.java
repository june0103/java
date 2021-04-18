package com.s05.poly;

//�θ�Ŭ����
class Product {
	int price; // ��ǰ����
	int bonusPoint; // ���Ž� ���ʽ� ����Ʈ

	public Product(int price) {
		this.price = price;
		bonusPoint = price / 10;
	}

	public String getName() {
		return "��ǰ";
	}
}

class Tv extends Product {
	public Tv() {
		// �θ�Ŭ���� ������ Ÿ���� int�� �����ڸ� ȣ��
		super(100);
	}

	// �޼���������̵�
	@Override
	public String getName() {
		return "TV";
	}
}

class Computer extends Product {
	public Computer() {
		super(200);
	}

	@Override
	public String getName() {
		return "Computer";
	}

}

class Audio extends Product {
	public Audio() {
		super(300);
	}

	@Override
	public String getName() {
		return "�����";
	}
}

class Buyer {
	int money = 1000;
	int bonusPoint = 0;

	public void buy(Product p) {
		if (money < p.price) {
			System.out.println("�ܾ��� �����Ͽ� ������ ������ �� �����ϴ�.");
			return; // �޼��带 ������ ���������� ���� ��
		}

		money -= p.price;
		bonusPoint += p.bonusPoint;

		System.out.println(p.getName() + "�� �����ϼ̽��ϴ�.");
		System.out.println("���� ���� ���� " + money + "�����Դϴ�.");
		System.out.println("���� ���ʽ� ������ " + bonusPoint + "���Դϴ�.");
	}

}

public class PolyMain04 {
	public static void main(String[] args) {
		Buyer b = new Buyer();

		Tv tv = new Tv();
		Computer computer = new Computer();

		Audio a = new Audio();

		b.buy(tv); // tv -> prodect����ȯ
		b.buy(computer); // computer ->product ����ȯ
		b.buy(a);

	}

}
