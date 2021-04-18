public class InstanceMain {

	//멤버 메서드
	public int sum(int a, int b){
		return a + b;
	}
	
	public static void main(String[] args) {
		//객체 선언 및 생성
		InstanceMain im = new InstanceMain();
		//객체의 멤버 메서드 호출
		int result = im.sum(10, 20);
		
		System.out.println(result);
		
	}
	
}
