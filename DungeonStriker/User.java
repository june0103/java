package DungeonStriker;

import java.util.Scanner;

public class User {
	
	boolean Bag_flag = true;
	
	static String name;
	
	static int Money;
	static int Potion_small = 0 ;
	static int Potion_medium = 0 ;
	static int Potion_big = 0;
	int Bag_Select;
	
	Scanner sc = new Scanner(System.in);
	
	
	
		public User(String User_name, int User_money) //�̸��� �� �Ķ���͸� ���� ���� ������
		{
			this.name = User_name;
			User.Money = User_money;
		}

		
		void getUserInfo()	//�������������ֱ�
		{
			System.out.println("------------���� ����-------------");
			System.out.println("�г��� : " +User.name);
			System.out.println("�������� : " +Shop.Unit_No);
			System.out.println("������� : " +User.Money);
			System.out.println("���� ���� : "+User.Potion_small);
			System.out.println("���� ���� : "+User.Potion_medium);
			System.out.println("���� ���� : "+User.Potion_big);
			System.out.println("�÷��̽ð� : "+Timer.time_h +"�ð�  "+Timer.time_m+"��  "+Timer.time_s+"��");
			System.out.println("---------------------------------");
			System.out.println("�����÷��� �ƹ�Ű�� �Է����ּ���.");
		}
		
		void bag() throws InterruptedException
		{
			
			Bag_flag = true;
			
			while(Bag_flag)
			{
				Menu.Bag();
			
				Bag_Select = sc.nextInt();
			
			
			
				switch(Bag_Select)
				{
				case 1: //��������
					getUserInfo();
					
					 try		//�ƹ�Ű�� �Է�
				        {
				            System.in.read();
				        }  
				        catch(Exception e)
				        {}  
					break;	//�������� ����
			
				case 2: 	//�������� ����
					boolean Unit_info_flag = true;
					
			Loop1 : while(Unit_info_flag) //������������ �ݺ���
					{
						if(Unit.UnitList[0]==null) //���� ������ 0���϶�
						{
							System.out.println("���� �����ϰ��ִ� ������ �����ϴ�.");
							break Loop1;
						}
						
						else	//������ �������϶� ���������������
						{
							System.out.println("----------���� ���� ���----------");
				
							for(int a=0; a<Shop.Unit_No; a++) //���ֻ̱⿡�� ���� �迭 �ε��������� ������ŭ �������ָ�� ���
							{
								System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		���� : "+ Unit.UnitList[a].Level );
							}
							System.out.println("---------------------------------");
							System.out.println("1. �� ���� ����    2. ������");
							int Unitinfo_menu = sc.nextInt();	
						
							if(Unitinfo_menu ==1)	//������ ����
							{
								System.out.println("���� ���� ��Ͽ��� ���ϴ� ������ ��ȣ�� �Է����ּ���.");
					
								int Unitinfo = sc.nextInt();
								Unit.UnitList[Unitinfo-1].getInfo();	//�Էµ� ������ �迭�ε��� ������ ������ ȣ��
								System.out.println("�����÷��� �ƹ�Ű�� �Է����ּ���.");
								try		//�ƹ�Ű�� �Է�
						        {
						            System.in.read();
						        }  
						        catch(Exception e)
						        {}  
								
							}
							else if(Unitinfo_menu == 2)	//������
							{
								Unit_info_flag = false;	//������������ �ݺ��� �÷��� ����
							}
						
							else //�׿ܰ� �Է½�
								System.out.println("�߸��� �Է��Դϴ�.");
						}
					}
						break; //�������� ����
					
				case 3:
					Bag_flag = false;
					break;
				default :  //�׿ܰ� �Է½�
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
			}
			
		
			
			
			}
		}
		
		
	
		

}
