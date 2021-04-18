public class Student04 {
	//멤버변수, 객체를 생성시 객체에 포함된다
		String name;
		int korean;
		int math;
		int english;
		
		//멤버메서드, 이미 선언된 멤버변수는 인자로 받지않고 직접 받아서 작업이 가능하다
		//총점 구하는 메서드
		public int makeSum() {
			
			return korean + english + math;	
		}
		
		//멤버메서드, 선언된 메서드를 직접 가져와 사용이 가능하다
		//평균 구하는 메서드
		public int makeAverage() {
			
			return makeSum() / 3;
		}
		
		
		public static void main(String[] args) {
			//객체 선언 및 생성
			Student04 s = new Student04();
			
			s.name = "홍길동";
			s.korean = 98;
			s.english = 97;
			s.math = 96;
						
			System.out.println("이름 : " + s.name);
			System.out.println("국어 : " + s.korean);
			System.out.println("영어 : " + s.english);
			System.out.println("수학 : " + s.math);
			System.out.println("총점 : " + s.makeSum());
			System.out.println("평균 : " + s.makeAverage());
		}
}
