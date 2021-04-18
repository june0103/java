public class ArrayMain16 {

	public static void main(String[] args) {
		
		int array1[] = {11, 22, 33, 44, 55};
		int array2[] = new int[10];
		
		//배열 복사  
		//(원본, 원본 배열의 시작위치, 복사되는 위치(전송처), 전송처 내의 복사위치, 복사되는 배열의 요소의 수)
		System.arraycopy(array1, 0, array2, 3, 5);
		
		System.out.println("원본 배열");
		for(int i : array1) {
			System.out.print(i + " ");
			
		}
		System.out.println("\n복사본 배열");
		for(int j : array2) {
			System.out.print(j + " ");
		}
		
		
	}
}
