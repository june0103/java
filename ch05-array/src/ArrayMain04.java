public class ArrayMain04 {

	public static void main(String[] args) {
		
		//�ִ밪, �ּҰ� ���ϱ�
		int score[] = {79, 88, 91, 33, 100, 55, 95};
		
		int max = score[0]; //�迭�� ù��° ������ �ִ밪�� �����Ͽ� ��
		int min = score[0];
		
		for(int i = 1; i<score.length; i++) {
			//�ִ밪 ���ϱ�
			if(score[i] > max) {
				max = score[i];
			}
			//�ּҰ� ���ϱ�
			if (score[i] < min) {
				min = score[i];
			}
		}
		
		System.out.println(max);
		System.out.println(min);
	}
	
}
