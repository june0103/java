package DungeonStriker;


import java.io.FileInputStream;

import javazoom.jl.player.*;


public class BGM extends Thread 
{
	public void play()	//음악재생 하기위한 플레이메소드 생성
	{
		try
		{
			FileInputStream file = new FileInputStream("C:\\Users\\June\\Music\\HYP_SKY_CASTLE.mp3"); //하드디스크상에 존재하는 파일로부터 바이트단위의 입력 받는 클래스 FileInputStream //파일경로를 슽링으로
			Player music = new Player(file); //jlayer 에서 제공하는 Player 클래스를 사용
			music.play();	//음악시작
		}
		catch(Exception e)
		{
			
		}
		
		
	}
	
	public void run()	//쓰레드를 통해서 수행할 내용들
	{
		while(true)	//배경음으로 사용하기때문에 항상 반복
		{
		play();	//플레이메소드 
		}
	}
	


}

