import java.util.Scanner;

public class OperatorMain16 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		
		int num, won;
		double total;
		String name;
		
		System.out.print("��ǰ�� �Է� : ");
		name = sc.next();
		
		System.out.print("�ܰ� �Է� : ");
		won = sc.nextInt();
		
		System.out.print("�Ǹ� ���� �Է� : ");
		num = sc.nextInt();
		
		total = won*num*0.85;
		System.out.printf("%s %,d���� ������ %d", name, num, (int)total);
				
		
		sc.close();

	}

}
