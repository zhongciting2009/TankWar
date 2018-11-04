package tankwar;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * �߳���  ��Ϸ������
 */
public class MainScene extends JPanel implements KeyListener,Runnable {
	private static final long serialVersionUID = 1L;
	private MyTank mt;              //�ҷ�̹��
	private boolean threadAlive=true;
	private int  enemyTankCount=9;//��ʼ�� ����̹������
    private  Vector<EnemyTank>  enemyTankVector;//����̹�˵ļ���
    private Vector<ZJeffect> zjeffectVector;//   ����Ч������
    private String zjimg="/tankwar/images/jizhong.png";
    
    private Random random=new Random();
    
   
     
    private Vector<Obstruction> obstVector=new Vector<Obstruction>();//�ϰ��Ｏ��
    private  int obstcount=38;//�ϰ��������   ������10%
    
	public MainScene() {//���캯�� ��ʼ��
	    super();
	    if(enemyTankVector==null){
	    	enemyTankVector=new Vector<EnemyTank>();
	    }
	    zjeffectVector=new Vector<ZJeffect>();
	    
	    if(mt==null){
	         mt = new MyTank(180, 470);
		     mt.setObstVector(obstVector);
	    }
    	 for(int i=1;i<=enemyTankCount;i++){ //��ʼ�����˵�̹�� ���ӵ�
			  EnemyTank dtank= null;
			  if(i<=3){
				  dtank=new EnemyTank(i*160-80,5);
				  dtank.syncData();
			  }else{
				  dtank=new EnemyTank((i-3)*80-40,10);
				  dtank.syncData();
			  }
			   dtank.setDtankVector(enemyTankVector);
			   dtank.setObstVector(obstVector);
			   enemyTankVector.add(dtank);
			  new Thread(dtank).start();//����̹���߳�����
			  Bullet dzd=new Bullet(dtank.getX()+10,dtank.getY()+30,Tank.TANK_ORIENTATION_SOUTH);//�з��ӵ�
			  dtank.getZdVector().add(dzd);
			  new Thread(dzd).start();//���˷��ӵ����߳�����
			 
		}
		/*    
	     * ���ϰ�����ֵ�����Ϊ(x,y)
	     * x��{f(m)|f(m)=m*20+50},m��{0,1,2,...,21}  ����� r��[0,1)  
	     * y��{f(n)|f(n)=n*20+50},n ��{0,1,2,...,20}   
	     */
		 for(int i=1;i<=obstcount;i++){
			int x=random.nextInt(21)*20+50;
			int y=random.nextInt(20)*20+50;
			int materical=random.nextInt(2);
			 Obstruction obst=new Obstruction(x,y,materical);
			 obstVector.add(obst);
			 new  Thread(obst).start();
		 }
 
		new Thread(this).start();//��屾��100�����ػ�һ��   ���߳�����
	}
    
