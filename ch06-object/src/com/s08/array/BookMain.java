package com.s08.array;

public class BookMain {
	
	public static void main(String[] args) {
		//배열생성
		Book bookArray[] = new Book[3];
		int total = 0;
		
		//Book 객체를 생성해서 베열에 저장
		bookArray[0] = new Book("IT", "자바의 이해", 10000, 5.0);
		bookArray[1] = new Book("영화", "한국 영화 역사", 20000 , 0.03);
		bookArray[2] = new Book("미술", "신윤복의 그림 이해하기", 30000, 0.06);
		
		//배열의 객체를 반복문을 이용해서 호출
		for(int i = 0; i<bookArray.length; i++) {
			System.out.println("카테고리 : " + bookArray[i].getCategory());
			System.out.println("책제목 : " + bookArray[i].getName());
			System.out.printf("가격 : %,d%n", bookArray[i].getPrice());
			System.out.printf("할인율 : %.2f%n", bookArray[i].getDiscount());
			System.out.println("-----------------------------");
			total += bookArray[i].getPrice();
		}
		
		System.out.printf("책 가격의 합 : %,d원",total);
		
		
		
	}

}
