class Worker{
	//사람이름, 돈, 계좌 변수를 선언
	//일을 할때마다 천원씩 증가하는 work메소드
	//10번 일하는데 번 돈이 3천원일 때마다 저축하는 deposite 메소드
	//deposite 메소드는 money의 돈을 balance에 누적시키고 money는 0으로 처리
	
	
	String name;
	int money, balance;
	
	public void work() {
		money += 1000;
	}
	
	public void deposite() {
			balance += money;
			money = 0;	
	}
	
}

public class WorkerMain {
	
	public static void main(String[] args) {
		
		Worker w = new Worker();
		w.name = "홍길동";
		
		for(int i = 0; i<10; i++) {
			
			w.work();
			if(w.money == 3000)
				w.deposite();
			
		}
		
		System.out.println("직원 이름 : " + w.name);
		System.out.printf("저축하지 않은 급여 : %,d원%n" , w.money);
		System.out.printf("통장 잔고 : %,d원" , w.balance);
		
	}
}
