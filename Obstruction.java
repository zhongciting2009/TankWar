package tankwar;

/**
 * �ϰ���  �߳���
 *  20*20

 */
public class Obstruction implements Runnable{
	private int x;
	private int y;
	private int materical;//�ϰ��� ��������   0��ש   1��ש 
    //private boolean threadAlive=true;
	//private boolean  bitten=false;//false û�л��� �� true ����
	//private boolean isAlive=true;// �����
	private int blood=3;//�ϰ�����3��Ѫ 
	@Override
	public void run() {
		 while(blood>0){
			try {
			   Thread.sleep(700);
			} catch (Exception e) {
				 e.printStackTrace();
			}
		 }
	}
	/**
	 * �ж��ӵ��Ƿ��������(�ϰ���)
	 * @param zd
	 * @return
	 */
    public boolean isBitten(Bullet zd){
    	if(zd.isThreadAlive()&&zd.getX()>=this.x&&zd.getX()<=this.getX()+20
    			&&zd.getY()>=this.getY()&&zd.getY()<=this.getY()+20){
    		return true;
    	}else{
    		return false;
    	}
    }
   
	 
	//����һ��Ѫ
	public void reduceBlood(){
		this.blood-=1;
	}
 
	public int getBlood() {
		return blood;
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

	public int getMaterical() {
		return materical;
	}

	public void setMaterical(int materical) {
		this.materical = materical;
	}

	public Obstruction() {
		super();
	}

	public Obstruction(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Obstruction(int x, int y, int materical) {
		super();
		this.x = x;
		this.y = y;
		this.materical = materical;//���� 0 �� 1 ����
	}
	 
 
   
 
}
