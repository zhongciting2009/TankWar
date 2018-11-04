package tankwar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * 坦克大战  入口 类
 */
public class TankWar implements ActionListener {
	private JMenuBar menubar;
	private JMenu menu1, menu2;
	private JMenuItem item1, item2, item3, item4;
	private JMenuItem jxyouxiitem;
	private JMenuItem bctuichuitem;
    private JFrame f;
    
	public static void main(String[] args) {
		TankWar war = new TankWar();
		war.startGame();
	}
	void startGame() {
		 f = new MyJFrame(AboutGame.GAME_NAME, 655, 554, 400, 100);
		this.menubar = new JMenuBar();
		this.menu1 = new JMenu("游戏(G)");
		this.menu1.setMnemonic('G');
		this.menu2 = new JMenu("帮助(H)");
		this.menu2.setMnemonic('H');
		this.item1 = new JMenuItem("新游戏");
		this.item1.addActionListener(this);
		this.item1.setActionCommand("new game");
		this.jxyouxiitem = new JMenuItem("继续游戏");
		this.jxyouxiitem.addActionListener(this);
		this.jxyouxiitem.setActionCommand("coutinue game");
		this.bctuichuitem = new JMenuItem("保存退出");
		this.bctuichuitem.addActionListener(this);
		this.bctuichuitem.setActionCommand("save and exit");
		this.item2 = new JMenuItem("退出");
		this.item2.addActionListener(this);
		this.item2.setActionCommand("exit game");
		this.item3 = new JMenuItem("操作说明");
		this.item3.addActionListener(this);
		this.item3.setActionCommand("operating instructions");
		this.item4 = new JMenuItem("关于游戏");
		this.item4.addActionListener(this);
		this.item4.setActionCommand("about game");
		this.menu1.add(this.item1);
		this.menu1.add(this.jxyouxiitem);
		this.menu1.add(this.bctuichuitem);
		this.menu1.add(this.item2);
		this.menu2.add(this.item3);
		this.menubar.add(this.menu1);
		this.menubar.add(this.menu2);
		//创建开始场景    保存   开启线程  开启事件监听
		StartScene stScene = new StartScene(0, 0, 500, 500);
		FrameAndScene.setStScene(stScene);//保存开始场景
		FrameAndScene.setFrame(f);//保存窗体
		new Thread(stScene).start();
	    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				 int op=JOptionPane.showConfirmDialog(f, "确定退出吗", "退出游戏", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				 if (op == JOptionPane.YES_OPTION){
						System.exit(0);
			     }
			}
		});
		f.setJMenuBar(this.menubar);
		f.add(stScene);
		stScene.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newGame();
			}
		});
		f.setVisible(true);
		f.setResizable(false);
	}
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "new game":
		   newGame();
		   break;
		case "coutinue game":
			continueGame();
			break;
        case "exit game":
        	//System.exit(0);
        	exitGame();
			break;
        case "save and exit":
        	saveAndExit();
        	break;
        case "operating instructions":
        	JOptionPane.showMessageDialog(FrameAndScene.getFrame(), AboutGame.GAME_NAME+" 操作说明\nW:向上移动\nS:向下移动\nA:向左移动\nD:" +
        			"向右移动\nJ:发射子弹\n");
        	break;
		}
	}
	void newGame(){
		 StartScene  stsc=FrameAndScene.getStScene();
			if(stsc!=null ){
				  if(stsc.isThreadAlive()){
					  stsc.setThreadAlive(false);
				  }
				 JFrame f= FrameAndScene.getFrame();
				 f.remove(stsc);
				 stsc=null; 
				 FrameAndScene.setSwScene(null);
			}
		 MainScene msc = FrameAndScene.getMainScene();
		        if (msc != null) {
		          if (msc.isThreadAlive()) {
		            msc.setThreadAlive(false);
		          }
		          JFrame f = FrameAndScene.getFrame();
		          f.remove(msc);
		          msc = null;
		          FrameAndScene.setMainScene(null);
		   }	
			    
			        MyTankData.initData();
			        EnemyTankData.initData();
			        MainScene mainScene = new MainScene();
			        FrameAndScene.setMainScene(mainScene);
			        mainScene.setSize(500, 500);
			        JFrame f = FrameAndScene.getFrame();
		           f.add(mainScene);
		           f.addKeyListener(mainScene);
		           
		        
		        f.setVisible(true);
		        f.setResizable(false);
	}
	
	void continueGame(){
		
	}
	
	void saveAndExit(){
		
	}
	void exitGame(){
		 int op=JOptionPane.showConfirmDialog(f, "确定退出吗", "退出游戏", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		 if (op == JOptionPane.YES_OPTION){
				System.exit(0);
	     } 
	}
}