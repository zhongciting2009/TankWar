package tankwar;

public class EnemyTankData {
	public  static int   sceneNumber=1;//�ؿ���  �ڼ���
    public  static int   speed=3;//�ƶ��ٶ� 
    public  static long sleepInterval=100L;//������
    public  static int launchZdRate=65;//�ӵ���������
	public static int blood=9;//Ѫ��
	public static int orientation=Tank.TANK_ORIENTATION_SOUTH;//̹�˳�ʼ������
	public static int zdSpeed=2;
    public static long  minSleepInterval=50L;//��С������
    public static int maxZdSpeed=9;//����ӵ��ٶ� 9
    public  static int maxSpeed=9;//����ٶ�Ϊ9
    public static int maxLaunchZdRate=100;//�����ӵ����伸��
    public static int maxBlood=9;//���Ѫ��
    public static void initData(){
    	   sceneNumber=1;
           blood=9;
           orientation=Tank.TANK_ORIENTATION_SOUTH;
           speed=3;           
           sleepInterval=100L; 
          launchZdRate=65;
          zdSpeed=3;
    }
	public static void passScene() {//ͨ�غ��Ч��
		  if(sceneNumber%3==0){
			  if(zdSpeed<maxZdSpeed){
				  zdSpeed++;  
			  }
		  }
		  if(sceneNumber%4==0){
			  if(speed<maxSpeed){
				  speed++;// 
			  }
		  }
		  if(sleepInterval>minSleepInterval){
			  sleepInterval-=5;//̹�������ڼ�5
		  }
		  if(launchZdRate<maxLaunchZdRate){
			  launchZdRate+=5;//�����ӵ��ļ��� ����5
		  }
	} 
     
}

