public class ForMain03 {

	public static void main(String[] args) {
		
		//1부터 100까지의 합을 for문으로 구하기
		int sum = 0;
		
		for(int i=1; i<=100; i++) {
			
			sum += i; //누적은 대입연산자를 사용
			
		}
		
		System.out.print(sum);
	}
}
