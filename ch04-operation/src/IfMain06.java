public class IfMain06 {

	public static void main(String[] args) {
		
		//������ �Է¹޾� ¦���� 10, Ȧ���� 20�� ���Ͽ� ���
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int number, result;
		
		System.out.print("������ �Է��ϼ��� :");
		number = input.nextInt();
		
		if(number%2 == 0) {
			
			result = number + 10;
		}
		else {
			
			result = number + 20;
			
		}
		
		System.out.println("�Է��� ���� : "+ number);
		System.out.println("����� : " + result);
		
		input.close();
		
	}
}
