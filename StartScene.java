package tankwar;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 游戏开始 场景
 */
public class StartScene extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L; 
	private int x;
	private int y;
	private int width;
	private int height;
	
	private boolean threadAlive=true;  //线程存活
	public boolean isThreadAlive() {
		return threadAlive;
	}
	public void setThreadAlive(boolean threadAlive) {
		this.threadAlive = threadAlive;
	}
    public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			Font font=new Font("华文行楷",Font.BOLD,40) ;
			g.setFont(font);
			g.drawString("坦克大战", 80, 200);
	}
	public  StartScene(){
		super();
	}
    public  StartScene(int x,int y,int width,int height){
    	super();
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		 
	}
	public void run() {
 		while(threadAlive){
 			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
			}
 			 repaint();
 		}
	}
}
