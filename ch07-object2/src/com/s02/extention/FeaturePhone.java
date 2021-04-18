package com.s02.extention;

public class FeaturePhone extends Phone {
	private int pixel;

	public int getPixel() {
		return pixel;
	}

	public FeaturePhone(String number, String model, String color, int pixel) {
		this.number = number;
		this.model = model;
		this.color = color;
		this.pixel = pixel;
	}
}
