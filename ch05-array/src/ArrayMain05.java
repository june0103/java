public class ArrayMain05 {

	public static void main(String[] args) {
		
		//배열 선언, 생성, 초기화
		int array[] = {10, 20, 30, 40, 50};
		
		//반복문을 이용한 배열의 요소 출력
		for(int i = 0; i<array.length; i++) {
			System.out.println("array[" + i + "] : " + array[i]);
		}
		
		//배열에서만 사용가능한 for문 , 개선된 루프(확장 for문)
		//배열의요소가 저장되는 변수 : 벼열명
		for(int num : array) {
			System.out.println(num);
		}
	}
	
}
