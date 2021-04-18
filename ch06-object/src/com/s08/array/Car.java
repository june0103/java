package com.s08.array;

public class Car {
	
	/*
	 * 멤버변수 : 색깔, 가격, 이름
	 * 		  : 접근지정자는 private로 은닉화
	 * 생성자 : 인자가 없느 ㄴ생성자와 인자가 있는생성자
	 * 멤버메서드 : set get
	 */

	private String name, color;
	private int price;
	
	public Car() {}
	
	public Car(String name, int price , String color) {
		this.name = name;
		this.price = price;
		this.color = color;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}	
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
}
