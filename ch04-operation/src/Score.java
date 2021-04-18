public class Score {

	public static void main(String[] args) {
	
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int 국어, 영어, 수학, 충점;
		char 학점;
		float 평균;
		
		//점수는 0~100만 입력 가능
		do {
			System.out.print("국어 >");
			국어 = input.nextInt();
		}while(국어<0 || 국어>100);
		do {
			System.out.print("영어 >");
			영어 = input.nextInt();
		}while(영어<0 || 영어>100);
		do {
			System.out.print("수학 >");
			수학 = input.nextInt();
		}while(수학<0 || 수학>100);
		
		충점 = 국어 + 영어 + 수학;
		평균 = 충점 / 3.0f;
		
		switch((int)(평균/10)) {
		case 10:
		case 9: 학점 = 'A'; break;
		case 8: 학점 = 'B'; break;
		case 7: 학점 = 'C'; break;
		case 6: 학점 = 'D'; break;
		default : 학점 = 'F';
		}
		
		System.out.println();
		
		System.out.printf("총점 : %d%n", 충점);
		System.out.printf("평균 : %.2f%n", 평균);
		System.out.println("학점 : " + 학점 + "학점");
		
		input.close();
		
	}
}
