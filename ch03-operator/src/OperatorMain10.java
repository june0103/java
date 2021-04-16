public class OperatorMain10 {

	public static void main(String []args) {
		
		System.out.println("=====조건 연산자(삼항연산자)=====");
		int a = 15;
		int b = 100;
		int max, min;
		
		max = a > b ? a : b;
		min = a < b ? a : b;
		
		System.out.println(max);
		System.out.println(min);
		
	}
}
