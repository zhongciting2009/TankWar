package tankwar;
/*
�ӵ� �߳���
 */
public class Bullet implements  Runnable {
	public static final int BULLET_ORIENTATION_NORTH=0;//�ӵ�����  ��
	public  static final int BULLET_ORIENTATION_WEST=1;//��
	public  static final int BULLET_ORIENTATION_SOUTH=2;//��
	public  static final int BULLET_ORIENTATION_EAST=3;//��
	private int x;
	private int y;
    private int speed=4;//�ӵ��ٶ�
	private int orientation;//�ӵ�����
	private  boolean threadAlive=true; //�Ƿ��ڴ����
	 

	public boolean isThreadAlive() {
		return threadAlive;
	}

	public void setThreadAlive(boolean threadAlive) {
		this.threadAlive = threadAlive;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
    
	public Bullet(int x, int y, int orientation) {
		super();
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public Bullet(int x, int y,  int orientation,int speed ) {
		super();
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.orientation = orientation;
	}

	public Bullet(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Bullet() {
		super();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void run() {
		while(threadAlive){
			try {
				Thread.sleep(50);
				switch(this.orientation){
				case Bullet.BULLET_ORIENTATION_NORTH:
					this.y-=this.speed;
					break;
				case Bullet.BULLET_ORIENTATION_WEST:
					this.x-=this.speed;
					break;
				case Bullet.BULLET_ORIENTATION_SOUTH:
					this.y+=this.speed;
					break;
				case Bullet.BULLET_ORIENTATION_EAST:
					this.x+=this.speed;
					break;
				}
				if(this.x<0||this.x>500||this.y<0||this.y>500){
					//this.isAlive=false;
					 this.threadAlive=false;
				}
			} catch (Exception e) {
			}
		}
	}

}
