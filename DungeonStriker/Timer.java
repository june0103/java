package DungeonStriker;

public class Timer extends Thread {

	static int time_s = 0; //��
	static int time_m = 0; //��
	static int time_h = 0; //��
	
	public void run()
	{
		while(true)	//�׻� �ݺ�
		{
			try
			{
				sleep(1000); //������ 1������
				time_s += 1; //�ݺ��� ���۽� 1�� ����
				
				if(time_s==59)	//������Ÿ�������Ͽ� 59�ʰ��ɶ�
				{
					sleep(1000);	//1������ _ �ð�󿡼� 60�ʷ� �������ʰ� 59 -> 0 -> 1 �ʷ� ǥ��
					time_s = 0;		//�� �ʱ�ȭ
					time_m += 1;	//�� 1�� ����
					
					if(time_m==60)	//�ø� ��Ÿ�������Ͽ�
					{
						time_m = 0;
						time_h +=1;
						
					}
				}
				
				if(time_m==1&& time_s ==0)	//1�ð��������� �����̿� ����� ���
				{
					System.out.println("������ �÷����� �� "+time_m+"�ð��� �������ϴ�.\n������ ���� �̿��� �������� �ϻ��Ȱ�� ������ �� �� �ֽ��ϴ�.\n");
					
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}
}
