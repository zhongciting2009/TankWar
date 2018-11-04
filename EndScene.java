package tankwar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


/**
 * ��������
 */
public class EndScene extends JPanel  implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int width;
	private int height;
	private int times=0;
	private boolean threadAlive=true;
	public EndScene(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void run() {
 		while(threadAlive){
 			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
			}
 			 repaint();
 		}
	}
 
 
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		int sceneNumber=MyTankData.sceneNumber;
		int totalScore=MyTankData.totalScore;
		times++;
		if(times%3==0){
			g.setColor(Color.GREEN);
			Font font=new Font("�����п�",Font.BOLD,40) ;
			g.setFont(font);
			g.drawString("Game Over!!", 100, 140);
			g.drawString("�����������"+sceneNumber+" ��",100,220);
			g.drawString("��   �֣�"+totalScore+"��",100,300);
			 
		}else if(times%3==1){
			g.setColor(Color.cyan);
			Font font=new Font("�����п�",Font.BOLD,40) ;
			g.setFont(font);
			g.drawString("Game Over!!", 100, 140);
			g.drawString("�����������"+sceneNumber+" ��",100,220);
			g.drawString("��   �֣�"+totalScore+"��",100,300);
		}else{
			g.setColor(Color.pink);
			Font font=new Font("�����п�",Font.BOLD,40) ;
			g.setFont(font);
			g.drawString("Game Over!!", 100, 140);
			g.drawString("�����������"+sceneNumber+" ��",100,220);
			g.drawString("��   �֣�"+totalScore+"��",100,300);
		}
		g.setColor(Color.white);
		g.setFont(new Font("�����п�",Font.BOLD,25));
		
	}

	public EndScene() {
		super();
	}
	public boolean isThreadAlive() {
		return threadAlive;
	}
	public void setThreadAlive(boolean threadAlive) {
		this.threadAlive = threadAlive;
	}

}
