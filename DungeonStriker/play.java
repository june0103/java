package DungeonStriker;

import java.util.Scanner;
import java.util.Random;
																//Attacker(String Unit_name, int Unit_HP, int Unit_Atk, int Unit_Grad, int Unit_Level, int Unit_Att)
public class play {												//Monster(String name, int HP, int Atk, int Att)

	public static void Unit_Select_Battle()	//������Ƽ �����޼ҵ�
	{
		Scanner sc = new Scanner(System.in);
	
		 
		System.out.println("���� �����ϰ��ִ� �����Դϴ�.");
		
		System.out.println("----------���� ���� ���----------");
	
		for(int a=0; a<Shop.Unit_No; a++)	//�������� �ݺ���������Ͽ� ���
		{
			System.out.println(a+1 + "." +Unit.UnitList[a].name +" ���� : "+ Unit.UnitList[a].Level );
		}
		System.out.println("---------------------------------");
		
		System.out.println("������ ���� �� ������ �������ּ���!! (3����)");
		System.out.println("---------------------------------");
		System.out.println("ù��° ������ �������ּ���!!");
		int choice1 = sc.nextInt();

		Unit.UnitList_battle[0] = Unit.UnitList[choice1-1];	//�������ָ�Ͽ��� ������ ������ ��Ʋ���ָ���Ʈ�� �Է�
		for(int a=0; a<1; a++)
		{
			System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level );
		}
		System.out.println("---------------------------------");
		System.out.println("�ι�° ������ �������ּ���!!");
		int choice2 = sc.nextInt();

