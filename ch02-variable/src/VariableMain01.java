public class VariableMain01 {

	public static void main(String[] args) {
		
		//변수는 값을 저장할 수 있는 메모리의 공간
		
		//변수 선언 - 변수명과 메모리 공간을 연결시키는 것		
		int num;
		//변수 초기화
		num = 10; //=은 같다의 뜻이 아닌 대입
		
		System.out.println(num);
		
		//변수 선언 및 초기화를 동시에
		int num1 = 15;
		
		System.out.println(num1);
		
		//변수의 데이터 변경
		num = 20;
		System.out.println(num);
		
		//동일한 변수명으로는 변수 선언 할 수 없다
		//int num = 11; x
		
		//동일한 자료형을 사용하기 때문에 두번째 변수명 앞의 자료형은 생략가능
		int a= 10, b = 20;
		
		int result = a+b;
		
		System.out.println(result);
		System.out.printf("result = %d%n", result);
	
		System.out.println("result = "+result);
		/*
		 * +연산 = 숫자 + 숫자
		 * +연결 = 문자열 + 숫자 -> 새로운 문자열
		 * 		  숫자 + 문자열
		 * 		  문자열 + 문자열
		 */
		
		System.out.println("result = " + a + b); //문자 연결로 출력
		System.out.println("result = " + (a+b)); //숫자 연산으로 출력
		
		//변수 선언 후 출력 또는 연산하기 전에 반드시 초기화 필수
		/* 
		 * int no;
		 * System.out.println(no);
		*/
	}

}
