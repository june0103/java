package DungeonStriker;

public class Loading implements Runnable {
	
	public void run()
	{
		try
		{
			String s ="Loading¢¹¢º¢¹¢º¢¹¢º¢¹¢º¢¹¢º¢¹¢º¢¹¢º¢¹¢º¢¹¢º¢¹¢º¢¹¢º¢¹¢º";
			for(int i=0; i<s.length(); i++)
			{
				System.out.print(s.charAt(i));
				Thread.sleep(100);
			}
			System.out.println();
			System.out.println("--------------------------------------------------------------");
			
		}
		catch(Exception e)
		{
			
		}
	}

}
