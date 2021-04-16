import java.util.Scanner;

public class OperatorMain13 {

	public static void main(String [] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int korean, english, math, sum;
		double avg;
		
		System.out.print("국어점수 : ");
		korean = sc.nextInt();
		
		System.out.print("영어점수 : ");
		english = sc.nextInt();
		
		System.out.print("수학점수 : ");
		math = sc.nextInt();
		
		sum = korean + english + math;
		avg = sum / 3.0;
		
		System.out.println("=====성 적 표=====");
		System.out.println("국어점수 : " + korean);
		System.out.println("영어점수 : " + english);
		System.out.println("수학점수 : " + math);
		System.out.println("총점 : " + sum);
		System.out.println("평균 : "+ avg);
		
		sc.close();
		
	}
}
