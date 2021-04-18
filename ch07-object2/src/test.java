import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String name, grade;
		String subject[] = { "국어", "영어", "수학", "과학" };
		int score[] = new int[subject.length];
		int sum = 0;
		float avg = 0.0f;

		System.out.print("이름 입력>");
		name = sc.next();

		for (int i = 0; i < score.length; i++) {
			do {
				System.out.print(subject[i] + " 점수입력>");
				score[i] = sc.nextInt();
			} while (score[1] < 0 || score[i] > 100);

			sum += score[i];
		}

		avg = sum / (float) score.length;

		System.out.println("-------------");
		System.out.println("이름 : " + name);

		for (int i = 0; i < score.length; i++) {
			System.out.printf("%s점수 : %d%n", subject[i], score[i]);
			switch (score[i] / 10) {
			case 10:
			case 9:
				grade = "A";
				break;
			case 8:
				grade = "B";
				break;
			case 7:
				grade = "C";
				break;
			case 6:
				grade = "D";
				break;
			default:
				grade = "F";
			}
			System.out.printf("%s등급 : %s%n", subject[i], grade);
			System.out.println("-------------");
		}
		System.out.println("총점 : " + sum);
		System.out.println("평균 : " + avg);

		sc.close();
	}

}
