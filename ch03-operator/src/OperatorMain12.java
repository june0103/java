import java.util.Scanner;

public class OperatorMain12 {

	public static void main(String [] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int a, b;
		
		System.out.print("ù��° ���� : ");
		
		a = sc.nextInt();
		
		System.out.print("�ι�° ���� : ");
		
		b = sc.nextInt();
		
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		
		int max = a > b ? a : b;
		int min = a < b ? a : b;
		
		System.out.println("max = "+max);
		System.out.println("min = "+min);
		
		sc.close();
		
	}
}
