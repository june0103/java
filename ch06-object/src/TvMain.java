//�ϳ��� �ڹ����Ͽ� ���������� Ŭ������ ����� �ִ�.
//�����Ͻ��� �������ϸ� ���θ޼ҵ尡�ִ� Ŭ������ �� Ŭ������ ����ȴ�.
//������ public�� ���θ޼ҵ尡�ִ� Ŭ������ ��밡���ϴ�
class Tv{
	//�������
	boolean power; //�������� (on / off)
	int channel; //ä��

	//����޼ҵ�
	public void isPower() {
		power = !power; //������ on/off �Ѵ�.  �������
		
	}
	
	public void channelUp() {
		++channel; //ä���� ���̴� ���
	}
	
	public void channelDown() {
		--channel; //ä���� ���ߴ� ���
	}
}

public class TvMain {
	
	public static void main(String[] args) {

		Tv tv = new Tv();
		tv.isPower(); //false -> true
		System.out.println("TV ���� ���� : " + tv.power);
		System.out.println("���� ä�� : " + tv.channel);
		
		System.out.println("---------------------");
		tv.channel = 7;
		System.out.println("ù��° ����� ä�� : " + tv.channel);

		System.out.println("---------------------");
		tv.channelUp();
		System.out.println("�ι�° ����� ä�� : " + tv.channel);
		
		System.out.println("---------------------");
		tv.isPower();
		System.out.println("TV ���� ���� : " + tv.power);
	}
}
