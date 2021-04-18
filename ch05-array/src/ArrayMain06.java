public class ArrayMain06 {

	public static void main(String[] args) {
		
		//객체형 배열
		String array[] = new String[5];
		
		array[0] = "Java";
		array[1] = "JSP";
		array[2] = "Servlet";
		array[3] = "Spring";
		array[4] = "Android";
		
		//반복문을 이용한 배열의 요소 출력
		for(int i = 0; i<array.length; i++) {
			System.out.println(array[i]);
		}
		
		//확장for문을 이용한 배열의 요소 출력
		for(String str : array) {
			System.out.println(str);
		}
		
	}
}
