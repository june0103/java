public class SwitchMain03 {

	public static void main(String[] args) {
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int score;
		char grade;
		
		System.out.print("성적 입력>");
		score = input.nextInt();
		
		if(score < 0 || score >100) {
			
			System.out.println("잘못 입력했습니다.");
			//프로그램 종료
			System.exit(0);
		}
		switch(score/10) { //0~10 범위로 만들어준다
		
		case 10: //10과 9의 결과는 같기에 19의 코드는 삭제해도 9가실행되어 결과가 같다
				//프로그램 줄이는 방법
		case 9:
			grade = 'A';			
			break;
		case 8:
			grade = 'B';			
			break;
		case 7:
			grade = 'C';			
			break;
		case 6:
			grade = 'D';			
			break;
		default:
			grade = 'F';
			
		}
		
		System.out.printf("점수 : %d%n", score);
		System.out.printf("등급 : %c%n", grade);
		
		
		input.close();
		
	}
}
