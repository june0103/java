public class OperatorMain02 {

	public static void main(String []args) {
		
		System.out.println("=====증감연산자=====");
		
		int i = 5;
		
		System.out.println(i++); //++가 뒤에 붙으면 +연산을 한 뒤 메모리공간에 저장 출력:5
		System.out.println(i);	//연산 후 메모리공간에 저장된 값을 불러온다 출력:6
		
		
		int j = 10;
		
		System.out.println(j--); //--가 뒤에 붙으면 -연산을 한 뒤 메모리공간에 저장
		System.out.println(j);	//연산 후 메모리 공간에 저장된 값을 불러온다
	}
}
