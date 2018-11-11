package tankwar;

import java.util.Random;


/**
 * Ԫ��   �߳���
 * 
 * 
 */
public class Element implements Runnable {
	public  static final int ELEMENT_ORIENTATION_NORTH=0;
	public  static final int ELEMENT_ORIENTATION_WEST=1;
	public  static final int ELEMENT_ORIENTATION_SOUTH=2;
	public  static final int ELEMENT_ORIENTATION_EAST=3;
	public static final int TYPE_BLOOD=0;//Ѫ��
	public static final int TYPE_SPIRIT=1;//�ƶ��ٶ�
	public static final int TYPE_WISDOM=2;//�ӵ��ٶ�
	private int x;
	private int y;
	private boolean threadAlive=true;
    private Random r=new Random();
    private int orientation=ELEMENT_ORIENTATION_SOUTH;
    private int speed=12;
	/**
	 * Ԫ������  0 (Ѫ��)   1(�ƶ��ٶ�)   2(�ӵ��ٶ�)
	 */
	private int type;
	@Override
	public void run() {
		while(threadAlive){
			try {
					 orientation=r.nextInt(4); 
					 switch(orientation){
					 case ELEMENT_ORIENTATION_NORTH:
						 if(this.y>0){
							  this.y-=this.speed;
						 }
						 break;
					 case   ELEMENT_ORIENTATION_WEST:
						 if(this.x>0){
							 this.x-=this.speed;
						 }
						 break;
					 case   ELEMENT_ORIENTATION_SOUTH:
						 if(this.y<490){
							 this.y+=this.speed;		 
						 }
							break;
					 case   ELEMENT_ORIENTATION_EAST:
						 if(this.x<490){
							 this.x+= this.speed;
						 }
						 break;
						 
  					 }
					 Thread.sleep(1000);
				 }
				
			  catch (Exception e) {
			}
		}
	}
	//�ж��Ƿ���̹����ײ
	public boolean  isCollsion(Tank tank){
		
		switch(tank.getOrientation()){
		case Tank.TANK_ORIENTATION_NORTH:
		case Tank.TANK_ORIENTATION_SOUTH:
		if(this.x>=tank.getX()&&this.x<=tank.getX()+20&&this.y>=tank.getY()&&this.y<=tank.getY()+30){
			return true;	
		}
		if(this.x+10>=tank.getX()&&this.x+10<=tank.getX()+20&&this.y>=tank.getY()&&this.y<=tank.getY()+30){
			return true;
		}
		if(this.x>=tank.getX()&&this.x<=tank.getX()+20&&this.y+10>=tank.getY()&&this.y+10<=tank.getY()+30){
			return true;
		}
		if(this.x+10>=tank.getX()&&this.x+10<=tank.getX()+20&&this.y+10>=tank.getY()&&this.y+10<=tank.getY()+30){
			return true;
		}
		break;
		case  Tank.TANK_ORIENTATION_EAST:
		case  Tank.TANK_ORIENTATION_WEST:
			if(this.x>=tank.getX()&&this.x<=tank.getX()+30&&this.y>=tank.getY()&&this.y<=tank.getY()+20){
				return true;	
			}
			if(this.x+10>=tank.getX()&&this.x+10<=tank.getX()+30&&this.y>=tank.getY()&&this.y<=tank.getY()+20){
				return true;
			}
			if(this.x>=tank.getX()&&this.x<=tank.getX()+30&&this.y+10>=tank.getY()&&this.y+10<=tank.getY()+20){
				return true;
			}
			if(this.x+10>=tank.getX()&&this.x+10<=tank.getX()+30&&this.y+10>=tank.getY()&&this.y+10<=tank.getY()+20){
				return true;
			}
			break;
		}
		return false;
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

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isThreadAlive() {
		return threadAlive;
	}
	public void setThreadAlive(boolean threadAlive) {
		this.threadAlive = threadAlive;
	}
	public Element() {
		super();
	}
	public Element(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Element(int x, int y, int type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	
}