		Unit.UnitList_battle[1] = Unit.UnitList[choice2-1];	//�������ָ�Ͽ��� ������ ������ ��Ʋ���ָ���Ʈ�� �Է�
		for(int a=0; a<2; a++)
		{
			System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level );
		}
		System.out.println("---------------------------------");
		System.out.println("����° ������ �������ּ���!!");
		int choice3 = sc.nextInt();

		Unit.UnitList_battle[2] = Unit.UnitList[choice3-1];
		for(int a=0; a<3; a++)
		{
			System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level );
		}
		System.out.println("---------------------------------");
	}


	public static void main(String []args) throws InterruptedException //���ͷ�Ʈ ���� �����ϰ��ִ� �����尡 �ϰ��ִ� ���� ���߰� �ٸ����� �϶�� �˷��ٶ� ���
	 {																	//sleep, join�� ����� �� Thread�� ��� ���·� ���ٰ� ����� �� �Ҷ� �߻��ϴ� ����
		 Scanner sc = new Scanner(System.in);
		 Random random = new Random();
		 
		 char Play;		//�����ϱ� 

		 int Menu_Select;	//���θ޴� ����
		 int Map_Select;	//�� ����
		 int Map_G_Select;	//�� ������������
		 int Map_L_Select;	//�� ������������
		 int Map_W_Select;	//�� ������������
		 int Map_F_Select;	//�� ������������
		 
		 String Player_name;	//��ĳ�ʷ� �����̸� �Է�
		 
		 Menu menu = new Menu();	//�޴�Ŭ���� ��üȭ
		 Map map = new Map();		//��Ŭ���� ��üȭ
		 Shop shop = new Shop();	//����Ŭ���� ��üȭ
		 BGM bgm = new BGM();		//���� BGM������ ��üȭ
		 Timer timer = new Timer();	//Ÿ�̸Ӿ����� ��üȭ
		 Main_string ms = new Main_string();//���Ӽ��� ������ ��üȭ
		 
		 //timer.start();	//Ÿ�̸Ӿ����� ����
		// bgm.start();	//���� BGM������ ����
		 //Thread.sleep(1000);
		// ms.start();	//���Ӽ��� ������ ����

//------------------------------------------------------------------------------------------------------------------------	
		try {
			ms.join();	//���Ӽ������� ����ɶ����� ���
			}
		catch(InterruptedException e)
		{}
		
		Runnable Loading = new Loading();	//�ε� Runnable
		Thread Loading_s = new Thread(Loading);		 
		Loading_s.start();							//�ε�����
		
		try
		{
			Loading_s.join();
		}
		catch(InterruptedException e)
		{
			
		}
		Thread.sleep(500);
		System.out.println("Press Enter key to continue...");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {} 
	        
	    Thread.sleep(1000);
	    System.out.println("--------------------------------------------------------------");
	    
	    String s1 = "�г����� �������ּ���.\n";
	    for(int i=0; i<s1.length(); i++)
	    {
	    	System.out.print(s1.charAt(i));
	    	Thread.sleep(100);
	    }
		
		Player_name = sc.next();
		
		User User = new User(Player_name, 0);	//����Ŭ������ �����ڸ� ���Ͽ� �̸��� �Է¹ް� ����, �ʱ��� 0��
		Thread.sleep(1000);
		
		
		String s2 = DungeonStriker.User.name + "�� ȯ���մϴ�.\n";
	    for(int i=0; i<s2.length(); i++)
	    {
	    	System.out.print(s2.charAt(i));
	    	Thread.sleep(100);
	    }
	    
		Thread.sleep(1000);
		System.out.println("--------------------------------------------------------------");
		String s3 = "������ �����Ͻ÷��� ������ �ʿ��մϴ�!!\n\n";
		String s4 = "���ֻ̱�� 1ȸ�� 300���Դϴ�.\n\n";
		String s5 = "������!! ó���̽ô� 3���� �����ȸ�� �帱����!!\n\n";
		String s6 = "���� - ���ֻ̱�� �̵��Ͽ� ������ �Բ��� ������ �̾� �ּ���!!\n";
		for(int i=0; i<s3.length(); i++)	//�ѱ��ھ� ���
	    {
	    	System.out.print(s3.charAt(i));
	    	Thread.sleep(100);
	    }
		for(int i=0; i<s4.length(); i++)	//�ѱ��ھ� ���
	    {
	    	System.out.print(s4.charAt(i));
	    	Thread.sleep(100);
	    }	
		for(int i=0; i<s5.length(); i++)	//�ѱ��ھ� ���
	    {
	    	System.out.print(s5.charAt(i));
	    	Thread.sleep(100);
	    }
		for(int i=0; i<s6.length(); i++)	//�ѱ��ھ� ���
	    {
	    	System.out.print(s6.charAt(i));
	    	Thread.sleep(100);
	    }
		
		System.out.println("--------------------------------------------------------------");
		Thread.sleep(2000);
		
		while(true)	//�������� �������� �׻�ݺ� 
		{
			menu.Menu_Select();	//���θ޴�����
			
			Menu_Select = sc.nextInt();
					
			switch(Menu_Select) //���θ޴� ���� ���ǹ�
			{
			case 1:	//��������
				
				if(Unit.UnitList[0]==null) //���� ������ 0���϶�
				{
					System.out.println("���� �����ϰ��ִ� ������ �����ϴ�.");
					System.out.println("������ �̾� ������ ������ ���ּ���.");
				}
				
				else	//������ �����ϰ�������
				{
					Unit_Select_Battle();	//���� ��Ƽ���� �޼ҵ����
					
					map.Map_Select();		//�ʼ���
					Map_Select = sc.nextInt();
				
					switch(Map_Select) //�ʼ��� ���ǹ� ����
					{
					case 1:	//Ǯ��������
						
						System.out.println("Ǯ���� ���� ��.....");
		                  map.Map_G();	//Ǯ���� ������������
		                  Map_G_Select = sc.nextInt(); 
		               
		                  switch(Map_G_Select) //Ǯ���� �������� ���ǹ� ����
		                  {
		                  case 1:	//Ǯ-01 ���� ����
		                	  bgm.suspend();	//����� �Ͻ����� . suspend - ������� �Ͻ����� ���°� �ȴ�
		                	  Unit.Battle_G1(); //Ǯ - 01 ���� ��Ʋ �޼ҵ� ����
		                	  bgm.resume();		//����� �ٽ����
		                	  break;
						case 2:
							  bgm.suspend();
		                	  Unit.Battle_G2();
		                	  bgm.resume();
		                	  break;
		                  case 3:
		                	  bgm.suspend();
		                	  Unit.Battle_G3();
		                	  bgm.resume();
		                	  break;
		                  case 4:
		                	  bgm.suspend();
		                	  Unit.Battle_G4();	
		                	  bgm.resume();
		                	  break;
		                  case 5:
		                	  bgm.suspend();
		                	  Unit.Battle_G5();
		                	  bgm.resume();
		                	  break;
		                  case 6:
		                	  bgm.suspend();
		                	  Unit.Battle_G6();
		                	  bgm.resume();
		                	  break;
		                  case 7:
		                	  bgm.suspend();
		                	  Unit.Battle_G7();
		                	  bgm.resume();
		                	  break;
		                  case 8:
		                	  bgm.suspend();
		                	  Unit.Battle_G8();
		                	  bgm.resume();
		                	  break;
		                  case 9:
		                	  bgm.suspend();
		                	  Unit.Battle_G9();
		                	  bgm.resume();
		                	  break;
		                  case 10:
		                	  bgm.suspend();
		                	  Unit.Battle_G10();
		                	  bgm.resume();
		                	  break;
		                  default : //�׿ܰ��� �Է�����
							  System.out.println("�߸��� �Է��Դϴ�.");
							  break;
		                  }
					break; //Ǯ���� ����
									
					case 2: //������ ����
						
						System.out.println("������ ���� ��.....");
		                  map.Map_L();
		                  Map_L_Select = sc.nextInt();
		               
		                  switch(Map_L_Select)
		                  {		
		                  case 1:	//��-01 ���� ����
		                	  bgm.suspend();
		                	  Unit.Battle_L1();
		                	  bgm.resume();
		                	  break;
		                  case 2:
		                	  bgm.suspend();
		                	  Unit.Battle_L2();
		                	  bgm.resume();
		                	  break;
		                  case 3:
		                	  bgm.suspend();
		                	  Unit.Battle_L3();
		                	  bgm.resume();
		                	  break;
		                  case 4:
		                	  bgm.suspend();
		                	  Unit.Battle_L4();	
		                	  bgm.resume();
		                	  break;
		                  case 5:
		                	  bgm.suspend();
		                	  Unit.Battle_L5();
		                	  bgm.resume();
		                	  break;
		                  case 6:
		                	  bgm.suspend();
		                	  Unit.Battle_L6();
		                	  bgm.resume();
		                	  break;
		                  case 7:
		                	  bgm.suspend();
		                	  Unit.Battle_L7();
		                	  bgm.resume();
		                	  break;
		                  case 8:
		                	  bgm.suspend();
		                	  Unit.Battle_L8();
		                	  bgm.resume();
		                	  break;
		                  case 9:
		                	  bgm.suspend();
		                	  Unit.Battle_L9();
		                	  bgm.resume();
		                	  break;
		                  case 10:
		                	  bgm.suspend();
		                	  Unit.Battle_L10();
		                	  bgm.resume();
		                	  break;
		                  default : 
							  System.out.println("�߸��� �Է��Դϴ�.");
							  break;
		                  }		
					break;				
									
					case 3: //������ ����
						System.out.println("������ ���� ��.....");
		                  map.Map_W();
		                  Map_W_Select = sc.nextInt();
		               
		                  switch(Map_W_Select)
		                  {		
		                  case 1:	//��-01 ���� ����
		                	  bgm.suspend();
		                	  Unit.Battle_W1();
		                	  bgm.resume();
		                	  break;
		                  case 2:
		                	  bgm.suspend();
		                	  Unit.Battle_W2();
		                	  bgm.resume();
		                	  break;
		                  case 3:
		                	  bgm.suspend();
		                	  Unit.Battle_W3();
		                	  bgm.resume();
		                	  break;
		                  case 4:
		                	  bgm.suspend();
		                	  Unit.Battle_W4();	
		                	  bgm.resume();
		                	  break;
		                  case 5:
		                	  bgm.suspend();
		                	  Unit.Battle_W5();
		                	  bgm.resume();
		                	  break;
		                  case 6:
		                	  bgm.suspend();
		                	  Unit.Battle_W6();
		                	  bgm.resume();
		                	  break;
		                  case 7:
		                	  bgm.suspend();
		                	  Unit.Battle_W7();
		                	  bgm.resume();
		                	  break;
		                  case 8:
		                	  bgm.suspend();
		                	  Unit.Battle_W8();
		                	  bgm.resume();
		                	  break;
		                  case 9:
		                	  bgm.suspend();
		                	  Unit.Battle_W9();
		                	  bgm.resume();
		                	  break;
		                  case 10:
		                	  bgm.suspend();
		                	  Unit.Battle_W10();
		                	  bgm.resume();
		                	  break;
		                  default : 
							  System.out.println("�߸��� �Է��Դϴ�.");
							  break;
		                  }		
					break;				
					
						
					case 4: //�Ҵ��� ����
						System.out.println("�Ҵ��� ���� ��.....");
		                  map.Map_F();
		                  Map_F_Select = sc.nextInt();
		               
		                  switch(Map_F_Select)
		                  {		
		                  case 1:	//��-01 ���� ����
		                	  bgm.suspend();
		                	  Unit.Battle_F1();
		                	  bgm.resume();
		                	  break;
		                  case 2:
		                	  bgm.suspend();
		                	  Unit.Battle_F2();
		                	  bgm.resume();
		                	  break;
		                  case 3:
		                	  bgm.suspend();
		                	  Unit.Battle_F3();
		                	  bgm.resume();
		                	  break;
		                  case 4:
		                	  bgm.suspend();
		                	  Unit.Battle_F4();	
		                	  bgm.resume();
		                	  break;
		                  case 5:
		                	  bgm.suspend();
		                	  Unit.Battle_F5();
		                	  bgm.resume();
		                	  break;
		                  case 6:
		                	  bgm.suspend();
		                	  Unit.Battle_F6();
		                	  bgm.resume();
		                	  break;
		                  case 7:
		                	  bgm.suspend();
		                	  Unit.Battle_F7();
		                	  bgm.resume();
		                	  break;
		                  case 8:
		                	  bgm.suspend();
		                	  Unit.Battle_F8();
		                	  bgm.resume();
		                	  break;
		                  case 9:
		                	  bgm.suspend();
		                	  Unit.Battle_F9();
		                	  bgm.resume();
		                	  break;
		                  case 10:
		                	  bgm.suspend();
		                	  Unit.Battle_F10();
		                	  bgm.resume();
		                	  break;
		                  default : 
							  System.out.println("�߸��� �Է��Դϴ�.");
							  break;
		                  }		
					break;		
					
					case 5 : 
						bgm.suspend();
						Unit.Battle_Boss();
						bgm.resume();
						break;
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
										
					}
								
			} 
				break; // �������� ����
			case 2:	//��������

				shop.Shopping();	//�����޼ҵ� ����
				
				break; //���� ����������
				
			case 3: //�κ��丮
				
				User.bag();
				
				break; //�κ��丮 ����������
			
			case 4: // ���� ��ް�ȭ
				Unit.Grad_up();
				
				break;
			case 5: //��������
				System.out.println("������ �����Ͻðڽ��ϱ�? Y/N");
				Play=sc.next().charAt(0);
				
				if(Play == 'Y')
				{
					System.out.println("������....");
					Thread.sleep(1000);

					
					
					Thread.sleep(1000);
					System.out.println("����");
					System.exit(0); //���α׷����� _ ��������ϰ��ִ� ���μ�����������
				}
				
				else
					break;
				
			case 1004:	//�׽�Ʈ��
				System.out.println("����� �Ѿ�!! 10000�� ȹ��");
				DungeonStriker.User.Money += 10000;
				System.out.println("�����ݾ� : "+DungeonStriker.User.Money);
				
				break;
				
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				
			}
		
		}
	 }

}
