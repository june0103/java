class Worker{
	//����̸�, ��, ���� ������ ����
	//���� �Ҷ����� õ���� �����ϴ� work�޼ҵ�
	//10�� ���ϴµ� �� ���� 3õ���� ������ �����ϴ� deposite �޼ҵ�
	//deposite �޼ҵ�� money�� ���� balance�� ������Ű�� money�� 0���� ó��
	
	
	String name;
	int money, balance;
	
	public void work() {
		money += 1000;
	}
	
	public void deposite() {
			balance += money;
			money = 0;	
	}
	
}

public class WorkerMain {
	
	public static void main(String[] args) {
		
		Worker w = new Worker();
		w.name = "ȫ�浿";
		
		for(int i = 0; i<10; i++) {
			
			w.work();
			if(w.money == 3000)
				w.deposite();
			
		}
		
		System.out.println("���� �̸� : " + w.name);
		System.out.printf("�������� ���� �޿� : %,d��%n" , w.money);
		System.out.printf("���� �ܰ� : %,d��" , w.balance);
		
	}
}
