package tankwar;

/*
  ��Ϸ�û�����
 */
public class MyTankData {
    public static int totalScore=0;//�õķ���  
    public static int sceneNumber=1;//�ؿ���  ��ʾ�ڼ���
    public static int currBlood=3;//�û�̹�˵ĵ�ǰѪ��
    public static int maxBlood=9;//�û�̹�˵����Ѫ��
	public static int zdSpeed=3;//�ӵ��ƶ��ٶ�
	public static int zdMaxSpeed=9;
	public static int speed=3;//̹���ƶ��ٶ�
	public static int maxSpeed=9;//̹���ƶ�
    public static void initData(){
    	 speed=3;//�ƶ��ٶ�
    	 zdSpeed=3;//�ӵ��ƶ��ٶ�
    	totalScore=0;//�õķ���  
    	sceneNumber=1;//�ؿ���  ��ʾ�ڼ���
    	currBlood=3;//�û�̹�˵ĵ�ǰѪ��
    	maxBlood=9;//���Ѫ��
    	zdMaxSpeed=9;//����ٶ�
    	maxSpeed=9;//�ӵ��ƶ�����ٶ�
    	
    }
}
