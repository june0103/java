public class PrintMain07 {

	public static void main(String[] args) {
		
		/*
		System.out.printf("%s은 은행계좌를 가지고있다. 계좌에 "
				+ "%d원이 예금되어 있는데 오늘 %d원을 송금받아서 잔고가"
				+ " %,d원이 되었다. 특별이자율은 %.1f이 적용되어 내일 이자"
				+ " %.1f원을 받는다.\n" ,"유재석",1000,325,1325,0.3,397.5 );
		*/
		
		System.out.println(" ");
		System.out.printf("%s : %s\n", "예금자","유재석");
		System.out.printf("%s : %,d원\n", "송금되기전 잔금",1000);
		System.out.printf("%s : %d원\n", "송금된 금액",325);
		System.out.printf("%s : %,d원\n", "송금이후 잔금",1325);
		System.out.printf("%s : %.1f원\n", "내일 입금될 이자",397.5);
	}

}
