public class IfMain02 {

	public static void main(String [] args) {
		
		//Ȧ��, ¦�� �Ǻ���
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		System.out.print("������ ������ �Է� : ");
		int a = input.nextInt();
		
		if(a%2 == 1) { //Ȧ��
			
			System.out.println("Ȧ���Դϴ�.");
			
		}
		else { //¦��
			
			System.out.println("¦���Դϴ�.");
			
		}
		
		
		input.close();
	}
}
