public class ArrayMain13 {

	public static void main(String[] args) {

		// 2차원 배열 선언, 생성, 초기화 (2행 4열)
		int array[][] = { { 10, 20, 30, 40 }, { 50, 60, 70, 80 } };

		// 2차원 배열 출력
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.println("array[" + i + "][" + j + "] : " + array[i][j]);
			}
		}

	}
}
