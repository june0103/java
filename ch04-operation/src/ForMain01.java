public class ForMain01 {

	public static void main(String[] args) {
		
		
		//for(초기시; 조건식; 증감식)
		for(int i=1; i<=5; i++)
		{ //for문 시작
			System.out.println(i+"번째 실행");
		} //for문 끝
		
		System.out.println("------------");
		
		//수행문이 한 줄일 경우 for문 {} 생략 가능
		for(int i=1; i<=5; i++)
			System.out.println(i);
		
		System.out.println("프로그램 끝");
	}
}
