package com.s08.abs;

abstract class AbsTest1 {
	abstract public void make();

	abstract public void play();

	public void print() {
		System.out.println("AbsTest1�� print");
	}
}

abstract class AbsTest2 extends AbsTest1 {

	// ���������ڿ� �߻�Ű������ ������ �������

	public abstract void take();

	@Override
	public void make() {
		System.out.println("AbsTest2�� make");
	}
}

class AbsTest3 extends AbsTest2 {
	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println("AbsTest3�� play");
	}

	@Override
	public void take() {
		System.out.println("AbsTest3�� take");
	}
}

public class AbsMain03 {
	public static void main(String[] args) {

	}
}
