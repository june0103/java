package DungeonStriker;

public class Timer extends Thread {

	static int time_s = 0; //초
	static int time_m = 0; //분
	static int time_h = 0; //시
	
	public void run()
	{
		while(true)	//항상 반복
		{
			try
			{
				sleep(1000); //쓰레드 1초지연
				time_s += 1; //반복문 동작시 1씩 증가
				
				if(time_s==59)	//분을나타내기위하여 59초가될때
				{
					sleep(1000);	//1초지연 _ 시계상에서 60초로 나오지않고 59 -> 0 -> 1 초로 표현
					time_s = 0;		//초 초기화
					time_m += 1;	//분 1식 증가
					
					if(time_m==60)	//시를 나타내기위하여
					{
						time_m = 0;
						time_h +=1;
						
					}
				}
				
				if(time_m==1&& time_s ==0)	//1시간간격으로 게임이용 경고문구 출력
				{
					System.out.println("게임을 플레이한 지 "+time_m+"시간이 지났습니다.\n과도한 게임 이용은 정상적인 일상생활에 지장을 줄 수 있습니다.\n");
					
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}
}
