package DungeonStriker;

import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Dungeon_BGM  implements Runnable {	//던전입장시 메인BGM이 일시정지되고 던전배경음을 재생
	
	boolean stop_bgm = false;
	
	public void play() 
	{
		try
		{
			FileInputStream file = new FileInputStream("C:\\Users\\June\\Music\\Rafael_Krux__Epic_Boss_Battle.mp3");
			Player music = new Player(file);
			
			music.play();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void run()
	{
		while(!stop_bgm)
		{
			play();
		}
	}
	
	public void stop()
	{
		stop_bgm = true;
	}
	


}

