public class ArrayMain10 {

	public static void main(String[] args) {

		// 10,20,30,40,50을 초기값으로 갖는 1차원 배열을 test이름으로 선언,생성,초기화
		// 반복문을 이용하여 test 배열의 요소를 출력
		// 확장for문을 이용하여 출력
		// 인덱스 3의 데이터를 100으로 변경
		// 마지막 요소의 값을 200으로 변경
		// 반복문을 이용하여 모든 인덱스의 값을 0으로 초기화
		// 홀수 인덱스에 10, 짝수 인덱스에 20으로 초기화
		// 모든 인덱스의 총합과 평균을 구하고 출력

		// 암시적 배열생성
		int test[] = { 10, 20, 30, 40, 50 };

		// for반복문을 이용하여 배열 출력
		for (int i = 0; i < test.length; i++) {

			System.out.println(test[i]);

		}

		System.out.println("--------------");

		// 확장for문을 이용하여 배열 출력
		for (int i : test) {

			System.out.println(i);

		}

		System.out.println("--------------");

		// 인덱스3의 데이터를 100으로 변경
		test[3] = 100;
		System.out.println(test[3]);

		System.out.println("--------------");

		// 마지막 인덱스의 데이터를 200으로 변경
		// test[4] = 200;
		test[test.length - 1] = 200;
		System.out.println(test[4]);

		System.out.println("--------------");
		for (int i : test) {

			System.out.println(i);

		}

		// 반복문을 이용하여 모든 인덱스의 값을 0으로 초기화
		for (int i = 0; i < test.length; i++) {

			test[i] = 0;
			System.out.println(test[i]);

		}
		System.out.println("--------------");

		// 홀수 인덱스에 10, 짝수 인덱스에 20으로 초기화
		for (int i = 0; i < test.length; i++) {

			if (i % 2 == 0) {
				test[i] = 20;
			} else {
				test[i] = 10;
			}

		}
		System.out.println("--------------");
		for (int i : test) {

			System.out.println(i);

		}

		// 모든 인덱스의 총합과 평균을 구하고 출력
		int sum = 0, avg = 0;
		for (int i = 0; i < test.length; i++) {

			sum += test[i];
			avg = sum / test.length;

		}
		System.out.println("--------------");
		System.out.println("sum = " + sum);
		System.out.println("avg = " + avg);

	}
}
