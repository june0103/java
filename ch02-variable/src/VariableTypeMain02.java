public class VariableTypeMain02 {

	public static void main(String[] args) {

		System.out.println("=====정수형=====");
		
		//byte, 크기 : 1byte, 표현범위 : -128 ~ 127
		byte b1 = 127;
		System.out.println("b1 = "+b1);
		
		//short, 크기 : 2byte, 표현범위 : -32,768 ~ 32,767
		short s1 = 32767;
		System.out.println("s1 = "+s1);
		
		//int, 크기 : 4byte, 표현범위 : -2,147,483,648 ~ 2,147,483,647
		//java의 정수를 표현할 때 기본 사용
		int i1 = 2147483647;
		System.out.println("i1 = "+i1);
		
		//long, 크기 : 8byte
		long l1 = 12345L;
		System.out.println("l1 = "+l1);
		
		System.out.println("=====실수헝=====");
		
		//float, 크기 : 4byte
		float f1 = 9.1f;
		System.out.println("f1 = "+f1);
		
		//double, 크기 : 8byte
		//실수를 표현할 때 기본사용
		double du = 9.8;
		System.out.println("du = "+du);
		
		System.out.println("=====문자열=====");
		//문자열을 표시할 때 참조자료형을 사용함.
		String str = "Hello World!";
		System.out.println("str = "+str);
	}

}
