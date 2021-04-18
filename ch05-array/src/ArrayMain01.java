public class ArrayMain01 {

	public static void main(String[] args) {
		
		char[]	ch; //배열 선언
		ch = new char[4]; //배열 생성
		
		//배열 초기화,  배열의 주소값을 인덱스라하며 0서부터 시작한다
		ch[0] = 'J';
		ch[1] = 'a';
		ch[2] = 'v';
		ch[3] = 'a';
		
		//배열에 저장된 데이터를 출력
		System.out.println(ch[0]);;
		System.out.println(ch[1]);;
		System.out.println(ch[2]);;
		System.out.println(ch[3]);;
		
		//반복문을 이용하여 배열의 요소를 출력
		for(int i = 0; i<ch.length; i++) {
			System.out.println("ch["+i+"] : " + ch[i]);
		}
		
		//배열의 선언과 생성을 동시에
		int it[] = new int[6];
		
		//배열을 생성시에 초기화를 하지 않아도 기본값으로 초기화가 되어있다.
		//각 자료형의 기본값은 기본자료형의 종류에서 보자
		for(int i = 0; i<it.length; i++) {
			System.out.println("it["+i+"] : " + it[i]);
		}
		
		//배열의 선언, 생성(명시적 배열 생성), 초기화 동시에
		char ch2[] = new char[] {'J', 'a', 'v', 'A'};
		
		for(int i = 0; i<ch2.length; i++) {
			System.out.println("ch2["+i+"] : " + ch2[i]);
		}
		
		//배열의 선언, 생성(암시적 배열 생성), 초기화 동시에
		char ch3[] = {'자', '바'};
		for(int i = 0; i<ch3.length; i++) {
			System.out.println("ch3["+i+"] : " + ch3[i]);
		}
	}
}
