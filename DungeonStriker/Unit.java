package DungeonStriker;

import java.util.Random;
import java.util.Scanner;

class Unit {
	
	String name;
	int HP;
	int Atk;
	int Grad;
	int Level;
	int Att; //Attribute
	int Exp;
	
	Scanner sc = new Scanner(System.in);

	static Unit[] UnitList = new Unit[100];	//����Ŭ���� �迭�� ������ �̰� �����Ű������ ���� ��Ͽ뵵
	static Unit[] UnitList_battle = new Unit[3]; //������Ƽ �迭
	
	public Unit(String name, int HP, int Atk, int Grad, int Level, int Att, int Exp) //�̸�,ü��,���ݷ�,���,����,�Ӽ�,����ġ�� �Ķ����
	{  //������, ����� ������
		this.name = name;	//this �� ����Ŭ������ �ν��Ͻ��� ����Ų��
		this.HP = HP;
		this.Atk = Atk;
		this.Grad = Grad;
		this.Level = Level;
		this.Att = Att;
		this.Exp = Exp;
		
	}
	
	public Unit(String Monster_name, int Monster_HP, int Monster_Atk, int Monster_Att, int Monster_Exp) //�̸�,ü��,���ݷ�,�Ӽ�,����ġ�� �Ķ����
	{   ///���� ������	
		this.name = Monster_name;
		this.HP = Monster_HP;
		this.Atk = Monster_Atk;
		this.Att = Monster_Att;
		this.Exp = Monster_Exp;
	}
	
	
	void incHP(int Point)  //ü������ �޼ҵ� 
	{ 
		this.HP += Point; //point��ŭ ü���� ����
	}
	
	void decHP(int Point) //ü�°��� �޼ҵ�
	{ 
		this.HP -= Point; //point��ŭ ü�� ����
	}
	
	int getHP() //ü��Ȯ�� �޼ҵ�
	{
		return this.HP; //this�� ����Ű�� HP�� ����
	}
	
	int getLevel() //����Ȯ�� �޼ҵ�
	{ 
		return this.Level; 
	}

	void getInfo() //��������Ȯ�� �޼ҵ�
	{
		
		System.out.println("---------------------------------");
		System.out.println("�̸� : " + this.name); 
		if(this.Att ==0) //�Ӽ�Ȯ��
		{
			System.out.println("�Ӽ� : Ǯ");
		}
		else if(this.Att == 1)
		{
			System.out.println("�Ӽ� : ��");
		}
		else if(this.Att == 2)
		{
			System.out.println("�Ӽ� : ��");
		}
		else if(this.Att ==3)
		{
			System.out.println("�Ӽ� : ��");
		}
		System.out.println("��� : " + this.Grad);
		System.out.println("���� : " + this.Level);
		System.out.println("ü�� : " + this.HP);
		System.out.println("���ݷ� : " + this.Atk);
		System.out.println("����ġ : " + this.Exp);
		System.out.println("---------------------------------");
	}
	
