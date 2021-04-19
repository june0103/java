package DungeonStriker;

public class Clear extends Thread { //던전클리어시 나타나는 문구쓰레드
	public void run()
	{
		try
		{
			String s1="     ______         __              ________         ______         _______  ";
			String s2="    /              /  |            /        |       /       |      /        |";
			String s3="   /$$$$$$  |      $$ |            $$$$$$$$/       /$$$$$$  |      $$$$$$$  |";
			String s4="   $$ |  $$        $$ |            $$ |__          $$ |__$$ |      $$ |__$$ |";
			String s5="   $$ |            $$ |            $$    |         $$    $$ |      $$    $$< ";
			String s6="   $$ |   __       $$ |            $$$$$/          $$$$$$$$ |      $$$$$$$  |";
			String s7="   $$  __/  |      $$ |_____       $$ |_____       $$ |  $$ |      $$ |  $$ |";
			String s8="   $$    $$/       $$       |      $$       |      $$ |  $$ |      $$ |  $$ |";
			String s9="    $$$$$$/        $$$$$$$$/       $$$$$$$$/       $$/   $$/       $$/   $$/ ";
			
			String s_[][]  ={{s1},
							{s2},
							{s3},
							{s4},
							{s5},
							{s6},
							{s7},
							{s8},
							{s9}};
			
			for(int i=0; i<9; i++)	//0.3초마다 s1부터 s9출력
		    {
		    	System.out.println(s_[i][0]);
		    	Thread.sleep(300); 
		    }
			
			System.out.println("Boss던전을 클리어하셨습니다!!");
			System.out.println("다음 업데이트에 새로운 보스가 등장합니다!!");
			Thread.sleep(1000);
			
		}
		catch(Exception e)
		{
			
		}
	}
}
