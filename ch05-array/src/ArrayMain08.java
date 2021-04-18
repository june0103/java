public class ArrayMain08 {

	public static void main(String[] args) {
		//ArrayMain08을 실행하면서 외부에서 정수 2개를 전달해서 연산하기
		
		//전달된 데이터는 String으로 인식하기 때문에 +연산이 된 것이 아니라 문자열 연결이 됨 [아구먼트에서 10 20 입력시 1020출력이 되는경우)
		System.out.println(args[0] + args[1]);
		
		//String -> int 변환
		int num = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);
		
		System.out.println(num+num2);
	}
}
