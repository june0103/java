public class CoffeeMachine02 {

	public static void main(String[] args) {

		/*
		 * �ǽ� Ŀ�Ǹ� �ֹ��ϴ� Ŀ�� �ӽſ��� Ŀ�Ǹ� �ֹ��ϰ� ���� �����ϸ� ������ ������ ���� üũ�ؼ� ������ ������ �Ž������� �����ϰ� ������
		 * �����ϸ� �Ž����� ������ ���
		 */

		java.util.Scanner input = new java.util.Scanner(System.in);

		int price = 300; // Ŀ�ǰ���
		int amount = 0; // �����ݾ�(������)
		int coin = 1000; // �����ݾ�(�Ž�����)
	

		while (true) {
			System.out.println("-----------------------");
			System.out.print("1: Ŀ�Ǹ��ñ� | 2: ����");
			int num = input.nextInt();
			if (num == 1) {
				
				System.out.print("���� �Է�>");
				//���� �ݾ�
				int payment = input.nextInt();
				//�Ž�����
				int change = payment - price;
				
				if(payment < price) {
					System.out.println("������ �ݾ� ����!!");
					continue;
				}
				//�Ž����� ���ҿ��� üũ
				if(change > coin) {
					//�Ž������� �����ϸ�
					System.out.println("�Ž����� ����!");
					continue;
				}
				
				//�Ž����� ���� ���ɽ�
				coin -= change;
				amount += payment;
				
				//�Ž����� ���
				System.out.printf("�Ž����� : %,d��%n", change);
				System.out.println("�� ���� Ŀ�ǰ� �غ�Ǿ����ϴ�.");
				
				//���� ���Ǳ� ���� ���
				System.out.println("===���� ���Ǳ� ����===");
				System.out.printf("���������ݾ� : %,d��%n", coin);
				System.out.printf("�� ���Աݾ� : %d��%n", amount);
			
				
			}
			else if(num == 2) {
				System.out.println("���Ǳ� ���α׷� ����");
				break;
			}
			else {
				System.out.println("�߸� �Է��߽��ϴ�.");
			}
			

		}
		input.close();
	}
}
