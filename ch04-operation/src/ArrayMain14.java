public class ArrayMain14 {

	public static void main(String[] args) {
		
		int score[][] = {
				{99, 98, 97}, {96, 95, 94}, {93, 92, 91}, {90, 89, 88}, {87, 86, 85}
		};
		
		System.out.println("번호 국어 영어 수학 총점 평균");
		System.out.println("======================");
		
		for(int i = 0; i<score.length; i++) { //행번호
			
			int sum = 0;
			
			System.out.print(" " + (i + 1) + "  ");
			
			for(int j = 0; j<score[i].length; j++) {
				//총점 구하기
				sum += score[i][j];
				
				//과목 성적 출력
				System.out.print(score[i][j] + "  ");
				
			}
			
			//평균구하기
			System.out.println(sum + "  " + sum/score[i].length);
			
		}
		
		
	}
	
}
