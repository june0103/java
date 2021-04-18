public class ArrayMain04 {

	public static void main(String[] args) {
		
		//최대값, 최소값 구하기
		int score[] = {79, 88, 91, 33, 100, 55, 95};
		
		int max = score[0]; //배열의 첫번째 값으로 최대값을 지정하여 비교
		int min = score[0];
		
		for(int i = 1; i<score.length; i++) {
			//최대값 구하기
			if(score[i] > max) {
				max = score[i];
			}
			//최소값 구하기
			if (score[i] < min) {
				min = score[i];
			}
		}
		
		System.out.println(max);
		System.out.println(min);
	}
	
}
