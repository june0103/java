import java.util.Scanner;

public class OperatorMain13 {

	public static void main(String [] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int korean, english, math, sum;
		double avg;
		
		System.out.print("�������� : ");
		korean = sc.nextInt();
		
		System.out.print("�������� : ");
		english = sc.nextInt();
		
		System.out.print("�������� : ");
		math = sc.nextInt();
		
		sum = korean + english + math;
		avg = sum / 3.0;
		
		System.out.println("=====�� �� ǥ=====");
		System.out.println("�������� : " + korean);
		System.out.println("�������� : " + english);
		System.out.println("�������� : " + math);
		System.out.println("���� : " + sum);
		System.out.println("��� : "+ avg);
		
		sc.close();
		
	}
}
