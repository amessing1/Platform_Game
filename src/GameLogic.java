import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameLogic extends JPanel{

	private JFrame window;
	private JPanel panel;
	private int[] screenSize;
	private Character mainCharacter;
	
	
	public GameLogic() {

		mainCharacter = new Character(100, 100);
		screenSize = new int[2];
		screenSize[0] = 1200;
		screenSize[1] = 900;
		//playTheGame();
	}
	
	public void paint(Graphics g){
		Graphics2D gb = (Graphics2D)g;
		
		double[] charPos = mainCharacter.getPosition();
		BufferedImage charImg = mainCharacter.getFrameImage();
		gb.drawImage(charImg, 0, 0, charImg.getWidth(), charImg.getHeight(), null);
		repaint();
	}
	
	
}
