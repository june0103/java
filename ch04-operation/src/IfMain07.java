public class IfMain07 {

	public static void main(String[] args) {
		
		//�ΰ��� ������ �Է¹޾� ������ �����ϰ� �ΰ� �� �ִ밪, �ּҰ��� ���ض� 
		//�Է��� �� ���� ������� "�� ���� ����"
		
		java.util.Scanner sc = new java.util.Scanner(System.in);
		
		int a, b, max, min;
		
		System.out.print("ù��° ���� : ");
		a = sc.nextInt();
		System.out.print("�ι�° ���� : ");
		b = sc.nextInt();
				
		if(a>b) {
			
			max = a;
			min = b;
			System.out.println("max : " + max);
			System.out.println("min : " + min);
		}
		else if(a<b) {
			
			max = b;
			min = a;
			System.out.println("max : " + max);
			System.out.println("min : " + min);
			
		}
		else {
			
			System.out.println("�� ���� ����");
			
		}
				
		sc.close();
			
	}
	
}
