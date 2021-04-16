public class PrintMain05 {

	public static void main(String[] args) {
		/*
		 * System.out.printf(포맷문자, 데이터)
		 */
		
		//문자
		System.out.printf("%c\n", 'A'); //\n줄바꿈 특수문자 print사용가능
		System.out.printf("%c%n", '강'); //%n 줄바꿈 포맷문자에서만
		System.out.printf("%c%n",'X');

		System.out.println("-----------");
		
		//정수
		System.out.printf("%d\n", 23);
		System.out.printf("%,d\n", 10000); //%d사이에 쉼표. 3자리씩 끊어준다

		System.out.println("-----------");

		//실수
		System.out.printf("%f%n", 35.896);
		System.out.printf("%.3f\n", 35.896); //%f사이에 .자릿수를 삽입한다
		System.out.printf("%.2f%n", 35.896);
		
		System.out.println("-----------");

		//문자열
		System.out.printf("%s\n", "우주");
		
		System.out.println("-----------");

		//논리값
		System.out.printf("%b\n",true);
		
		System.out.println("-----------");

		//종류가 다른 여려 개의 데이터 출력
		System.out.printf("%s는 %d점입니다.", "점수",100);
		
	}

}
