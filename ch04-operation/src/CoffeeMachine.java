public class CoffeeMachine {

	public static void main(String[] args) {
		
		/* �ǽ�
		 * Ŀ�Ǹ� �ֹ��ϴ� Ŀ�� �ӽſ��� Ŀ�Ǹ� �ֹ��ϰ� ���� �����ϸ�
		 * ������ ������ ���� üũ�ؼ� ������ ������ �Ž������� �����ϰ�
		 * ������ �����ϸ� �Ž����� ������ ���
		 */
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int price = 300; //Ŀ�ǰ���
		int amount = 0; //�����ݾ�(������)
		int coin = 1000; //�����ݾ�(�Ž�����)
		int payment = 0;
		int change = 0;
		
		while (true) {
			System.out.println("-----------------------");
			System.out.print("1: Ŀ�Ǹ��ñ� | 2: ����");
			int num = input.nextInt();
			if(num == 1) {
				
				while(true) {		
					System.out.println("-----------------------");
					System.out.print("���� �Է�> ");
					int a = input.nextInt(); //���� �ݾ� �Է�
					payment += a; //�����ݾ� ���
					amount += a;
					System.out.printf("�����ݾ� : %d��%n", payment);	
					if(amount >= price) { //Ŀ�Ǹ� ��� �ִ� ���� �־�����	
						
						break;						
					}						
				}
								
				//�Ž����� change ����
				change = payment - price;
				
				if(coin >= change) {
					System.out.println("-------Ŀ�� ���� ��-------");
					coin -= change; //�����ݾ�
					payment = 0; //������ �ʱ�ȭ
					System.out.println("Ŀ�� ����!!");
					System.out.printf("�Ž����� : %d�� ��ȯ%n", change);
					System.out.println("���Ǳ�����");
					System.out.printf("�����ݾ�(������) : %d��%n", amount);
					System.out.printf("�����ݾ�(����) : %d��%n", coin);
					
				}
				else {
					System.out.println("-----------------------");
					System.out.println("�Ž���������");
					System.out.printf("�����ݾ� : %d��%n", payment);
					System.out.printf("�����ݾ�%d�� ��ȯ%n", payment);
					break;					
				}
				
				
				//�Ž����� ���� ���� üũ
				
				//�Ž������� ������ �� ������ �Ž����� ���
				//�Ž������� �����ϸ� "�Ž����� ����"
				
				//���� ���Ǳ� ���� ���
				//�����ݾ�, �Ž����� ��� 
				
			}
			else if(num == 2) {
				break;				
			}
			else if(coin == 0) System.out.println("�Ž���������");
			else {
				
				System.out.println("�ٽ� �Է��� �ּ���.");
				
			}
		}
		
		
		input.close();
		
		
		
	}
}
