public class WhileMain06 {

	public static void main(String[] args) {
		
		/* �ǽ�
		 * Ŀ�� �Ƹ޸�ī�� 4,000
		 * ���� Ŀ�� ���� ���ϰ� ���� ����
		 * ������ ������ �߻��� �Ž����� ���
		 * ��ǰ�� �� ��뺸�� ������ ���� ��� ������ �� ���� ���
		 * "�ݾ��� �����մϴ�." ����ϰ� �ٽ� ���� ����
		 *  ��¿���
		 *  ���� ���� �Է� : 2
		 *  ���� ������ �ݾ� : 10000
		 *  �Ž����� : 2000��
		 *  
		 *  ���� ���� �Է� : 2
		 *  ���� ������ �ݾ� : 5000
		 *  3000���� �����մϴ�.
		 */
		
		int price = 4000;
		int balance = 0;
		int quantity,payment,total;
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		while(true)
		{
			System.out.print("���� ���� �Է� : ");
			quantity = input.nextInt();
			total = price * quantity;
			System.out.print("���� ������ �ݾ� : ");
			payment = input.nextInt();
			balance = payment - total;
			
			
			if(payment >= total) {
				
				System.out.printf("�Ž����� : %,d��%n",balance);
				break;
			}
			else {
				System.out.printf("%,d���� �����մϴ�.%n",-balance);
			}			
		}
		input.close();
		
	}
	
}