	void getMInfo() //���� ����Ȯ�� �޼ҵ�
	{
		System.out.println("���� ����!!");
		System.out.println("-------------------------------------");
		System.out.println("�̸� : " + this.name);
		if(this.Att ==0)
		{
			System.out.println("�Ӽ� : Ǯ");
		}
		else if(this.Att == 1)
		{
			System.out.println("�Ӽ� : ��");
		}
		else if(this.Att == 2)
		{
			System.out.println("�Ӽ� : ��");
		}
		else if(this.Att ==3)
		{
			System.out.println("�Ӽ� : ��");
		}
		System.out.println("ü�� : " + this.HP);
		System.out.println("���ݷ� : " + this.Atk);
		System.out.println("-------------------------------------");
	}

	
	public void attack(Unit Enemy) 		//���� �޼ҵ� (�Է��ڷ��� �Էº���)
	{	
		
		
		String s1 = this.name + "�� ���ݷ� : " + this.Atk+"\n"; //���ݷ� ���
		String s2 = this.name + " -> " + Enemy.name + " ����!!\n";
		Runnable attack_bgm = new Attack_Thread(); //���ݸ޼ҵ����� ����bgm�� �׸� ������ �����ϱ�����  Runnable�� �ν��Ͻ��� Thread�� �����ڷ� ����
		Thread a_bgm = new Thread(attack_bgm);	//Thread ������
		try { //����ó����
			Thread.sleep(500);	//sleep�� �������� �帧�� ��� ���߰� �����Ų��. 1000 = 1��
		} catch (InterruptedException e) {
			
		}
		a_bgm.start();	//attack_bgm ������ ����
		try {
			a_bgm.join(); //join������Ͽ� �ش罺���尡 ����Ǳ���� ��ٷȴٰ� ��������
			}
		catch(InterruptedException e)
		{}
		for(int i=0; i<s1.length(); i++) //���ڿ� s1�ѱ��ھ����
		{
			System.out.print(s1.charAt(i));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			
			}
		}
		for(int i=0; i<s2.length(); i++)
		{
			System.out.print(s2.charAt(i));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			
			}
		}
		if(Enemy instanceof Defender)	//���ݸ޼ҵ��� �Էº��� Enemy�� ������� �ν��Ͻ���
		{
			
			Enemy.decHP(this.Atk-this.Atk/10); 
			System.out.println(Enemy.name + "�� ����� ȿ���� �޴����� 10% ����!!");
		}
		
		else 	//���ݸ޼ҵ��� �Էº��� Enemy�� �Ǵ� ����Ű�� �ν��Ͻ��� ���ݷ¸�ŭ ����
		{
			Enemy.decHP(this.Atk);
		}
		
		
		String s3 = Enemy.name + "�� ���� HP : " + Enemy.getHP()+"\n";
		for(int i=0; i<s3.length(); i++)
		{
			System.out.print(s3.charAt(i));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			
		}
		
		
	}
	
	

	public boolean Live() //�������� �޼ҵ�
	{
		
		if(HP <= 0) { 	//HP�� 0�����̸� ������ ���� �޴´�
			return false;
		}
		
		else {
			return true;
		}
	}
	
	static void Grad_up() throws InterruptedException //��ް�ȭ�޼ҵ�
	{
		
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		
		boolean Grad_up_flag = true;	//��ް�ȭ �ݺ� �÷���
		
		while(Grad_up_flag)
		{
			
			if(Unit.UnitList[0]==null) //���� ������ 0���϶�
			{
				System.out.println("���� �����ϰ��ִ� ������ �����ϴ�.");
				Grad_up_flag = false;
			}
			
			else
			{
				System.out.println("---------------------------------");
				System.out.println("1.��� ��ȭ(5000��)       2.������");
				System.out.println("---------------------------------");
				int Grad_menu = sc.nextInt();
				
				if(Grad_menu ==1) //��ް�ȭ
				{
					if(User.Money>=5000)	//�����ݾ��� 5õ������ ������ ��ް�ȭ����
					{
						System.out.println("----------���� ���� ���----------");
						
						for(int a=0; a<Shop.Unit_No; a++)		//�������ָ�� ��� �ݺ���
						{
							System.out.println(a+1 + ". " +Unit.UnitList[a].name +"		���� : "+ Unit.UnitList[a].Level );
						}
						System.out.println("��� ��ȭ �� ������ �������ּ���. ");
						int Grad_Unit_Select = sc.nextInt();				
						
						System.out.println("��ȭ �� ���� ����");
						Unit.UnitList[Grad_Unit_Select-1].getInfo();	//���ָ���Ʈ���� ������ ������ ����Ȯ�θ޼ҵ� ���
						Thread.sleep(2000);
						
						int plus_Atk = random.nextInt(11);	//����������Ͽ� �ɷ�ġ ��ȭ
						int plus_HP = random.nextInt(101);
						System.out.println("��ȭ ����!!");
						System.out.println("�߰� HP : " + plus_HP);
						System.out.println("�߰� Atk : " + plus_Atk);
						Unit.UnitList[Grad_Unit_Select-1].Grad += 1;	//���ָ���Ʈ���� ������ �ε����� ��� +1
						Unit.UnitList[Grad_Unit_Select-1].HP += plus_HP;	//HP +
						Unit.UnitList[Grad_Unit_Select-1].Atk += plus_Atk;	//Atk +
						
						System.out.println("��ȭ �� �������� ");
						Unit.UnitList[Grad_Unit_Select-1].getInfo();		//�ɷ�ġ��ȭ�� ��������
						User.Money-=5000;
						System.out.println("������ : "+User.Money);
						System.out.println("�����÷��� �ƹ�Ű�� �Է����ּ���.");
						try		//�ƹ�Ű�� �Է�
				        {
				            System.in.read();	//�ܼ�â���� �Է����޴� �Լ� (�ƽ�Ű�ڵ�)
				        }  
				        catch(Exception e)
				        {}  
					}
					else		//�����ݾ��� ������ 
					{
						System.out.println("�ݾ��� �����մϴ�.");
					}
				}// ��ް�ȭ ����
				
				else if(Grad_menu == 2) //������
				{
					Grad_up_flag = false;
				}
				
				else	//�߸����Է�
				{
					System.out.println("�߸��� �Է��Դϴ�.");
				}
			
			}
			
		}
		
		
	}
	
	public static void Battle_G1() throws InterruptedException	//������������ �� ��Ʋ�޼ҵ�
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();	//���� bgm Runnable
		Thread D_bgm = new Thread(Dungeon_BGM);		 
		D_bgm.start();							//����bgm���
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;		//��Ʋ���� �ൿ����
		int Battle_USelect;				//������ ���ּ���
		int Per_Potion;					//����ȹ��Ȯ�� ����
		int Reset_M_HP, Reset_M_Atk;	//���������� ������ ü��, ���ݷ� ���
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;	//���������� ������ ü�¹��
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;	//���������� ������ ���ݷ� ���
		int Use_Select_Potion;	//���ǻ���Ҷ� ���ú���
		int Use_sPo;	//�������� ��밹��
		int Use_mPo;	//�������� ��밹��
		int Use_bPo;	//�������� ��밹��
		char Exit;		//���������� ����
		Menu menu = new Menu();	//�޴�Ŭ���� ��ü���Ͽ� ���
		
		
		System.out.println("Ǯ - 01 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.G1.getMInfo(); //���� G1������ ���� ȣ��
		
		Reset_M_HP = Map.G1.HP;							//���������� ���Ϳ� ������ ü�°� ���ݷ� ���
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G1.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G1.Live()) //���Ϳ� ������ �Ѹ����� ��������� �ݺ�
		{
			
			menu.Battle_Select();	//��Ʋ�޴� ȣ��
			Battle_Select_Action = sc.nextInt();	//��Ʋ���� �ټǼ���
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G1);	//������ ���� G1����
					Thread.sleep(1000);
					 if(!Map.G1.Live()) 	//���� G1�� �׾��ٸ� ����������, ��������� ���� G1�� ���� ����
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾����� �Ʒ����� ����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G1);
						Thread.sleep(1000);
						 if(!Map.G1.Live()) 
						 {
						 break;
						 }
						System.out.println("-------------------------------------");
						Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G1);
						Thread.sleep(1000);
						 if(!Map.G1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G1);
						Thread.sleep(1000);
						 if(!Map.G1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G1);
						Thread.sleep(1000);
						 if(!Map.G1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G1);
						Thread.sleep(1000);
						 if(!Map.G1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G1);
						Thread.sleep(1000);
						 if(!Map.G1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.G1.Atk = Reset_M_Atk;	//�������� �� ����س��� ���Ϳ� ������ ���ݷ� ���󺹱�
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20); //ü������ �޼ҵ带 ����Ͽ� 20��ŭ ȸ��


						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					
					break Loop1; //��������
				}
				
				else
					
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.G1.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G1.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.G1.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300) //������ ����ġ�� ���������ǿ� �����ϸ�
           {
        	   Unit.UnitList_battle[0].Level += 1;								//������
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));	//������������ �ʿ����ġ
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G1.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.G1.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G1.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.G1.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");	//���������� ���ȹ��
           User.Money += Reset_M_HP;		//��ȹ��		
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.G1.HP = Reset_M_HP;	//��������� ���Ϳ� ������ ü�� ���󺹱�
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();	//���� bgm������ ����
	}
	
	public static void Battle_G2() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("Ǯ - 02 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.G2.getMInfo(); //���� ����
		
		Reset_M_HP = Map.G2.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G2.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G2.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
					Thread.sleep(1000);
					 if(!Map.G2.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
						Thread.sleep(1000);
						 if(!Map.G2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
						Thread.sleep(1000);
						 if(!Map.G2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
						Thread.sleep(1000);
						 if(!Map.G2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
						Thread.sleep(1000);
						 if(!Map.G2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
						Thread.sleep(1000);
						 if(!Map.G2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G2);
						Thread.sleep(1000);
						 if(!Map.G2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.G2.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.G2.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G2.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.G2.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G2.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.G2.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G2.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.G2.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.G2.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_G3() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("Ǯ - 03 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.G3.getMInfo(); //���� ����
		
		Reset_M_HP = Map.G3.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G3.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G3.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
					Thread.sleep(1000);
					 if(!Map.G3.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.G3.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.G3.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G3.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.G3.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G3.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.G3.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G3.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.G3.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.G3.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_G4() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("Ǯ - 04 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.G4.getMInfo(); //���� ����
		
		Reset_M_HP = Map.G4.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G4.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G4.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
					Thread.sleep(1000);
					 if(!Map.G4.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
						Thread.sleep(1000);
						 if(!Map.G4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
						Thread.sleep(1000);
						 if(!Map.G4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
						Thread.sleep(1000);
						 if(!Map.G4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
						Thread.sleep(1000);
						 if(!Map.G4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
						Thread.sleep(1000);
						 if(!Map.G4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G4);
						Thread.sleep(1000);
						 if(!Map.G4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.G4.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.G4.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G4.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.G4.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G4.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.G4.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G4.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.G4.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.G4.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_G5() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("Ǯ - 05 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.G5.getMInfo(); //���� ����
		
		Reset_M_HP = Map.G5.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G5.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G5.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
					Thread.sleep(1000);
					 if(!Map.G5.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
						Thread.sleep(1000);
						 if(!Map.G5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
						Thread.sleep(1000);
						 if(!Map.G5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
						Thread.sleep(1000);
						 if(!Map.G5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
						Thread.sleep(1000);
						 if(!Map.G5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
						Thread.sleep(1000);
						 if(!Map.G5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G5);
						Thread.sleep(1000);
						 if(!Map.G5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.G5.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.G5.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G5.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.G5.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G5.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.G5.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G5.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.G5.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.G5.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_G6() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("Ǯ - 06 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.G6.getMInfo(); //���� ����
		
		Reset_M_HP = Map.G6.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G6.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G6.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
					Thread.sleep(1000);
					 if(!Map.G6.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
						Thread.sleep(1000);
						 if(!Map.G6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
						Thread.sleep(1000);
						 if(!Map.G6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
						Thread.sleep(1000);
						 if(!Map.G6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
						Thread.sleep(1000);
						 if(!Map.G6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
						Thread.sleep(1000);
						 if(!Map.G6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G6);
						Thread.sleep(1000);
						 if(!Map.G6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.G6.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.G6.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G6.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.G6.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G6.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.G6.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G6.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.G6.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.G6.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_G7() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("Ǯ - 07 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.G7.getMInfo(); //���� ����
		
		Reset_M_HP = Map.G7.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G7.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G7.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
					Thread.sleep(1000);
					 if(!Map.G7.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
						Thread.sleep(1000);
						 if(!Map.G7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
						Thread.sleep(1000);
						 if(!Map.G7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
						Thread.sleep(1000);
						 if(!Map.G7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
						Thread.sleep(1000);
						 if(!Map.G7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
						Thread.sleep(1000);
						 if(!Map.G7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G7);
						Thread.sleep(1000);
						 if(!Map.G7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.G7.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.G7.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G7.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.G7.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G7.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.G7.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G7.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.G7.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.G7.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_G8() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("Ǯ - 08 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.G8.getMInfo(); //���� ����
		
		Reset_M_HP = Map.G8.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G8.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G8.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
					Thread.sleep(1000);
					 if(!Map.G8.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
						Thread.sleep(1000);
						 if(!Map.G8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
						Thread.sleep(1000);
						 if(!Map.G8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
						Thread.sleep(1000);
						 if(!Map.G8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
						Thread.sleep(1000);
						 if(!Map.G8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
						Thread.sleep(1000);
						 if(!Map.G8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G8);
						Thread.sleep(1000);
						 if(!Map.G8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.G8.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.G8.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G8.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.G8.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G8.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.G8.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G8.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.G8.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.G8.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_G9() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("Ǯ - 09 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.G9.getMInfo(); //���� ����
		
		Reset_M_HP = Map.G9.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G9.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G9.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
					Thread.sleep(1000);
					 if(!Map.G9.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
						Thread.sleep(1000);
						 if(!Map.G9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
						Thread.sleep(1000);
						 if(!Map.G9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
						Thread.sleep(1000);
						 if(!Map.G9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
						Thread.sleep(1000);
						 if(!Map.G9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
						Thread.sleep(1000);
						 if(!Map.G9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G9);
						Thread.sleep(1000);
						 if(!Map.G9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.G9.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.G9.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G9.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.G9.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G9.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.G9.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G9.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.G9.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.G9.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_G10() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("Ǯ - 10 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.G10.getMInfo(); //���� ����
		
		Reset_M_HP = Map.G10.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G10.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G10.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
					Thread.sleep(1000);
					 if(!Map.G10.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
						Thread.sleep(1000);
						 if(!Map.G10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
						Thread.sleep(1000);
						 if(!Map.G10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
						Thread.sleep(1000);
						 if(!Map.G10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
						Thread.sleep(1000);
						 if(!Map.G10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
						Thread.sleep(1000);
						 if(!Map.G10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G10);
						Thread.sleep(1000);
						 if(!Map.G10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.G10.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.G10.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G10.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.G10.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G10.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.G10.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G10.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.G10.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.G10.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_L1() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 01 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.L1.getMInfo(); //���� ����
		
		Reset_M_HP = Map.L1.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L1.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L1.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
					Thread.sleep(1000);
					 if(!Map.L1.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
						Thread.sleep(1000);
						 if(!Map.L1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
						Thread.sleep(1000);
						 if(!Map.L1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
						Thread.sleep(1000);
						 if(!Map.L1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
						Thread.sleep(1000);
						 if(!Map.L1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
						Thread.sleep(1000);
						 if(!Map.L1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L1);
						Thread.sleep(1000);
						 if(!Map.L1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.L1.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.L1.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L1.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.L1.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L1.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.L1.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L1.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.L1.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.L1.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_L2() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 02 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.L2.getMInfo(); //���� ����
		
		Reset_M_HP = Map.L2.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L2.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L2.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
					Thread.sleep(1000);
					 if(!Map.L2.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
						Thread.sleep(1000);
						 if(!Map.L2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
						Thread.sleep(1000);
						 if(!Map.L2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
						Thread.sleep(1000);
						 if(!Map.L2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
						Thread.sleep(1000);
						 if(!Map.L2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
						Thread.sleep(1000);
						 if(!Map.L2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L2);
						Thread.sleep(1000);
						 if(!Map.L2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.L2.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.L2.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L2.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.L2.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L2.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.L2.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L2.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.L2.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.L2.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_L3() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 03 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.G3.getMInfo(); //���� ����
		
		Reset_M_HP = Map.G3.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.G3.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.G3.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
					Thread.sleep(1000);
					 if(!Map.G3.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.G3);
						Thread.sleep(1000);
						 if(!Map.G3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.G3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.G3.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.G3.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.G3.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.G3.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.G3.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.G3.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.G3.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.G3.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.G3.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_L4() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 04 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.L4.getMInfo(); //���� ����
		
		Reset_M_HP = Map.L4.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L4.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L4.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
					Thread.sleep(1000);
					 if(!Map.L4.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
						Thread.sleep(1000);
						 if(!Map.L4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
						Thread.sleep(1000);
						 if(!Map.L4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
						Thread.sleep(1000);
						 if(!Map.L4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
						Thread.sleep(1000);
						 if(!Map.L4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
						Thread.sleep(1000);
						 if(!Map.L4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L4);
						Thread.sleep(1000);
						 if(!Map.L4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.L4.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.L4.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L4.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.L4.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L4.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.L4.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L4.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.L4.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.L4.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_L5() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 05 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.L5.getMInfo(); //���� ����
		
		Reset_M_HP = Map.L5.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L5.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L5.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
					Thread.sleep(1000);
					 if(!Map.L5.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
						Thread.sleep(1000);
						 if(!Map.L5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
						Thread.sleep(1000);
						 if(!Map.L5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
						Thread.sleep(1000);
						 if(!Map.L5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
						Thread.sleep(1000);
						 if(!Map.L5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
						Thread.sleep(1000);
						 if(!Map.L5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L5);
						Thread.sleep(1000);
						 if(!Map.L5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.L5.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.L5.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L5.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.L5.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L5.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.L5.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L5.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.L5.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.L5.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_L6() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 06 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.L6.getMInfo(); //���� ����
		
		Reset_M_HP = Map.L6.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L6.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L6.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
					Thread.sleep(1000);
					 if(!Map.L6.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
						Thread.sleep(1000);
						 if(!Map.L6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
						Thread.sleep(1000);
						 if(!Map.L6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
						Thread.sleep(1000);
						 if(!Map.L6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
						Thread.sleep(1000);
						 if(!Map.L6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
						Thread.sleep(1000);
						 if(!Map.L6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L6);
						Thread.sleep(1000);
						 if(!Map.L6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.L6.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.L6.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L6.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.L6.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L6.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.L6.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L6.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.L6.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.L6.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_L7() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 07 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.L7.getMInfo(); //���� ����
		
		Reset_M_HP = Map.L7.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L7.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L7.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
					Thread.sleep(1000);
					 if(!Map.L7.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
						Thread.sleep(1000);
						 if(!Map.L7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
						Thread.sleep(1000);
						 if(!Map.L7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
						Thread.sleep(1000);
						 if(!Map.L7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
						Thread.sleep(1000);
						 if(!Map.L7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
						Thread.sleep(1000);
						 if(!Map.L7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L7);
						Thread.sleep(1000);
						 if(!Map.L7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.L7.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.L7.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L7.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.L7.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L7.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.L7.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L7.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.L7.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.L7.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_L8() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 08 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.L8.getMInfo(); //���� ����
		
		Reset_M_HP = Map.L8.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L8.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L8.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
					Thread.sleep(1000);
					 if(!Map.L8.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
						Thread.sleep(1000);
						 if(!Map.L8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
						Thread.sleep(1000);
						 if(!Map.L8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
						Thread.sleep(1000);
						 if(!Map.L8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
						Thread.sleep(1000);
						 if(!Map.L8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
						Thread.sleep(1000);
						 if(!Map.L8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L8);
						Thread.sleep(1000);
						 if(!Map.L8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.L8.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.L8.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L8.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.L8.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L8.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.L8.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L8.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.L8.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.L8.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_L9() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 09 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.L9.getMInfo(); //���� ����
		
		Reset_M_HP = Map.L9.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L9.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L9.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
					Thread.sleep(1000);
					 if(!Map.L9.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
						Thread.sleep(1000);
						 if(!Map.L9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
						Thread.sleep(1000);
						 if(!Map.L9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
						Thread.sleep(1000);
						 if(!Map.L9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
						Thread.sleep(1000);
						 if(!Map.L9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
						Thread.sleep(1000);
						 if(!Map.L9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L9);
						Thread.sleep(1000);
						 if(!Map.L9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.L9.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.L9.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L9.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.L9.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L9.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.L9.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L9.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.L9.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.L9.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_L10() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 10 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.L10.getMInfo(); //���� ����
		
		Reset_M_HP = Map.L10.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.L10.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.L10.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
					Thread.sleep(1000);
					 if(!Map.L10.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
						Thread.sleep(1000);
						 if(!Map.L10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
						Thread.sleep(1000);
						 if(!Map.L10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
						Thread.sleep(1000);
						 if(!Map.L10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
						Thread.sleep(1000);
						 if(!Map.L10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
						Thread.sleep(1000);
						 if(!Map.L10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.L10);
						Thread.sleep(1000);
						 if(!Map.L10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.L10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.L10.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.L10.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.L10.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.L10.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.L10.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.L10.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.L10.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.L10.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.L10.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_W1() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 01 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.W1.getMInfo(); //���� ����
		
		Reset_M_HP = Map.W1.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W1.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W1.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
					Thread.sleep(1000);
					 if(!Map.W1.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
						Thread.sleep(1000);
						 if(!Map.W1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
						Thread.sleep(1000);
						 if(!Map.W1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
						Thread.sleep(1000);
						 if(!Map.W1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
						Thread.sleep(1000);
						 if(!Map.W1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
						Thread.sleep(1000);
						 if(!Map.W1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W1);
						Thread.sleep(1000);
						 if(!Map.W1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.W1.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.W1.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W1.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.W1.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W1.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.W1.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W1.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.W1.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.W1.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_W2() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 02 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.W2.getMInfo(); //���� ����
		
		Reset_M_HP = Map.W2.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W2.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W2.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
					Thread.sleep(1000);
					 if(!Map.W2.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
						Thread.sleep(1000);
						 if(!Map.W2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
						Thread.sleep(1000);
						 if(!Map.W2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
						Thread.sleep(1000);
						 if(!Map.W2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
						Thread.sleep(1000);
						 if(!Map.W2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
						Thread.sleep(1000);
						 if(!Map.W2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W2);
						Thread.sleep(1000);
						 if(!Map.W2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.W2.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.W2.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W2.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.W2.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W2.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.W2.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W2.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.W2.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.W2.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_W3() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 03 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.W3.getMInfo(); //���� ����
		
		Reset_M_HP = Map.W3.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W3.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W3.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
					Thread.sleep(1000);
					 if(!Map.W3.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
						Thread.sleep(1000);
						 if(!Map.W3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
						Thread.sleep(1000);
						 if(!Map.W3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
						Thread.sleep(1000);
						 if(!Map.W3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
						Thread.sleep(1000);
						 if(!Map.W3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
						Thread.sleep(1000);
						 if(!Map.W3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W3);
						Thread.sleep(1000);
						 if(!Map.W3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.W3.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.W3.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W3.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.W3.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W3.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.W3.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W3.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.W3.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.W3.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_W4() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 04 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.W4.getMInfo(); //���� ����
		
		Reset_M_HP = Map.W4.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W4.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W4.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
					Thread.sleep(1000);
					 if(!Map.W4.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
						Thread.sleep(1000);
						 if(!Map.W4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
						Thread.sleep(1000);
						 if(!Map.W4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
						Thread.sleep(1000);
						 if(!Map.W4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
						Thread.sleep(1000);
						 if(!Map.W4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
						Thread.sleep(1000);
						 if(!Map.W4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W4);
						Thread.sleep(1000);
						 if(!Map.W4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.W4.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.W4.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W4.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.W4.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W4.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.W4.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W4.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.W4.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.W4.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_W5() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 05 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.W5.getMInfo(); //���� ����
		
		Reset_M_HP = Map.W5.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W5.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W5.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
					Thread.sleep(1000);
					 if(!Map.W5.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
						Thread.sleep(1000);
						 if(!Map.W5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
						Thread.sleep(1000);
						 if(!Map.W5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
						Thread.sleep(1000);
						 if(!Map.W5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
						Thread.sleep(1000);
						 if(!Map.W5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
						Thread.sleep(1000);
						 if(!Map.W5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W5);
						Thread.sleep(1000);
						 if(!Map.W5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.W5.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.W5.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W5.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.W5.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W5.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.W5.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W5.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.W5.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.W5.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_W6() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 06 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.W6.getMInfo(); //���� ����
		
		Reset_M_HP = Map.W6.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W6.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W6.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
					Thread.sleep(1000);
					 if(!Map.W6.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
						Thread.sleep(1000);
						 if(!Map.W6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
						Thread.sleep(1000);
						 if(!Map.W6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
						Thread.sleep(1000);
						 if(!Map.W6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
						Thread.sleep(1000);
						 if(!Map.W6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
						Thread.sleep(1000);
						 if(!Map.W6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W6);
						Thread.sleep(1000);
						 if(!Map.W6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.W6.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.W6.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W6.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.W6.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W6.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.W6.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W6.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.W6.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.W6.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_W7() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 07 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.W7.getMInfo(); //���� ����
		
		Reset_M_HP = Map.W7.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W7.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W7.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
					Thread.sleep(1000);
					 if(!Map.W7.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
						Thread.sleep(1000);
						 if(!Map.W7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
						Thread.sleep(1000);
						 if(!Map.W7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
						Thread.sleep(1000);
						 if(!Map.W7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
						Thread.sleep(1000);
						 if(!Map.W7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
						Thread.sleep(1000);
						 if(!Map.W7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W7);
						Thread.sleep(1000);
						 if(!Map.W7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.W7.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.W7.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W7.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.W7.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W7.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.W7.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W7.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.W7.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.W7.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_W8() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 08 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.W8.getMInfo(); //���� ����
		
		Reset_M_HP = Map.W8.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W8.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W8.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
					Thread.sleep(1000);
					 if(!Map.W8.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
						Thread.sleep(1000);
						 if(!Map.W8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
						Thread.sleep(1000);
						 if(!Map.W8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
						Thread.sleep(1000);
						 if(!Map.W8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
						Thread.sleep(1000);
						 if(!Map.W8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
						Thread.sleep(1000);
						 if(!Map.W8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W8);
						Thread.sleep(1000);
						 if(!Map.W8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.W8.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.W8.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W8.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.W8.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W8.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.W8.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W8.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.W8.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.W8.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_W9() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 09 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.W9.getMInfo(); //���� ����
		
		Reset_M_HP = Map.W9.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W9.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W9.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
					Thread.sleep(1000);
					 if(!Map.W9.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
						Thread.sleep(1000);
						 if(!Map.W9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
						Thread.sleep(1000);
						 if(!Map.W9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
						Thread.sleep(1000);
						 if(!Map.W9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
						Thread.sleep(1000);
						 if(!Map.W9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
						Thread.sleep(1000);
						 if(!Map.W9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W9);
						Thread.sleep(1000);
						 if(!Map.W9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.W9.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.W9.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W9.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.W9.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W9.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.W9.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W9.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.W9.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.W9.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_W10() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 10 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.W10.getMInfo(); //���� ����
		
		Reset_M_HP = Map.W10.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.W10.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.W10.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
					Thread.sleep(1000);
					 if(!Map.W10.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
						Thread.sleep(1000);
						 if(!Map.W10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
						Thread.sleep(1000);
						 if(!Map.W10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
						Thread.sleep(1000);
						 if(!Map.W10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
						Thread.sleep(1000);
						 if(!Map.W10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
						Thread.sleep(1000);
						 if(!Map.W10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.W10);
						Thread.sleep(1000);
						 if(!Map.W10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.W10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.W10.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.W10.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.W10.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.W10.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.W10.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.W10.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.W10.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.W10.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.W10.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
 		D_bgm.stop();
		
	}
	
	public static void Battle_F1() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 01 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.F1.getMInfo(); //���� ����
		
		Reset_M_HP = Map.F1.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F1.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F1.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
					Thread.sleep(1000);
					 if(!Map.F1.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
						Thread.sleep(1000);
						 if(!Map.F1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
						Thread.sleep(1000);
						 if(!Map.F1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
						Thread.sleep(1000);
						 if(!Map.F1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
						Thread.sleep(1000);
						 if(!Map.F1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
						Thread.sleep(1000);
						 if(!Map.F1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F1);
						Thread.sleep(1000);
						 if(!Map.F1.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F1.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.F1.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.F1.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F1.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.F1.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F1.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.F1.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F1.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.F1.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.F1.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
 		D_bgm.stop();
		
	}
	
	public static void Battle_F2() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 02 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.F2.getMInfo(); //���� ����
		
		Reset_M_HP = Map.F2.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F2.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F2.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
					Thread.sleep(1000);
					 if(!Map.F2.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
						Thread.sleep(1000);
						 if(!Map.F2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
						Thread.sleep(1000);
						 if(!Map.F2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
						Thread.sleep(1000);
						 if(!Map.F2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
						Thread.sleep(1000);
						 if(!Map.F2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
						Thread.sleep(1000);
						 if(!Map.F2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F2);
						Thread.sleep(1000);
						 if(!Map.F2.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F2.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.F2.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.F2.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F2.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.F2.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F2.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.F2.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F2.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.F2.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.F2.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
 		D_bgm.stop();
		
	}
	
	public static void Battle_F3() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 03 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.F3.getMInfo(); //���� ����
		
		Reset_M_HP = Map.F3.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F3.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F3.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
					Thread.sleep(1000);
					 if(!Map.F3.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
						Thread.sleep(1000);
						 if(!Map.F3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
						Thread.sleep(1000);
						 if(!Map.F3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
						Thread.sleep(1000);
						 if(!Map.F3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
						Thread.sleep(1000);
						 if(!Map.F3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
						Thread.sleep(1000);
						 if(!Map.F3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F3);
						Thread.sleep(1000);
						 if(!Map.F3.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F3.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.F3.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.F3.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F3.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.F3.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F3.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.F3.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F3.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.F3.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.F3.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_F4() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 04 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.F4.getMInfo(); //���� ����
		
		Reset_M_HP = Map.F4.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F4.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F4.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
					Thread.sleep(1000);
					 if(!Map.F4.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
						Thread.sleep(1000);
						 if(!Map.F4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
						Thread.sleep(1000);
						 if(!Map.F4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
						Thread.sleep(1000);
						 if(!Map.F4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
						Thread.sleep(1000);
						 if(!Map.F4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
						Thread.sleep(1000);
						 if(!Map.F4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F4);
						Thread.sleep(1000);
						 if(!Map.F4.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F4.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.F4.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.F4.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F4.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.F4.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F4.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.F4.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F4.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.F4.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.F4.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_F5() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 05 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.F5.getMInfo(); //���� ����
		
		Reset_M_HP = Map.F5.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F5.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F5.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
					Thread.sleep(1000);
					 if(!Map.F5.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
						Thread.sleep(1000);
						 if(!Map.F5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
						Thread.sleep(1000);
						 if(!Map.F5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
						Thread.sleep(1000);
						 if(!Map.F5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
						Thread.sleep(1000);
						 if(!Map.F5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
						Thread.sleep(1000);
						 if(!Map.F5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F5);
						Thread.sleep(1000);
						 if(!Map.F5.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F5.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.F5.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.F5.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F5.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.F5.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F5.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.F5.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F5.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.F5.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.F5.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}
	
	public static void Battle_F6() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 06 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.F6.getMInfo(); //���� ����
		
		Reset_M_HP = Map.F6.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F6.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F6.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
					Thread.sleep(1000);
					 if(!Map.F6.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
						Thread.sleep(1000);
						 if(!Map.F6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
						Thread.sleep(1000);
						 if(!Map.F6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
						Thread.sleep(1000);
						 if(!Map.F6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
						Thread.sleep(1000);
						 if(!Map.F6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
						Thread.sleep(1000);
						 if(!Map.F6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F6);
						Thread.sleep(1000);
						 if(!Map.F6.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F6.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.F6.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.F6.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F6.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.F6.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F6.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.F6.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F6.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.F6.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.F6.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_F7() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 07 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.F7.getMInfo(); //���� ����
		
		Reset_M_HP = Map.F7.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F7.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F7.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
					Thread.sleep(1000);
					 if(!Map.F7.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
						Thread.sleep(1000);
						 if(!Map.F7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
						Thread.sleep(1000);
						 if(!Map.F7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
						Thread.sleep(1000);
						 if(!Map.F7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
						Thread.sleep(1000);
						 if(!Map.F7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
						Thread.sleep(1000);
						 if(!Map.F7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F7);
						Thread.sleep(1000);
						 if(!Map.F7.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F7.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.F7.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.F7.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F7.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.F7.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F7.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.F7.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F7.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.F7.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.F7.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_F8() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 08 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.F8.getMInfo(); //���� ����
		
		Reset_M_HP = Map.F8.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F8.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F8.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
					Thread.sleep(1000);
					 if(!Map.F8.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
						Thread.sleep(1000);
						 if(!Map.F8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
						Thread.sleep(1000);
						 if(!Map.F8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
						Thread.sleep(1000);
						 if(!Map.F8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
						Thread.sleep(1000);
						 if(!Map.F8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
						Thread.sleep(1000);
						 if(!Map.F8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F8);
						Thread.sleep(1000);
						 if(!Map.F8.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F8.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.F8.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.F8.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F8.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.F8.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F8.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.F8.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F8.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.F8.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.F8.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_F9() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 09 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.F9.getMInfo(); //���� ����
		
		Reset_M_HP = Map.F9.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F9.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F9.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
					Thread.sleep(1000);
					 if(!Map.F9.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
						Thread.sleep(1000);
						 if(!Map.F9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
						Thread.sleep(1000);
						 if(!Map.F9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
						Thread.sleep(1000);
						 if(!Map.F9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
						Thread.sleep(1000);
						 if(!Map.F9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
						Thread.sleep(1000);
						 if(!Map.F9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F9);
						Thread.sleep(1000);
						 if(!Map.F9.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F9.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.F9.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.F9.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F9.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.F9.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F9.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.F9.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F9.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.F9.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.F9.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_F10() throws InterruptedException

	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("�� - 10 ���� ���� ��.....");
		Thread.sleep(1000);
		Map.F10.getMInfo(); //���� ����
		
		Reset_M_HP = Map.F10.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.F10.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.F10.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
					Thread.sleep(1000);
					 if(!Map.F10.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
						Thread.sleep(1000);
						 if(!Map.F10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
						Thread.sleep(1000);
						 if(!Map.F10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
						Thread.sleep(1000);
						 if(!Map.F10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
						Thread.sleep(1000);
						 if(!Map.F10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
						Thread.sleep(1000);
						 if(!Map.F10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.F10);
						Thread.sleep(1000);
						 if(!Map.F10.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.F10.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.F10.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.F10.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.F10.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.F10.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.F10.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.F10.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.F10.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.F10.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.F10.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

	public static void Battle_Boss() throws InterruptedException
	{
		Runnable Dungeon_BGM = new Dungeon_BGM();
		Thread D_bgm = new Thread(Dungeon_BGM);
		D_bgm.start();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int Battle_Select_Action;
		int Battle_USelect;
		int Per_Potion;
		int Reset_M_HP, Reset_M_Atk;
		int Reset_U1_HP, Reset_U2_HP, Reset_U3_HP;
		int Reset_U1_Atk, Reset_U2_Atk, Reset_U3_Atk;
		int Use_Select_Potion;
		int Use_sPo;
		int Use_mPo;
		int Use_bPo;
		char Exit;
		Menu menu = new Menu();
		
		System.out.println("���� ���� ���� ��.....");
		Thread.sleep(1000);
		Map.Boss.getMInfo(); //���� ����
		
		Reset_M_HP = Map.Boss.HP;
		Reset_U1_HP = Unit.UnitList_battle[0].HP;
		Reset_U2_HP = Unit.UnitList_battle[1].HP;
		Reset_U3_HP = Unit.UnitList_battle[2].HP;
		Reset_M_Atk = Map.Boss.Atk;
		Reset_U1_Atk = Unit.UnitList_battle[0].Atk;
		Reset_U2_Atk = Unit.UnitList_battle[1].Atk;
		Reset_U3_Atk = Unit.UnitList_battle[2].Atk;
		
		
Loop1 : while((Unit.UnitList_battle[0].Live()||Unit.UnitList_battle[1].Live()||Unit.UnitList_battle[2].Live())&&Map.Boss.Live()) //���� ��������
		{
			
			menu.Battle_Select();
			Battle_Select_Action = sc.nextInt();
			
			switch(Battle_Select_Action)
			{
			case 1: //�����ϱ�
				
				if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live())  //���ִ� ���������
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					for(int a=0; a<3; a++)
					{
						System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +"  ���� : "+ Unit.UnitList_battle[a].Level+"  ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
					}
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					System.out.println("-------------------------------------");
					Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
					Thread.sleep(1000);
					 if(!Map.Boss.Live()) 
					 {
					 break;
					 }
					 System.out.println("-------------------------------------");
					Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
					Thread.sleep(1000);
					
				}
				
				else if(Unit.UnitList_battle[0].Live()==false&&Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
										
					if(Battle_USelect == 0)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
						Thread.sleep(1000);
						 if(!Map.Boss.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}

				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[2].Level+" ���ݷ� : "+ Unit.UnitList_battle[2].Atk+" ü�� : "+ Unit.UnitList_battle[2].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
						Thread.sleep(1000);
						 if(!Map.Boss.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				
				else if(Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0 || Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
						Thread.sleep(1000);
						 if(!Map.Boss.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&Unit.UnitList_battle[2].Live()) //ù��°,�ι�° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 2) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
						Thread.sleep(1000);
						 if(!Map.Boss.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(!Unit.UnitList_battle[0].Live()&&Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //ù��°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("2." +Unit.UnitList_battle[1].name +" ���� : "+ Unit.UnitList_battle[1].Level+" ���ݷ� : "+ Unit.UnitList_battle[1].Atk+" ü�� : "+ Unit.UnitList_battle[1].HP);
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�." );
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 0 || Battle_USelect == 2)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 1) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
						Thread.sleep(1000);
						 if(!Map.Boss.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				else if(Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live()) //�ι�°, ����° ���� �׾�����
				{
					System.out.println("-------------------------------------");
					System.out.println("��Ƽ ���");
					System.out.println("1." +Unit.UnitList_battle[0].name +" ���� : "+ Unit.UnitList_battle[0].Level+" ���ݷ� : "+ Unit.UnitList_battle[0].Atk+" ü�� : "+ Unit.UnitList_battle[0].HP );
					System.out.println("2." +Unit.UnitList_battle[1].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("3." +Unit.UnitList_battle[2].name +" ����Ͽ� ������ �� �����ϴ�.");
					System.out.println("-------------------------------------");
					
					System.out.println("������ ������ �������ּ���.");
					Battle_USelect = sc.nextInt()-1;
					
					if(Battle_USelect == 2 || Battle_USelect == 1)		//�������� ����������
					{	
						System.out.println("�����Ͻ� ������ ����Ͽ� ������ �� �����ϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
						
					}
					
					else if(Battle_USelect == 0) 	//��������  �������
					{
						System.out.println("-------------------------------------");
						Unit.UnitList_battle[Battle_USelect].attack(Map.Boss);
						Thread.sleep(1000);
						 if(!Map.Boss.Live()) 
						 {
						 break;
						 }
						 System.out.println("-------------------------------------");
						Map.Boss.attack(Unit.UnitList_battle[Battle_USelect]);
						Thread.sleep(1000);
					}
					
					else	//�׿� �� �Է����� ��
					{
						System.out.println("�߸��� �����Դϴ�.");
						System.out.println("�ൿ�� �ٽ� ������ �ּ���.");
					}
				}
				
				Map.Boss.Atk = Reset_M_Atk;
				Unit.UnitList_battle[0].Atk = Reset_U1_Atk;
				Unit.UnitList_battle[1].Atk = Reset_U2_Atk;
				Unit.UnitList_battle[2].Atk = Reset_U3_Atk;
				
				break;
				
			case 2: //������
				System.out.println("-----------���� ������Ȳ----------");
		   		System.out.println("1. ���� ����(HP20) : "+User.Potion_small);
		   		System.out.println("2. ���� ����(HP40) : "+User.Potion_medium);
		   		System.out.println("3. ���� ����(HP60) : "+User.Potion_big);
				System.out.println("����Ͻ� ������ �����ϼ���.");
				Use_Select_Potion = sc.nextInt();
				
				switch(Use_Select_Potion)
				{ 
				case 1:	//�������༱��
					if(User.Potion_small>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_sPo = sc.nextInt();
						Unit.UnitList_battle[Use_sPo-1].incHP(20);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� 20 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_sPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_sPo-1].HP);
						}
						User.Potion_small -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 2: //�������� ����
					if(User.Potion_medium>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_mPo = sc.nextInt();
						Unit.UnitList_battle[Use_mPo-1].incHP(40);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� 40 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_mPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_mPo-1].HP);
						}
						User.Potion_medium -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
				case 3: //�������� ����
					if(User.Potion_big>0) //������ ������
					{
						System.out.println("ȸ���� ������ �����ϼ���.");
						for(int a=0; a<3; a++)
						{
							System.out.println(a+1 + "." +Unit.UnitList_battle[a].name +" ���� : "+ Unit.UnitList_battle[a].Level+" ���ݷ� : "+ Unit.UnitList_battle[a].Atk+" ü�� : "+ Unit.UnitList_battle[a].HP );
						}
						
						Use_bPo = sc.nextInt();
						Unit.UnitList_battle[Use_bPo-1].incHP(60);
						
						if(Unit.UnitList_battle[0].HP > Reset_U1_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[0].HP = Reset_U1_HP;
							System.out.println(Unit.UnitList_battle[0].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[0].name + " ü�� : " + Unit.UnitList_battle[0].HP);
						}
						else if(Unit.UnitList_battle[1].HP > Reset_U2_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[1].HP = Reset_U2_HP;
							System.out.println(Unit.UnitList_battle[1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[1].name + " ü�� : " + Unit.UnitList_battle[1].HP);
						}
						else if(Unit.UnitList_battle[2].HP > Reset_U3_HP) //���������� �� �ִ�ü�� �̻��� �ɰ�� �ִ�ü�±����� ä����
						{
							
							Unit.UnitList_battle[2].HP = Reset_U3_HP;
							System.out.println(Unit.UnitList_battle[2].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[2].name + " ü�� : " + Unit.UnitList_battle[2].HP);
						}
						else
						{
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� 60 ȸ��!!");
							System.out.println(Unit.UnitList_battle[Use_bPo-1].name + " ü�� : " + Unit.UnitList_battle[Use_bPo-1].HP);
						}
						User.Potion_big -= 1;
					}
					else // ���� ���� ��
					{
						System.out.println("�����ϰ� �ִ� ������ �����ϴ�.");
					}
					break;
					
					default : 
						System.out.println("�߸��� �Է��Դϴ�.");
					break;
				}
				
				break; //������ ����
			case 3: //������
				System.out.println("������ �����ðڽ��ϱ�? Y/N");
				Exit=sc.next().charAt(0);
				
				if(Exit == 'Y')
				{
					break Loop1; //��������
				}
				
				else
					break;  //������ ����
			default : 
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
				
			}
		}
	
		//�������� �� ��� �� ����
		if(!Map.Boss.Live()) 		//�÷��̾ �̰������
        {
		   System.out.println("-------------������-------------");
           System.out.println(User.name + " Win!!");
           System.out.println("------------����ġȹ��------------");
          
           Unit.UnitList_battle[0].Exp +=Map.Boss.Exp;											//����ġ����
           System.out.println(Unit.UnitList_battle[0].name + " ����ġ " + Map.Boss.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[0].Exp >= Unit.UnitList_battle[0].Level*300)
           {
        	   Unit.UnitList_battle[0].Level += 1;
        	   System.out.println(Unit.UnitList_battle[0].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[0].Level);
        	   System.out.println(Unit.UnitList_battle[0].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[0].Level*300 - Unit.UnitList_battle[0].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[1].Exp +=Map.Boss.Exp;
           System.out.println(Unit.UnitList_battle[1].name + " ����ġ " + Map.Boss.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[1].Exp >= Unit.UnitList_battle[1].Level*300)
           {
        	   Unit.UnitList_battle[1].Level += 1;
        	   System.out.println(Unit.UnitList_battle[1].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[1].Level);
        	   System.out.println(Unit.UnitList_battle[1].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[1].Level*300 - Unit.UnitList_battle[1].Exp));
           }
           System.out.println("---------------------------------");
           Unit.UnitList_battle[2].Exp +=Map.Boss.Exp;
           System.out.println(Unit.UnitList_battle[2].name + " ����ġ " + Map.Boss.Exp +"ȹ��!!");
           if(Unit.UnitList_battle[2].Exp >= Unit.UnitList_battle[2].Level*300)
           {
        	   Unit.UnitList_battle[2].Level += 1;
        	   
        	   System.out.println(Unit.UnitList_battle[2].name + " ������!!");
        	   System.out.println("���緹�� : " + Unit.UnitList_battle[2].Level);
        	   System.out.println(Unit.UnitList_battle[2].Level+1 + "�������� �ʿ����ġ : " + (Unit.UnitList_battle[2].Level*300 - Unit.UnitList_battle[2].Exp));
           }
           Thread.sleep(1000);
           System.out.println("-------------���ȹ��-------------");
           User.Money += Reset_M_HP;//��ȹ��
           System.out.println(Reset_M_HP + "�� ȹ��!!");
           System.out.println("�����ݾ� : "+User.Money+"��");
           Thread.sleep(1000);
           
           System.out.println("-------------����ȹ��-------------");
           Per_Potion = random.nextInt(10);	//����ȹ�� Ȯ��
           
           if(Per_Potion == 9) 	//10% ��������
           {
        	   User.Potion_big +=1;
        	   System.out.println("�������� ȹ��!!");

           }
           else if(Per_Potion ==8 || Per_Potion == 7)	//20% ��������
           {
        	   User.Potion_medium += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else if(Per_Potion ==6 || Per_Potion == 5 || Per_Potion ==4)	//30% ��������
           {
        	   User.Potion_small += 1;
        	   System.out.println("�������� ȹ��!!");
           }
           else 		//40% ��ȹ��
           {
        	   System.out.println("ȹ���� ������ �����ϴ�.");
           }
           
    	   System.out.println("-----------���� ������Ȳ----------");
   		   System.out.println("���� ���� : "+User.Potion_small);
   		   System.out.println("���� ���� : "+User.Potion_medium);
   		   System.out.println("���� ���� : "+User.Potion_big);
           
           
           System.out.println();
           Clear clear = new Clear();
           
           clear.start();
           try {
   			clear.join();
   			}
   		catch(InterruptedException e)
   		{}
          
        }
        else if(!Unit.UnitList_battle[0].Live()&&!Unit.UnitList_battle[1].Live()&&!Unit.UnitList_battle[2].Live())
        {
           System.out.println("-------------������-------------");
           System.out.println(User.name + " Lose��.��");
        }
		
		 Map.Boss.HP = Reset_M_HP;
 		 Unit.UnitList_battle[0].HP = Reset_U1_HP;
 	     Unit.UnitList_battle[1].HP = Reset_U2_HP;
 		 Unit.UnitList_battle[2].HP = Reset_U3_HP;
		
 		D_bgm.stop();
	}

		
}











