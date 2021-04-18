package com.s08.array;

public class CarMain {
	public static void main(String[] args) {
		
		/*
		 * 객체를 배열에 저장
		 * 반복문을 이용해서 배열에 저장된 객체를 호출
		 * 반복문 내에서 가격을 누적
		 * 반보문을 빠져나와 자동차 총 구매 금액 출력
		 */
		
		Car carArray[] = new Car[4];
		int total = 0;
		
		carArray[0] = new Car("아반떼", 2000, "하얀색");
		carArray[1] = new Car("소나타", 3000, "회색");
		carArray[2] = new Car("그렌져", 4000, "검정색");
		carArray[3] = new Car("제네시스", 5000, "검정색");
		
		for(int i = 0; i<carArray.length; i++) {
			System.out.println("차 이름 : " + carArray[i].getName());
			System.out.printf("차 가격 : %,d%n",carArray[i].getPrice());
			System.out.println("차 색상 : " + carArray[i].getColor());
			System.out.println("--------------------");
			total += carArray[i].getPrice();
			
		}
		
		System.out.printf("차 총가격 : %,d%n",total);
		
		for(Car car : carArray) {
			System.out.println("이름 : "+ car.getName());
			System.out.printf("가격 : %,d%n", car.getPrice());
			System.out.println("색상 : "+ car.getColor());
			System.out.println("---------------------");
		}
		
		
		
		
		
	}

}
