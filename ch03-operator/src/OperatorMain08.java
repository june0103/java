public class OperatorMain08 {

	public static void main(String[] args) {
		
		int a, b;
		a = b = 10;
		
		boolean c = a++ >= ++b && ++a > b++;
		//			10  >= 11 f&&t 11 > 10  결과는 f
		System.out.println(c);
		
		int d, e;
		
		d = e = 10;
		
		boolean f = ++d > e++ || d++ >= ++e;
		//			11 > 10 t || 선행조건이 이미 true기 때문에 후조건의 결과상관 없이 참
		System.out.println(f);
		
		boolean g = ++d < e++ || d++ >= ++e;
		//			11 < 10 f || 11 >= 12 f
		System.out.println(g);

	}

}
