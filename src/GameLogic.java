import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
		addKeyListener(new TAdapter());
		setFocusable(true);
		//playTheGame();
	}
	
	public void paint(Graphics g){
		Graphics2D gb = (Graphics2D)g;
		
		double[] charPos = mainCharacter.getPosition();
		double[] charVel = mainCharacter.getVelocity();
		charPos[0] += charVel[0];
		charPos[1] += charVel[1];
		BufferedImage charImg = mainCharacter.getFrameImage();
		gb.drawImage(charImg, (int)charPos[0], (int)charPos[1], charImg.getWidth(), charImg.getHeight(), null);
		repaint();
	}
	
	private class TAdapter extends KeyAdapter {

		/**
		 * Registers if a key has been pressed and which
		 * key it is.
		 * 
		 * @param e	the current key being pressed
		 */
		public void keyPressed(KeyEvent e) {
			
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_LEFT) {
				System.out.println("Left Pressed");
				mainCharacter.addAcceleration(-1, 0);
				mainCharacter.setLeft(true);
			}
			if(key == KeyEvent.VK_RIGHT) {
				System.out.println("Right Pressed");
				mainCharacter.addAcceleration(1, 0);
				mainCharacter.setRight(true);
			}
			
			if(key == KeyEvent.VK_SPACE && !mainCharacter.isJumping()) {
				System.out.println("Space Pressed");
				mainCharacter.addAcceleration(0, -1);
				mainCharacter.jump();
			}
		
			if (key == KeyEvent.VK_SHIFT) {
				//mainCharacter.changeColor();
			}
		}
		
		/**
		 * Registers if a key has been released and
		 * which key it is. 
		 * 
		 * @param e	the current key being pressed
		 */
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_LEFT) {
				mainCharacter.setLeft(false);
			}
			if(key == KeyEvent.VK_RIGHT) {
				mainCharacter.setRight(false);
			}
		}
	}
}
