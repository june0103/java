package com.s01.basic;
class Account {

	// �ǽ�
	// Account
	// ������� : ���¹�ȣ(account_num), ������(name), �ܰ�(balance)
	// ����޼��� : �����ϱ�(deposite), ����ϱ�(withdraw), ��������ǥ���ϱ�(printAccount)

	int account_num, balance;
	String name;

	public void deposite(int a) {
		balance += a;
	}

	public void withdraw(int a) {
		balance -= a;

	}

	public void printAccount() {

		System.out.println("���¹�ȣ : " + account_num);
		System.out.println("������ : " + name);
		System.out.printf("�ܰ� : %,d%n", balance);

	}

}

public class BankMain {

	public static void main(String[] args) {

		Account b = new Account();

		b.account_num = 1013451245;
		b.name = "ȫ�浿";
		b.balance = 0;

		System.out.println("=====��������=====");
		b.printAccount();
		System.out.println("=====�Ա��ϱ�=====");
		System.out.println("�Աݱݾ� : 4,000��");
		b.deposite(4000);
		System.out.println("=====��������=====");
		b.printAccount();
		System.out.println("=====����ϱ�=====");
		System.out.println("�Աݱݾ� : 1,000��");
		b.withdraw(1000);
		System.out.println("=====��������=====");
		b.printAccount();
	}

}
