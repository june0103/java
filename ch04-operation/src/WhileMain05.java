public class WhileMain05 {

	public static void main(String[] args) {
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int balance = 0;
		
		while(true)	{
			System.out.println("-----------------------------");
			System.out.println("1.���� | 2.��� | 3.�ܰ� | 4.����");
			System.out.println("-----------------------------");
			
			System.out.print("�޴� ����> ");
			int num = input.nextInt();
			
			if(num ==1 ) {
				System.out.println("���ݾ�> ");
				balance += input.nextInt()	; //����
			}
			else if(num ==2 ) {
				System.out.println("��ݾ�> ");
				balance -= input.nextInt(); //����
				
			}
			else if(num ==3 ) {
				System.out.printf("�ܰ� : %,d��%n",balance);
			}
			else if(num ==4 ) {
				System.out.println("���α׷� ����");
				break;
				
			}
			else {
				System.out.println("�ٽ��Է����ּ���.");
			}
		}
		
		
		input.close();
	}
	
}
