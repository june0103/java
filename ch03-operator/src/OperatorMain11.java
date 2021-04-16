public class OperatorMain11 {

	public static void main(String [] args) {
		
		System.out.println("=====조건 연산자(삼항연산자)=====");
		
		char ch = 'b';
		String s;
		
		s = (ch>= 'A' && ch<='Z') ? "대문자" : "대문자가 아님";
		
		System.out.println(ch+"는 "+s);
		
	}
}
