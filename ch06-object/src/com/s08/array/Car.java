package com.s08.array;

public class Car {
	
	/*
	 * ������� : ����, ����, �̸�
	 * 		  : ���������ڴ� private�� ����ȭ
	 * ������ : ���ڰ� ���� �������ڿ� ���ڰ� �ִ»�����
	 * ����޼��� : set get
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
