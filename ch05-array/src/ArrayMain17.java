public class ArrayMain17 {

	public static void main(String[] args) {

		// 정수를 5개 입력받아 배열에 저장하고, 저장된 정수 중 최대값과 최소값을 구해서 출력

		java.util.Scanner input = new java.util.Scanner(System.in);

		int array[] = new int[5];
		int max = 0, min = 0;

		for (int i = 0; i < array.length; i++) {

			System.out.print("정수 입력> ");
			array[i] = input.nextInt();

		}

		System.out.println("===저장된 배열===");
		for (int j : array) {
			System.out.print(j + " ");
		}

		java.util.Arrays.sort(array);

		System.out.println("\n===정리된 배열===");
		for (int h : array) {
			System.out.print(h + " ");
		}

		min = array[0];
		max = array[array.length - 1];

		System.out.println("\n최소값 > " + min);
		System.out.println("최대값 > " + max);

		input.close();

	}
}
