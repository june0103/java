import java.util.Scanner;

public class OperatorMain16 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		
		int num, won;
		double total;
		String name;
		
		System.out.print("상품명 입력 : ");
		name = sc.next();
		
		System.out.print("단가 입력 : ");
		won = sc.nextInt();
		
		System.out.print("판매 수량 입력 : ");
		num = sc.nextInt();
		
		total = won*num*0.85;
		System.out.printf("%s %,d대의 가격은 %d", name, num, (int)total);
				
		
		sc.close();

	}

}
