package tankwar;


import javax.swing.JFrame;

 

/*
����������֮�佻������
 */
public class FrameAndScene {
    private static StartScene stScene;//��ʼ����
    private static SwitchScene  swScene;//�л��ؿ�����
    private static MainScene  mainScene;//��Ϸ������
    private static  EndScene endScene;
    private static JFrame frame;//������
   //  private static  MusicPlayer startMP,mainMP,endMP;
    
	public static StartScene getStScene() {
		return stScene;
	}
	public static void setStScene(StartScene stScene) {
		FrameAndScene.stScene = stScene;
	}
 
	public static SwitchScene getSwScene() {
		return swScene;
	}
	public static void setSwScene(SwitchScene swScene) {
		FrameAndScene.swScene = swScene;
	}
	public static MainScene getMainScene() {
		return mainScene;
	}
	public static void setMainScene(MainScene mainScene) {
		FrameAndScene.mainScene = mainScene;
	}
	public static JFrame getFrame() {
		return frame;
	}
	public static void setFrame(JFrame frame) {
		FrameAndScene.frame = frame;
	}
	public static EndScene getEndScene() {
		return endScene;
	}
	public static void setEndScene(EndScene endScene) {
		FrameAndScene.endScene = endScene;
	}
	 
	 
	 
 
	 
    
}
