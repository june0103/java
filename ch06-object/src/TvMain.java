//하나의 자바파일에 여러가지의 클래스를 만들수 있다.
//컴파일시켜 실행을하면 매인메소드가있는 클래스가 주 클래스로 실행된다.
//접근자 public은 메인메소드가있는 클래스만 사용가능하다
class Tv{
	//멤버변수
	boolean power; //전원상태 (on / off)
	int channel; //채널

	//멤버메소드
	public void isPower() {
		power = !power; //전원을 on/off 한다.  토글형식
		
	}
	
	public void channelUp() {
		++channel; //채널을 높이는 기능
	}
	
	public void channelDown() {
		--channel; //채널을 낮추는 기능
	}
}

public class TvMain {
	
	public static void main(String[] args) {

		Tv tv = new Tv();
		tv.isPower(); //false -> true
		System.out.println("TV 실행 여부 : " + tv.power);
		System.out.println("현재 채널 : " + tv.channel);
		
		System.out.println("---------------------");
		tv.channel = 7;
		System.out.println("첫번째 변경된 채널 : " + tv.channel);

		System.out.println("---------------------");
		tv.channelUp();
		System.out.println("두번째 변경된 채널 : " + tv.channel);
		
		System.out.println("---------------------");
		tv.isPower();
		System.out.println("TV 실행 여부 : " + tv.power);
	}
}
