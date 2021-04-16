import java.util.Scanner;

public class OperatorMain15 {

	public static void main(String [] args){
		
		
		System.out.println("3개의 정수에 값을 입력받아, 최대값 max와 최소값 min을 출력\r\n"
				+ "		");
		//3개의 정수에 값을 입력받아, 최대값 max와 최소값 min을 출력
		
		int first, second, third, max, min;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("first 값 입력 : ");
		first = sc.nextInt();
		
		System.out.print("second 값 입력 : ");
		second = sc.nextInt();
		
		System.out.print("third 값 입력 : ");
		third = sc.nextInt();
			
		int mNum = first > second ? first : second;
		max = mNum > third ? mNum : third;
		
		int mMin = first < second ? first : second;
		min = mMin < third ? mMin : third;
		
		System.out.println("최대값 = " + max);
		System.out.println("최소값 = " + min);
		
	
		
		
		
		sc.close();
		
		
	}
}
