package DungeonStriker;

import java.util.Scanner;
import java.util.Random;

public class Shop {
	
	String Unit_Name;
	
	boolean Shop_flag = true;	//���� �ݺ����� �÷���
	boolean Buy_Potion_flag = true;	//���Ǳ��� �ݺ����� �÷���
	boolean Sell_Potion_flag = true;	//�����Ǹ� �ݺ����� �÷���
	boolean Buy_Unit_flag = true;	//���ֻ̱� �ݺ����� �÷���
	boolean first_Buyunit_flag = true;	//ó�����۽� ����̱� �÷���

	int Buy_No;	//���౸�� ����
	int Sell_No;	//�����Ǹ� ����
	int Unit_per;	//���ֻ̱�� ������, ����� ��������
	int Unit_Att_per;	//���ֻ̱�� �Ӽ� ��������
	static int Unit_No=0;	//�������̰� ����Ʈ�� �����ϱ����� �뵵�λ��
	int first_Buyunit=0;	//���� ����̱� 3�� ī��Ʈ��� 
	
	
	int Shop_Select;	//������� ����
	int Potion_Select;	//������������
	int BuyUnit_Select;	//���ֻ̱� ����
	
	
	
	Scanner sc = new Scanner(System.in);
	Random rd = new Random();
	
	
	
	public void Shop_List()
	{
		System.out.println("--------------�޴��� ������ �ּ���--------------");
		System.out.println("1.���౸��    2.�����Ǹ�   3.���ֻ̱�    4.������");
		System.out.println("----------------------------------------------");
	}
	
	public void Buy_Potion_List()
	{
		System.out.println("---------------���� ������Ȳ-------------");
		System.out.println("���� ���� : "+User.Potion_small);
		System.out.println("���� ���� : "+User.Potion_medium);
		System.out.println("���� ���� : "+User.Potion_big);
		System.out.println("---------�����Ͻ� ������ ������ �ּ���----------");
		System.out.println("1.��������  2.�������� 3.��������  4.������");
		System.out.println("----------------------------------------");
	}
	
	public void Sell_Potion_List()
	{
		System.out.println("---------------------���� ������Ȳ-------------------");
		System.out.println("���� ���� : "+User.Potion_small);
		System.out.println("���� ���� : "+User.Potion_medium);
		System.out.println("���� ���� : "+User.Potion_big);
		System.out.println("-------------�Ǹ��Ͻ� ������ ������ �ּ���-------------");
		System.out.println("1.��������       2.��������       3.��������       4.������");
		System.out.println("----------------------------------------------------");
	}
	
	public void Buy_Unit_List()
	{
		System.out.println("-----�޴��� ������ �ּ���-----");
		System.out.println("1.�̱�(1ȸ 300��)   2.������");
		System.out.println("------------------------");
	}
	
	public void Buy_Potion_s()
	{
		System.out.println("----��������(10��)----");
		System.out.println("������ : "+User.Money);
		System.out.println("���� ������ �Է��ϼ���.");
		Buy_No = sc.nextInt();
		
		if(User.Money > Buy_No*10) //�����ݾ��� ������� ��������
		{
			User.Potion_small += Buy_No;	//�Է��� ������ŭ ��������
			User.Money -= Buy_No*10;	//�Է��� ������ŭ�� �ݾ� ����
			
			
			System.out.println("-------���� �Ϸ�------");
			System.out.println("���� �ݾ� : "+Buy_No*10);	//���űݾ� �����ֱ�
			System.out.println("-----���� ������Ȳ-----");	//���౸�ſϷ� �� �������� �����ֱ�
			System.out.println("���� ���� : "+User.Potion_small);	
			System.out.println("���� ���� : "+User.Potion_medium);
			System.out.println("���� ���� : "+User.Potion_big);
		}
		else	//�����ݾ��� ������� ���Ž���
			System.out.println("�ݾ��� �����մϴ�.");
			System.out.println("������ : "+User.Money);
	}
	
