public class VariableTypeMain01 {

	public static void main(String[] args) {
		
		System.out.println("=====논리형=====");
		
		boolean b = true;
		
		System.out.println("b = "+b);
		
		System.out.println("=====문자형=====");
		//문자형, 크기 : 2byte, 표현범위 : 0 ~ 65,535
		//다국얼르 처리하기 위해 유니코드 방식을 사용
		
		char c1 = 'A'; //내부적으로 아스키코드 값 65
		
		System.out.println(c1);
		
		char c2 = 65; //A에 해당하는 아스키코드 값
		
		System.out.println(c2);
		
		char c3 = '\u0041'; //A에 해당하는 유니코드 값 
		
		System.out.println(c3);
		
		char c4 = '자';
		
		System.out.println(c4);
		
		char c5 = '\uc790'; //자 에 해당하는 유니코드 값
		
		System.out.println(c5);
		
	}
}
