package DungeonStriker;


import java.io.FileInputStream;

import javazoom.jl.player.*;


public class BGM extends Thread 
{
	public void play()	//������� �ϱ����� �÷��̸޼ҵ� ����
	{
		try
		{
			FileInputStream file = new FileInputStream("C:\\Users\\June\\Music\\HYP_SKY_CASTLE.mp3"); //�ϵ��ũ�� �����ϴ� ���Ϸκ��� ����Ʈ������ �Է� �޴� Ŭ���� FileInputStream //���ϰ�θ� ��������
			Player music = new Player(file); //jlayer ���� �����ϴ� Player Ŭ������ ���
			music.play();	//���ǽ���
		}
		catch(Exception e)
		{
			
		}
		
		
	}
	
	public void run()	//�����带 ���ؼ� ������ �����
	{
		while(true)	//��������� ����ϱ⶧���� �׻� �ݺ�
		{
		play();	//�÷��̸޼ҵ� 
		}
	}
	


}

