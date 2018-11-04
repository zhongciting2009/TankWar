package tankwar;



import java.util.Vector;




public class MyTank extends Tank {
	private int tankType=Tank.TANK_TYPE_US;
	private Vector<Bullet> zdVector=new Vector<Bullet>();
   
	private int orientation=Tank.TANK_ORIENTATION_NORTH;
	public void fszd() {//�û�����j�������ӵ�ʱ    new��һ���ӵ����󲢼��뵽vector
		if(MyTankData.currBlood>0){ //�ҷ�̹�˻���  ���ܷ����ӵ�
			Bullet zd=new Bullet();
			switch(this.orientation){
			case  Tank.TANK_ORIENTATION_NORTH: 
			  	zd=new Bullet(x+10,y,Bullet.BULLET_ORIENTATION_NORTH,MyTankData.zdSpeed);
				zdVector.add(zd);
				break;
			case  Tank.TANK_ORIENTATION_WEST:
				zd=new Bullet(x,y+10,Bullet.BULLET_ORIENTATION_WEST,MyTankData.zdSpeed);
				zdVector.add(zd);
				break;
			case  Tank.TANK_ORIENTATION_SOUTH:
				zd=new Bullet(x+10,y+30,Bullet.BULLET_ORIENTATION_SOUTH,MyTankData.zdSpeed);
				zdVector.add(zd);
				break;
			case  Tank.TANK_ORIENTATION_EAST:
				zd=new Bullet(x+30,y+10,Bullet.BULLET_ORIENTATION_EAST,MyTankData.zdSpeed);
				zdVector.add(zd);
				break;
			}
			Thread t=new Thread(zd);//�ӵ������Ѳ����� �����ӵ��߳�
			t.start();
		}
		
	}
	
	public MyTank(int x,int y){
    	this.x=x;
    	this.y=y;
    }
	 
	public MyTank() {
		super();
	}
	public Vector<Bullet> getZdVector() {
		return zdVector;
	}
	public void setZdVector(Vector<Bullet> zdVector) {
		this.zdVector = zdVector;
	}
	public int getTankType() {
		return tankType;
	}
	public void setTankType(int tankType) {
		this.tankType = tankType;
	}
  
	public int getOrientation() {
		return orientation;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public void northmove() {
		this.y -= MyTankData.speed;
	}
	public void westmove() {
		this.x -=  MyTankData.speed;
	}
	public void southmove() {
		this.y +=  MyTankData.speed;
	}
	public void eastmove() {
		this.x+=  MyTankData.speed;
	}

 
}