	public void Buy_Potion_m()
	{
		System.out.println("----��������(50��)----");
		System.out.println("������ : "+User.Money);
		System.out.println("���� ������ �Է��ϼ���.");
		Buy_No = sc.nextInt();
		
		if(User.Money > Buy_No*50)
		{
			User.Potion_medium += Buy_No;
			User.Money -= Buy_No*50;
			System.out.println("-------���� �Ϸ�------");
			System.out.println("���� �ݾ� : "+Buy_No*50);
			System.out.println("-----���� ������Ȳ-----");
			System.out.println("���� ���� : "+User.Potion_small);
			System.out.println("���� ���� : "+User.Potion_medium);
			System.out.println("���� ���� : "+User.Potion_big);
		}
		else
			System.out.println("�ݾ��� �����մϴ�.");
			System.out.println("������ : "+User.Money);
	}
	
	public void Buy_Potion_b()
	{
		System.out.println("----��������(100��)----");
		System.out.println("������ : "+User.Money);
		System.out.println("���� ������ �Է��ϼ���.");
		Buy_No = sc.nextInt();
		
		if(User.Money > Buy_No*100)
		{
			User.Potion_big += Buy_No;
			User.Money -= Buy_No*100;

			System.out.println("-------���� �Ϸ�------");
			System.out.println("���� �ݾ� : "+Buy_No*100);
			System.out.println("-----���� ������Ȳ-----");
			System.out.println("���� ���� : "+User.Potion_small);
			System.out.println("���� ���� : "+User.Potion_medium);
			System.out.println("���� ���� : "+User.Potion_big);
		}
		else
			System.out.println("�ݾ��� �����մϴ�.");
			System.out.println("������ : "+User.Money);
	}
	
	public void Sell_Potion_s()
	{
		System.out.println("----��������(8��)----");	
		System.out.println("���� ���� : "+User.Potion_small);	//�������� �������� �����ֱ�
		System.out.println("�Ǹ� ������ �Է��ϼ���.");
		Sell_No = sc.nextInt();	//�ǸŰ����Է�
		
		if(User.Potion_small >= Sell_No)	//�ǸŰ����� ������������ ������ �Ǹ�����
		{
			User.Potion_small -= Sell_No;	//�Ǹż�����ŭ ���ళ�� ����
			User.Money += Sell_No*8;		//�Է��Ѽ�����ŭ �Ǹŵ� �ݾ� �ջ�
			
			System.out.println("-------�Ǹ� �Ϸ�------");
			System.out.println("�Ǹ� �ݾ� : "+Sell_No*8);
			System.out.println("-----���� ������Ȳ-----");	//�Ǹ��ϰ� ���� ���ళ�� �����ֱ�
			System.out.println("���� ���� : "+User.Potion_small);
			System.out.println("���� ���� : "+User.Potion_medium);
			System.out.println("���� ���� : "+User.Potion_big);
			System.out.println("������ : "+User.Money);
		}
		else
			System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
			
	}
	
	public void Sell_Potion_m()
	{
		System.out.println("----��������(40��)----");
		System.out.println("���� ���� : "+User.Potion_medium);
		System.out.println("�Ǹ� ������ �Է��ϼ���.");
		Sell_No = sc.nextInt();
		
		if(User.Potion_medium >= Sell_No)
		{
			User.Potion_medium -= Sell_No;
			User.Money += Sell_No*40;
		
			System.out.println("--------�Ǹ� �Ϸ�--------");
			System.out.println("�Ǹ� �ݾ� : "+Sell_No*40);
			System.out.println("-------���� ������Ȳ------");
			System.out.println("���� ���� : "+User.Potion_small);
			System.out.println("���� ���� : "+User.Potion_medium);
			System.out.println("���� ���� : "+User.Potion_big);
			System.out.println("������ : "+User.Money);
		}
		else
			System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
	}
	
