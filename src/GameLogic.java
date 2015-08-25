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
	private double timePerFrame = 0.033;
	private double speed = 4;
	
	
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
		long startTime = System.currentTimeMillis();
		Graphics2D gb = (Graphics2D)g;
		
		double[] charAcc = mainCharacter.getAcceleration();
		double[] charPos = mainCharacter.getPosition();
		double[] charVel = mainCharacter.getVelocity();
		double accAngle;
		if(charAcc[0] != 0){
			accAngle = Math.toDegrees(Math.atan(charAcc[1]/charAcc[0]));
		} else {
			accAngle = charAcc[1];
		}
		mainCharacter.setAcceleration(Math.cos(accAngle), Math.sin(accAngle));
		charPos[0] += (0.5 * Math.cos(accAngle) * timePerFrame * timePerFrame) + (charVel[0] * timePerFrame);
		charPos[1] += (0.5 * Math.sin(accAngle) * timePerFrame * timePerFrame) + (charVel[1] * timePerFrame);
		mainCharacter.setPosition(charPos[0], charPos[1]);
		mainCharacter.setVelocity((((Math.cos(accAngle) * speed) * timePerFrame) + charVel[0]), ((Math.sin(accAngle) * timePerFrame) + charVel[1]));
		
		BufferedImage charImg = mainCharacter.getFrameImage();
		gb.drawImage(charImg, (int)charPos[0], (int)charPos[1], charImg.getWidth(), charImg.getHeight(), null);
		
		long endTime;
		while(((endTime = System.currentTimeMillis()) - startTime) < 33){
			// wait
		}
		System.out.println("" + (endTime - startTime));
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
				mainCharacter.setAcceleration(-1.0, 0);
				mainCharacter.setLeft(true);
			}
			if(key == KeyEvent.VK_RIGHT) {
				mainCharacter.setAcceleration(1.0, 0);
				mainCharacter.setRight(true);
			}
			
			if(key == KeyEvent.VK_SPACE && !mainCharacter.isJumping()) {
				System.out.println("Space Pressed");
				mainCharacter.setAcceleration(0, -1);
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
				mainCharacter.setAcceleration(0, 0);
				mainCharacter.setLeft(false);
			}
			if(key == KeyEvent.VK_RIGHT) {
				mainCharacter.setAcceleration(0, 0);
				mainCharacter.setRight(false);
			}
		}
	}
}
