package DungeonStriker;

public class Clear extends Thread { //����Ŭ����� ��Ÿ���� ����������
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
			
			for(int i=0; i<9; i++)	//0.3�ʸ��� s1���� s9���
		    {
		    	System.out.println(s_[i][0]);
		    	Thread.sleep(300); 
		    }
			
			System.out.println("Boss������ Ŭ�����ϼ̽��ϴ�!!");
			System.out.println("���� ������Ʈ�� ���ο� ������ �����մϴ�!!");
			Thread.sleep(1000);
			
		}
		catch(Exception e)
		{
			
		}
	}
}