	public void Sell_Potion_b()
	{
		System.out.println("----��������(80��)----");
		System.out.println("���� ���� : "+User.Potion_big);
		System.out.println("�Ǹ� ������ �Է��ϼ���.");
		Sell_No = sc.nextInt();
		
		if(User.Potion_big >= Sell_No)
		{
			User.Potion_big -= Sell_No;
			User.Money += Sell_No*80;
		
			System.out.println("-------�Ǹ� �Ϸ�------");
			System.out.println("�Ǹ� �ݾ� : "+Sell_No*80);
			System.out.println("-----���� ������Ȳ-----");
			System.out.println("���� ���� : "+User.Potion_small);
			System.out.println("���� ���� : "+User.Potion_medium);
			System.out.println("���� ���� : "+User.Potion_big);
			System.out.println("������ : "+User.Money);
		}
		else
			System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
	}
	
	public void Buy_Unit1()
	{
		
		System.out.println("------�̴� ��------");	//���ֻ̱� ����
		//0 1 2 3 4 5 6 7 8 9
		
		Unit_per = rd.nextInt(2);	//������ Ÿ�� ������ ������� �����ϱ����� 0,1 ���� 0�� ������ 1�� �����
		Unit_Att_per = rd.nextInt(4);	//������ �Ӽ��� �����ϱ����� ����  0Ǯ 1�� 2�� 3��
		
		if(Unit_per == 0 && Unit_Att_per == 0)	//�������� Ǯ�Ӽ� �������ڰ� ���ý�
		{
			System.out.println("Ǯ�Ӽ� ������ ����!!");
			System.out.println("������ �̸��� �Է����ּ���.");
			Unit_Name = sc.next();			
			Unit.UnitList[Unit_No] = new Warrior(Unit_Name, 100, 12, 1, 1, Unit_Att_per,0);	//���ָ���Ʈ�� �����ü�� �Է�
			
			System.out.println("----------���� ���� ���----------");
		
			for(int a=0; a<Unit_No+1; a++)	//������ �̰� �������ָ���� ���
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		���� : "+ Unit.UnitList[a].Level );
			}
		
			
			Unit_No += 1;	//������ �̰� 1�������ϰ��Ͽ� ���ָ���Ʈ �迭�� �ڸ��� ����
		}
		
