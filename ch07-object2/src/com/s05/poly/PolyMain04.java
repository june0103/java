package com.s05.poly;

//부모클래스
class Product {
	int price; // 제품가격
	int bonusPoint; // 구매시 보너스 포인트

	public Product(int price) {
		this.price = price;
		bonusPoint = price / 10;
	}

	public String getName() {
		return "상품";
	}
}

class Tv extends Product {
	public Tv() {
		// 부모클래스 인자의 타입이 int인 생성자를 호출
		super(100);
	}

	// 메서드오버라이딩
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
		return "오디오";
	}
}

class Buyer {
	int money = 1000;
	int bonusPoint = 0;

	public void buy(Product p) {
		if (money < p.price) {
			System.out.println("잔액이 부족하여 물건을 구매할 수 없습니다.");
			return; // 메서드를 끝내고 빠져나가고 싶을 때
		}

		money -= p.price;
		bonusPoint += p.bonusPoint;

		System.out.println(p.getName() + "을 구입하셨습니다.");
		System.out.println("현재 남은 돈은 " + money + "만원입니다.");
		System.out.println("현재 보너스 점수는 " + bonusPoint + "점입니다.");
	}

}

public class PolyMain04 {
	public static void main(String[] args) {
		Buyer b = new Buyer();

		Tv tv = new Tv();
		Computer computer = new Computer();

		Audio a = new Audio();

		b.buy(tv); // tv -> prodect형변환
		b.buy(computer); // computer ->product 형변환
		b.buy(a);

	}

}
