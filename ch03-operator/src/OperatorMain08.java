public class OperatorMain08 {

	public static void main(String[] args) {
		
		int a, b;
		a = b = 10;
		
		boolean c = a++ >= ++b && ++a > b++;
		//			10  >= 11 f&&t 11 > 10  ����� f
		System.out.println(c);
		
		int d, e;
		
		d = e = 10;
		
		boolean f = ++d > e++ || d++ >= ++e;
		//			11 > 10 t || ���������� �̹� true�� ������ �������� ������ ���� ��
		System.out.println(f);
		
		boolean g = ++d < e++ || d++ >= ++e;
		//			11 < 10 f || 11 >= 12 f
		System.out.println(g);

	}

}
