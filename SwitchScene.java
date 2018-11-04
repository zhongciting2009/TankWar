package tankwar;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


/**
 *  ������һ�ؿ� ����
 * @author jelly
 *
 */
public class SwitchScene extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
    private int x;
    private int y;
    private int width;
    private int height;
    private int times=0;
    private boolean threadAlive=true;
     
	public boolean isThreadAlive() {
		return threadAlive;
	}
	public void setThreadAlive(boolean threadAlive) {
		this.threadAlive = threadAlive;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		Font font=new Font("�����п�",Font.BOLD,40) ;
		int sceneNumber=MyTankData.sceneNumber;
		int totalScore=MyTankData.totalScore;
		times++;
		if(times%3==0){
			g.setColor(Color.GREEN);
			g.setFont(font);
			g.drawString("�����"+sceneNumber+"��", 140, 200);
			g.drawString("�ܷ֣�"+totalScore,140,280);
		}else if(times%3==1){
			g.setColor(Color.cyan);
			g.setFont(font);
			g.drawString("�����"+sceneNumber+"��", 140, 200);
			g.drawString("�ܷ֣�"+totalScore,140,280);
		}else{
			g.setColor(Color.pink);
			g.setFont(font);
			g.drawString("�����"+sceneNumber+"��", 140, 200);
			g.drawString("�ܷ֣�"+totalScore,140,280);
		}
		g.setColor(Color.white);
		g.setFont(new Font("�����п�",Font.BOLD,25));
		
	}
	
    public SwitchScene(){
    	
    }
    
    public SwitchScene(int x,int y,int width,int height){
    	this.x=x;
    	this.y=y;
    	this.width=width;
    	this.height=height;
    }
    
    @Override
	public void run() {
		while(threadAlive){
 			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
 			 repaint();
 		}
	}
}