		else if(Unit_per == 0 && Unit_Att_per == 1) //������ ��
		{
			System.out.println("���Ӽ� ������ ����!!");
			System.out.println("������ �̸��� �Է����ּ���.");
			Unit_Name = sc.next();			
			Unit.UnitList[Unit_No] = new Warrior(Unit_Name, 100, 12, 1, 1, Unit_Att_per,0);
			
			System.out.println("----------���� ���� ���----------");
			
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		���� : "+ Unit.UnitList[a].Level );
			}
		
			
			Unit_No += 1;
		}
		
		else if(Unit_per == 0 && Unit_Att_per == 2) //������ ��
		{
			System.out.println("���Ӽ� ������ ����!!");
			System.out.println("������ �̸��� �Է����ּ���.");
			Unit_Name = sc.next();			
			Unit.UnitList[Unit_No] = new Warrior(Unit_Name, 100, 12, 1, 1, Unit_Att_per,0);
			
			System.out.println("----------���� ���� ���----------");
		
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		���� : "+ Unit.UnitList[a].Level );
			}
		
			
			Unit_No += 1;
		}

		else if(Unit_per == 0 && Unit_Att_per == 3) //������ ��
		{
			System.out.println("�ҼӼ� ������ ����!!");
			System.out.println("������ �̸��� �Է����ּ���.");
			Unit_Name = sc.next();			
			Unit.UnitList[Unit_No] = new Warrior(Unit_Name, 100, 12, 1, 1, Unit_Att_per,0);
			
			System.out.println("----------���� ���� ���----------");
		
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		���� : "+ Unit.UnitList[a].Level );
			}
			
			
			Unit_No += 1;
		}
		
		else if(Unit_per == 1 && Unit_Att_per ==0) //����� Ǯ
		{
			System.out.println("Ǯ�Ӽ� ����� ����!!");
			System.out.println("������ �̸��� �Է����ּ���.");
			Unit_Name = sc.next();
			Unit.UnitList[Unit_No] = new Defender(Unit_Name, 120, 10, 1, 1, Unit_Att_per, 0);
			
			System.out.println("----------���� ���� ���----------");
		
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		���� : "+ Unit.UnitList[a].Level );
			}
			
			
			Unit_No += 1;
		}

		else if(Unit_per == 1 && Unit_Att_per ==1) //����� ��
		{
			System.out.println("���Ӽ� ����� ����!!");
			System.out.println("������ �̸��� �Է����ּ���.");
			Unit_Name = sc.next();
			Unit.UnitList[Unit_No] = new Defender(Unit_Name, 120, 10, 1, 1, Unit_Att_per, 0);
			
			System.out.println("----------���� ���� ���----------");
		
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		���� : "+ Unit.UnitList[a].Level );
			}
			
			
			Unit_No += 1;
		}
		
		else if(Unit_per == 1 && Unit_Att_per ==2)	//����� ��
		{
			System.out.println("���Ӽ� ����� ����!!");
			System.out.println("������ �̸��� �Է����ּ���.");
			Unit_Name = sc.next();
			Unit.UnitList[Unit_No] = new Defender(Unit_Name, 120, 10, 1, 1, Unit_Att_per, 0);
			
			System.out.println("----------���� ���� ���----------");
	
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		���� : "+ Unit.UnitList[a].Level );
			}
			
			
			
			Unit_No += 1;
		}

		else if(Unit_per == 1 && Unit_Att_per ==3)	//����� ��
		{
			System.out.println("�ҼӼ� ����� ����!!");
			System.out.println("������ �̸��� �Է����ּ���.");
			Unit_Name = sc.next();
			Unit.UnitList[Unit_No] = new Defender(Unit_Name, 120, 10, 1, 1, Unit_Att_per, 0);
			
			System.out.println("----------���� ���� ���----------");
	
			for(int a=0; a<Unit_No+1; a++)
			{
				System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		���� : "+ Unit.UnitList[a].Level );
			}
			
			
			
			Unit_No += 1;
		}
	}
	
	
	
	
	
	public void Shopping()
	{
		Shop_flag = true;
		while(Shop_flag)
		{
			Shop_List();				
			Shop_Select = sc.nextInt();
	
			switch(Shop_Select)
			{
			case 1:
				Buy_Potion_flag = true;
				while(Buy_Potion_flag)
				{
					Buy_Potion_List();
					Potion_Select = sc.nextInt();
		
		
					switch(Potion_Select)
					{
					case 1:
						Buy_Potion_s();
						break;
			
					case 2:
						Buy_Potion_m();
						break;
			
					case 3:
						Buy_Potion_b();
						break;
				
					case 4:
						Buy_Potion_flag = false;
					}
				}
				break;
		
			case 2:
				Sell_Potion_flag = true;
				while(Sell_Potion_flag)
				{
					Sell_Potion_List();
					Potion_Select = sc.nextInt();
		
		
					switch(Potion_Select)
					{
					case 1:
						Sell_Potion_s();
						break;
			
					case 2:
						Sell_Potion_m();
						break;
			
					case 3:
						Sell_Potion_b();
						break;
				
					case 4:
						Sell_Potion_flag = false;
					}
				}
				break;
				
			
			case 3:
				Buy_Unit_flag = true;
				while(Buy_Unit_flag)
				{
					Buy_Unit_List();
					BuyUnit_Select = sc.nextInt();
		
					switch(BuyUnit_Select)
					{
					case 1:
						
						if(first_Buyunit_flag)
						{
							Buy_Unit1();
							first_Buyunit+=1;
							System.out.println("����̱� : " + first_Buyunit +"/3");
							if(first_Buyunit==3)
							{
								System.out.println("����̱� ����. �������� �̵��Ͽ� �ּ���.");
								first_Buyunit_flag = false;
							}
						}
						else if(User.Money>=300)
						{
							Buy_Unit1();
							User.Money -= 300;
							System.out.println("������ : "+User.Money);
						}
						else
						{
							System.out.println("�ݾ��� �����մϴ�.");
						}
						break;
			
				
					case 2:
						Buy_Unit_flag = false;
						
					}
				}
				break;
		
			
			case 4:
				Shop_flag = false;
				break;
				
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
		
				

	}
	
	}
	
	}
	
	
}
