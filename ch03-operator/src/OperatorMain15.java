import java.util.Scanner;

public class OperatorMain15 {

	public static void main(String [] args){
		
		
		System.out.println("3���� ������ ���� �Է¹޾�, �ִ밪 max�� �ּҰ� min�� ���\r\n"
				+ "		");
		//3���� ������ ���� �Է¹޾�, �ִ밪 max�� �ּҰ� min�� ���
		
		int first, second, third, max, min;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("first �� �Է� : ");
		first = sc.nextInt();
		
		System.out.print("second �� �Է� : ");
		second = sc.nextInt();
		
		System.out.print("third �� �Է� : ");
		third = sc.nextInt();
			
		int mNum = first > second ? first : second;
		max = mNum > third ? mNum : third;
		
		int mMin = first < second ? first : second;
		min = mMin < third ? mMin : third;
		
		System.out.println("�ִ밪 = " + max);
		System.out.println("�ּҰ� = " + min);
		
	
		
		
		
		sc.close();
		
		
	}
}
