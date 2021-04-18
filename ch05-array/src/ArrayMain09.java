public class ArrayMain09 {

	public static void main(String[] args) {

		java.util.Scanner input = new java.util.Scanner(System.in);

		String course[] = { "국어", "영어", "수학" };
		int score[] = new int[course.length];
		int sum = 0;
		float avg = 0.0f;

		// 성적입력
		for (int i = 0; i < course.length; i++) {
			do {
				System.out.print(course[i] + "=");
				score[i] = input.nextInt();
			} while (score[1] < 0 || score[i] > 100);
			// 초점구하기
			sum += score[i];
		}

		// 평균구하기
		avg = sum / (float) score.length;

		// 국어, 영어, 수학 점수 출력
		for (int i = 0; i < score.length; i++) {
			System.out.printf("%s = %d%n", course[i], score[i]);
		}

		// 총점, 평균 출력
		System.out.println("총점 = " + sum);
		System.out.println("평균 = " + avg);

		input.close();
	}
}