	/**
	 * paint ���� 
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 500, 500);
	      
		//����̹��
	     if(mt!=null&&MyTankData.currBlood>0){
			this.drawTank(mt.getX(), mt.getY(), g,mt.getTankType(),mt.getOrientation());
		 }
	     //���ҷ��ӵ�
	     if(mt!=null){
	    	 for(int i=0;i<mt.getZdVector().size();i++){//�����ҷ�̹�˵������ӵ�
		        	Bullet zd=mt.getZdVector().get(i);
		        	if(zd!=null&&zd.isThreadAlive()){
		        		g.setColor(Color.WHITE);
		        		g.fill3DRect(zd.getX()-1, zd.getY()-1, 3, 3, false);
		        	}
		        	 //����ҷ��ӵ����ез���
		        	if(!zd.isThreadAlive()){//vector���ӵ������Ƴ�һ��
		        		mt.getZdVector().remove(zd);
		        	}
		       }
	     }
	     //���з�̹��
        for(int i=0;i<enemyTankVector.size();i++){
        	  if(enemyTankVector.get(i).getBlood()>0){
        		  this.drawTank(enemyTankVector.get(i).getX(), 
        	        	   enemyTankVector.get(i).getY(), g, Tank.TANK_TYPE_ENEMY, enemyTankVector.get(i).getOrientation());
        	  }
        }
	    //���з��ӵ�
        for(int i=0;i<enemyTankVector.size();i++){
        	EnemyTank etank= enemyTankVector.get(i);//�з���ÿһ��̹��
     	     for(int j=0;j<etank.getZdVector().size();j++){  //ѭ�������з���ÿһ��̹�ˣ������з�ÿһ��̹�˵� ÿһ���ӵ�
     		   Bullet zd=etank.getZdVector().get(j);//d�ӵ�
     		   if(zd!=null&&zd.isThreadAlive()){
            		g.setColor(Color.WHITE);
            		g.fill3DRect(zd.getX()-1, zd.getY()-1, 3, 3, false);
            	  }
     		   if(!zd.isThreadAlive()){
     			   etank.getZdVector().remove(zd);
     		   }
     	   }
        }
        
        
        //���ϰ���
        for(int i=0;i<obstcount;i++){
             Obstruction obst=obstVector.get(i);
             if(obst.getBlood()>0){//ֻ�е��ϰ���Ѫ������0ʱ �Ż����ϰ���
            	 this.drawObst(obst.getX(), obst.getY(), obst.getMaterical(), g);
             }
        }
       
        
        
        //�����п���Ч��
        for(int i=0;i<zjeffectVector.size();i++){
			ZJeffect zjeffect=zjeffectVector.get(i);
			if(zjeffect.getSurvival()>5){
				Image img=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(zjeffect.getImgurl()));
				g.drawImage(img,zjeffect.getX(), zjeffect.getY(),15,15, this);
			}else if(zjeffect.getSurvival()>3){
				Image img=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(zjeffect.getImgurl()));
				g.drawImage(img,zjeffect.getX(), zjeffect.getY(),20,20, this);
			}else if(zjeffect.getSurvival()>0){
				Image img=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(zjeffect.getImgurl()));
				g.drawImage(img,zjeffect.getX(), zjeffect.getY(),30,30, this);
			}
			zjeffect.surReduce();
			if(zjeffect.getSurvival()==0){
				zjeffectVector.remove(zjeffect);
				zjeffect=null;
			}
		}
        
        
          //��ÿ��̹�˵�Ѫ��
        for(int i=0;i<12;i++){
        	if(i<9){
        		drawTank(505,i*40+15,g,Tank.TANK_TYPE_ENEMY,Tank.TANK_ORIENTATION_NORTH);
        		 int  currentsize= enemyTankVector.get(i).getBlood();        	
        		 drawBlock(535,i*40+15+5 , 10, 20, 9, currentsize, g, Color.RED);
        		 drawBlock(enemyTankVector.get(i).getX(),enemyTankVector.get(i).getY()-5 , 3, 4, 9, currentsize, g, Color.RED);
        	}else if(i==9){
        		drawTank(505,i*40+15,g,Tank.TANK_TYPE_US,Tank.TANK_ORIENTATION_NORTH);
        		drawBlock(535,i*40+15+5, 10, 20, MyTankData.maxBlood, MyTankData.currBlood, g, Color.RED);
        		drawBlock(mt.getX(), mt.getY()-5 , 3, 4,  MyTankData.maxBlood, MyTankData.currBlood, g, Color.RED);
        	}else if(i==10){
        		drawBlock(535, 9*40+15+5+25, 10, 20, MyTankData.maxSpeed, MyTankData.speed, g, Color.blue);
        	}else if(i==11){
        		drawBlock(535, 9*40+15+5+25+25, 10, 20, MyTankData.zdMaxSpeed, MyTankData.zdSpeed, g, Color.GREEN);
        	}
        }
        //������   �÷���
        g.setColor(Color.red);
        g.setFont(new Font("�����п�",Font.BOLD,20));
        g.drawString("��"+MyTankData.sceneNumber+"��",505, 470);
        g.setColor(Color.MAGENTA);
        g.drawString("�÷֣�"+MyTankData.totalScore,505, 490);
        
      
        
         //�ж�ͨ�أ� �����л��ؿ�����
        if(enemyTankVector!=null&&enemyTankVector.size()>0){
       	 if(hasDestroyAll(enemyTankVector)){
       		    MyTankData.sceneNumber++;//�ؿ�����1
       		    EnemyTankData.sceneNumber++;//�ؿ�����1
       		    EnemyTankData.passScene();
         	// �������������    ���Ӵ������Ƴ�������   
 		     if(this.isThreadAlive()){
 		    	  this.setThreadAlive(false);//���������������߳�
 		      }
 		     JFrame f2=FrameAndScene.getFrame(); //�����������Ӵ������Ƴ�
 		     if(f2!=null){
 		    	f2.remove(this);
 		     }
 		     
 		  	// ���л��ؿ����󴴽�,�������л��ؿ�������ӵ�������.  �����´������л��ؿ�����
       		 SwitchScene  newSwScene=new SwitchScene(0, 0, 500, 500);
       		 FrameAndScene.setSwScene(newSwScene);
       		 JFrame f=FrameAndScene.getFrame();
       		
       		 f.add(newSwScene); 
       		 new Thread(newSwScene).start();
       		 f.setVisible(true);
    		 f.setResizable(false);
    		 
       		 newSwScene.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					
					/*
					 * ���ʱ��   ���л��ؿ�������������Ӵ������Ƴ��л��ؿ�����
					 *          �����µ����������󣬲���������������ӵ�������.  �����´���������������
					 */
				   SwitchScene swsc=FrameAndScene.getSwScene();
					if(swsc!=null ){
						  if(swsc.isThreadAlive()){
							  swsc.setThreadAlive(false);
						  }
						 JFrame f= FrameAndScene.getFrame();
						 f.remove(swsc);
						 swsc=null; 
						 FrameAndScene.setSwScene(null);
					}
					MainScene nextMainScene= new MainScene();//�����µ�������
				    nextMainScene.setSize(500, 500);
					FrameAndScene.setMainScene(nextMainScene);//��������
					JFrame f=FrameAndScene.getFrame();
					f.add(nextMainScene); 
					   f.addKeyListener(nextMainScene);//�ҷ�̹���������� �����ӵ� ���� 
					new Thread(nextMainScene).start();
					f.setVisible(true);
					f.setResizable(false);
				}
       			 
		     });
       	 }
       }  
       //�ж��ҷ�̹���Ƿ����������������Ϸ����
        if(MyTankData.currBlood<=0){
        	 if(this.isThreadAlive()){
		    	  this.setThreadAlive(false);//���������������߳�
		      }
		     JFrame f2=FrameAndScene.getFrame(); //�����������Ӵ������Ƴ�
		     if(f2!=null){
		    	f2.remove(this);
		     }
        	  
		     EndScene  endScene=new EndScene(0, 0, 500, 500);
       		 FrameAndScene.setEndScene(endScene);
       		 JFrame f=FrameAndScene.getFrame();
       		 f.add(endScene); 
       		   new Thread(endScene).start();
       		   
       		    
	       	
       		 f.setVisible(true);
    		 f.setResizable(false);
    		 endScene.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					System.exit(0);
				}
		         
			   
		     });
		   
        }
	}
	
 
	/**
	 * ����̹��
	 * @param x
	 * @param y
	 * @param g
	 * @param type
	 * @param orientation
	 */
	public void drawTank(int x, int y, Graphics g,int type, int orientation ) {
		switch (type) {
		case Tank.TANK_TYPE_US:
			g.setColor(Color.GREEN);
			break;
		case Tank.TANK_TYPE_ENEMY:
			g.setColor(Color.YELLOW);
			break;
		}
		switch (orientation) {//̹�˳���
		case Tank.TANK_ORIENTATION_NORTH: //��
            g.fill3DRect(x, y, 5, 30, false);
            g.fill3DRect(x+15, y, 5, 30, false);
            g.fill3DRect(x+5, y+5, 10, 20, false);
            g.fillOval(x+5, y+10, 10, 10);
            g.drawLine(x+10, y+15, x+10, y-3);
            
			break;
		case Tank.TANK_ORIENTATION_WEST://��
            g.fill3DRect(x, y, 30, 5, false);
            g.fill3DRect(x, y+15, 30, 5, false);
            g.fill3DRect(x+5, y+5, 20, 10, false);
            g.fillOval(x+10, y+5, 10, 10);
            g.drawLine(x+15,y+10,x-3,y+10);
            
			break;
		case Tank.TANK_ORIENTATION_SOUTH://��
		    g.fill3DRect(x, y, 5, 30, false);
            g.fill3DRect(x+15, y, 5, 30, false);
            g.fill3DRect(x+5, y+5, 10, 20, false);
            g.fillOval(x+5, y+10, 10, 10);
            g.drawLine(x+10,y+15,x+10,y+33);
			break;
		case Tank.TANK_ORIENTATION_EAST://��
	        g.fill3DRect(x, y, 30, 5, false);
            g.fill3DRect(x, y+15, 30, 5, false);
            g.fill3DRect(x+5, y+5, 20, 10, false);
            g.fillOval(x+10, y+5, 10, 10);
            g.drawLine(x+15,y+10,x+33,y+10);
			break;
		}
	}
	
	
	
	/**
	 * �� ��״��    Ѫ  �� ��
	 * @param x
	 * @param y
	 * @param totalsize
	 * @param currentsize
	 * @param g
	 */

	public void drawBlock(int x,int y,int width,int height,int totalsize,int currentsize,Graphics g,Color c){
		g.setColor(c);
		g.drawRect(x, y, totalsize*width, height);
		g.fillRect(x, y, currentsize*width,height);
	}
	 
	/**
	 * ��̹��ͷ�ϵ�Ѫ��
	 */
	
	public void drawlife() {}
	
	/**
	 * ���ϰ���
	 * @param x
	 * @param y
	 * @param materical
	 * @param g
	 */
    public void drawObst(int x,int y,int materical,Graphics g){
    	switch(materical){
    	case 0:
    		g.setColor(new Color(Integer.parseInt("f27116",16)));
    		break;
    	case 1:
    		g.setColor(new Color(Integer.parseInt("ffffff",16)));
    		break;
    	}
    	g.fill3DRect(x, y, 20, 20, false);
    }
	
	/**
	 * �����Ƿ����
	 * @param zd
	 * @param tank
	 * @return
	 */
	private boolean isHit(Bullet zd, Tank tank) {//�����ӵ��Ƿ����̹�� 
	     switch(tank.getOrientation()){
	     case Tank.TANK_ORIENTATION_NORTH:
	     case Tank.TANK_ORIENTATION_SOUTH:
	    	 if(zd.getX()>=tank.getX()&&zd.getX()<=tank.getX()+20&&zd.getY()>=tank.getY()&&zd.getY()<=tank.getY()+30){
	    		 return true;
	    	 }else{
	    		 return false;
	    	 }
	     case Tank.TANK_ORIENTATION_WEST:
	     case Tank.TANK_ORIENTATION_EAST:
	    	 if(zd.getX()>=tank.getX()&&zd.getX()<=tank.getX()+30&&zd.getY()>=tank.getY()&&zd.getY()<=tank.getY()+20){
	    		 return true;
	    	 }else{
	    		 return false;
	    	 }
	     }
		return false;
	}
	
	/**
	 * �ж��Ƿ������˵з�����̹��  
	 * @param dtanks
	 * @return
	 */
	public boolean hasDestroyAll(Vector<EnemyTank> dtanks){
		for(EnemyTank tank:dtanks){
			if(tank.getBlood()>0){
				return false;
			}
		}
		return true;
	} 
	/**
	 * ���̼߳��̼���
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		 if(e.getKeyCode()==KeyEvent.VK_W){ //���ƶ�
			  mt.setOrientation(Tank.TANK_ORIENTATION_NORTH);
			  
			 if(mt.getY()>0&&mt.isCanNorthMove()){
				 mt.northmove();
				    mt.setCanWestMove(true);
		    	    mt.setCanNorthMove(true);
		    	    mt.setCanSouthMove(true);
		    	    mt.setCanEastMove(true);
				for(Obstruction obst :obstVector){
					if(obst.getBlood()>0){
						 if(mt.getX()>=obst.getX()&&mt.getX()<=obst.getX()+20&&mt.getY()>=obst.getY()&&mt.getY()<=obst.getY()+20){
						    	mt.setCanNorthMove(false);
					    	 break;
					      }
						 else if(mt.getX()+20>=obst.getX()&&mt.getX()+20<=obst.getX()+20&&mt.getY()>=obst.getY()&&mt.getY()<=obst.getY()+20){
							  mt.setCanNorthMove(false);
					    	 break;
					      }
					}
				}
			 }
		 }
		 else if(e.getKeyCode()==KeyEvent.VK_A){//���ƶ�
			  mt.setOrientation(Tank.TANK_ORIENTATION_WEST);
			 if(mt.getX()>0&&mt.isCanWestMove()){
				    mt.westmove();
				    mt.setCanWestMove(true);
		    	    mt.setCanNorthMove(true);
		    	    mt.setCanSouthMove(true);
		    	    mt.setCanEastMove(true);
				  for(Obstruction obst :obstVector){
						if(obst.getBlood()>0){
							 if(mt.getX()>=obst.getX()&&mt.getX()<=obst.getX()+20&&mt.getY()>=obst.getY()&&mt.getY()<=obst.getY()+20){
						    	    mt.setCanWestMove(false);
						    	   
						    	 break;
						      }
							 else if(mt.getX()>=obst.getX()&&mt.getX()<=obst.getX()+20&&mt.getY()+20>=obst.getY()&&mt.getY()+20<=obst.getY()+20){
								 
					    	    mt.setCanWestMove(false);
					    	   
						    	 break;
						      }  
						}
					}
			 }
		 }
		 else if(e.getKeyCode()==KeyEvent.VK_S){//���ƶ�
			  mt.setOrientation(Tank.TANK_ORIENTATION_SOUTH);
			 if(mt.getY()<470&&mt.isCanSouthMove()){
				     mt.southmove();
				    mt.setCanWestMove(true);
		    	    mt.setCanNorthMove(true);
		    	    mt.setCanSouthMove(true);
		    	    mt.setCanEastMove(true);
				  for(Obstruction obst :obstVector){
						if(obst.getBlood()>0){
							if(mt.getX()>=obst.getX()&&mt.getX()<=obst.getX()+20&&mt.getY()+30>=obst.getY()&&mt.getY()+30<=obst.getY()+20){
						    	    mt.setCanSouthMove(false);
						    	 break;
						      }
							else if(mt.getX()+20>=obst.getX()&&mt.getX()+20<=obst.getX()+20&&mt.getY()+30>=obst.getY()&&mt.getY()+30<=obst.getY()+20){
					    	    mt.setCanSouthMove(false);
						    	 break;
						      }
						}
					}
			 }
		 }
		 else  if(e.getKeyCode()==KeyEvent.VK_D){//���ƶ�
			  mt.setOrientation(Tank.TANK_ORIENTATION_EAST);
			 if(mt.getX()<470&&mt.isCanEastMove()){
				     mt.eastmove();
				     mt.setCanWestMove(true);
		    	    mt.setCanNorthMove(true);
		    	    mt.setCanSouthMove(true);
		    	    mt.setCanEastMove(true);
				 for(Obstruction obst :obstVector){
						if(obst.getBlood()>0){
							 if(mt.getX()+30>=obst.getX()&&mt.getX()+30<=obst.getX()+20&&mt.getY()>=obst.getY()&&mt.getY()<=obst.getY()+20){
							     
						    	    mt.setCanEastMove(false);
						    	 break;
						      }
							 else if(mt.getX()+30>=obst.getX()&&mt.getX()+30<=obst.getX()+20&&mt.getY()+20>=obst.getY()&&mt.getY()+20<=obst.getY()+20){
						 
					    	    mt.setCanEastMove(false);
						    	 break;
						      }
						}
					}
			 }
		 }
		 if(e.getKeyCode()==KeyEvent.VK_J){
			 if(mt.getZdVector().size()<8){
				  mt.fszd();// �����ӵ�  ֻ����ͬʱ��8���ӵ��ڽ����д���
			 }
		 }
		 this.repaint();//�ҷ�̹��ֻҪ�������ӵ����ƶ���λ�þͻ�ǿ���ػ�
	}

	 /**
	  * ���߳� run ����
	  */
	@Override
	public void run() {//�����ÿ��100�����ػ�һ�� ,����һ��Ҫ���߳���Ϣһ�£��������������ڴ�й¶
		while(threadAlive){
			 try {
				Thread.sleep(100);
				//�ж��ҷ����ез�
				for(int i=0;i<mt.getZdVector().size();i++){//���ҷ�̹�˵�ÿһ���ӵ������ж�  �ҷ�̹���ӵ����ез�
					if(mt.getZdVector().get(i).isThreadAlive()){
						for(int j=0;j<enemyTankVector.size();j++){
							 if(enemyTankVector.get(j).getBlood()>0){
								if( isHit(mt.getZdVector().get(i),enemyTankVector.get(j))){//����Ƿ�����̹��
									
									   mt.getZdVector().get(i).setThreadAlive(false);//�ҷ��ӵ��Ĵ��������Ϊfalse
									   enemyTankVector.get(j).reduceBlood();
									   zjeffectVector.add(new ZJeffect(enemyTankVector.get(j).getX(),enemyTankVector.get(j).getY(),zjimg));
									   MyTankData.totalScore+=5;
								}
							 }
						}
						
					}
				}
				//�жϵз������ҷ�
				for(int i=0;i<enemyTankVector.size();i++){//ѭ�������з������е�̹��
					 EnemyTank etank=  enemyTankVector.get(i);
					 for(int j=0;j<etank.getZdVector().size();j++){//�����з�ÿһ��̹�˵�ÿһ���ӵ�
						  if(MyTankData.currBlood>0){
							  if(isHit(etank.getZdVector().get(j),mt)){
								     etank.getZdVector().get(j).setThreadAlive(false);
							 	    zjeffectVector.add(new ZJeffect(mt.getX(),mt.getY(),zjimg));//����
								     MyTankData.currBlood--;//��Ѫ
									 
							  }
						  }
					 }
				}
				
				//�ж��ӵ������ϰ���
				for(int i=0;i<obstVector.size();i++){
				  Obstruction obst=	obstVector.get(i);
				   for(EnemyTank tanks :enemyTankVector){
					    if(tanks.getBlood()>0){
					    	for(Bullet zd  :tanks.getZdVector()){
					    		 if(zd.isThreadAlive()&&obst.getBlood()>0){
					    			 
					    			 if(obst.isBitten(zd)){
					    				  if(obst.getMaterical()==0){
					    					  zd.setThreadAlive(false);
											  obst.reduceBlood();
					    				  }else if(obst.getMaterical()==1){
					    					  zd.setThreadAlive(false);
					    				  }
					    			 }
					    		 }
					    	}
					    }
				   }
				  for(Bullet zd :mt.getZdVector()){//�ж��ӵ������ϰ���
					  if(zd.isThreadAlive()&&obst.getBlood()>0){
					    if(obst.isBitten(zd)){
						   if(obst.getMaterical()==0){
							   zd.setThreadAlive(false);
							   obst.reduceBlood();
						   }else if(obst.getMaterical()==1){
							   zd.setThreadAlive(false);
						   }
						  
					      }
				        }
					  }
				}
				
				
				
				
			  repaint();
			} catch (Exception e) {
				
			}
		}
		
	}
    

	
	
	public boolean isThreadAlive() {
		return threadAlive;
	}
	 
	public int getEnemyTankCount() {
		return enemyTankCount;
	}
	public void setEnemyTankCount(int enemyTankCount) {
		this.enemyTankCount = enemyTankCount;
	}
	public Vector<EnemyTank> getEnemyTankVector() {
		return enemyTankVector;
	}
	public void setEnemyTankVector(Vector<EnemyTank> enemyTankVector) {
		this.enemyTankVector = enemyTankVector;
	}
	public Vector<ZJeffect> getZjeffectVector() {
		return zjeffectVector;
	}
	public void setZjeffectVector(Vector<ZJeffect> zjeffectVector) {
		this.zjeffectVector = zjeffectVector;
	}
	public String getZjimg() {
		return zjimg;
	}
	public void setZjimg(String zjimg) {
		this.zjimg = zjimg;
	}
	public void setThreadAlive(boolean threadAlive) {
		this.threadAlive = threadAlive;
	}

	public MyTank getMt() {
		return mt;
	}

	public void setMt(MyTank mt) {
		this.mt = mt;
	}

	public Vector<Obstruction> getObstVector() {
		return obstVector;
	}

	public void setObstVector(Vector<Obstruction> obstVector) {
		this.obstVector = obstVector;
	}
	
    
}
