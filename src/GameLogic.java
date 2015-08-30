import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameLogic extends JPanel{

	private JFrame window;
	private JPanel panel;
	private Vector2D screenSize;
	private Character mainCharacter;
	private double timePerFrame = 0.033;
	private double maxSpeedX = 2;
	private double maxSpeedY = 2;
	private double resistanceX = maxSpeedX / 30;
	private Vector2D gravity;
	private String mapName = "testmap.txt";
	private Vector2D mapSizeInTiles;
	private int[][] map;
	private int pixelPerTile;
	private BufferedImage tileImg0;
	private BufferedImage tileImg1;
	
	
	public GameLogic() {

		screenSize = new Vector2D(1280, 720);
		gravity = new Vector2D(0, 1);
		loadMap(mapName);
		mainCharacter = new Character(new Vector2D(200, 200));
		addKeyListener(new TAdapter());
		setFocusable(true);
		//playTheGame();
	}
	
	
	
	public void paint(Graphics g){
		long startTime = System.currentTimeMillis();
		Graphics2D gb = (Graphics2D)g;
		
		
		calculatePlayerMovement();
		drawMap(gb);
		Vector2D charPos = mainCharacter.getPosition();
		BufferedImage charImg = mainCharacter.getFrameImage();
		gb.drawImage(charImg, (int)(charPos.x - (charImg.getWidth() / 2)) , (int)(charPos.y - charImg.getHeight()), charImg.getWidth(), charImg.getHeight(), null);
		
		long endTime;
		while(((endTime = System.currentTimeMillis()) - startTime) < 3000){
			// wait
		}
		//System.out.println("" + (endTime - startTime));
		System.out.println("" + charPos.x + ", " + charPos.y);
		repaint();
	}
	
	private void loadMap(String name){
		try{
			tileImg0 = ImageIO.read(new File("tile0.png"));
		} catch (Exception e){
				System.out.println("Failed to read tile0.");
		}
		try{
			tileImg1 = ImageIO.read(new File("tile1.png"));
		} catch (Exception e){
				System.out.println("Failed to read tile1.");
		}
		
		File file = new File(name);
		try {
			Scanner s = new Scanner(file);
			String tiles = s.nextLine();
			String[] mapTiles = tiles.split("[\\s*]");
			mapSizeInTiles = new Vector2D(Integer.parseInt(mapTiles[0]), Integer.parseInt(mapTiles[1]));
			map = new int[(int)mapSizeInTiles.x][(int)mapSizeInTiles.y];
			pixelPerTile = Integer.parseInt(s.nextLine());
			for (int y = 0; y < mapSizeInTiles.y; y++) {
				String line = s.nextLine();
				System.out.println(line);
				String[] a = line.split("[\\s*]");
				for (int x = 0; x < mapSizeInTiles.x; x++) {
					map[x][y] = Integer.parseInt(a[x]);
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void drawMap(Graphics2D gb){
		BufferedImage tileImg = null;
		for(int x = 0; x < mapSizeInTiles.x; x++){
			for(int y = 0; y < mapSizeInTiles.y; y++){
				switch(map[x][y]){
				case 0:
					tileImg = tileImg0;
					break;
				case 1:
					tileImg = tileImg1;
					break;
				default:
						break;
				}
				gb.drawImage(tileImg, x * pixelPerTile, y * pixelPerTile, tileImg.getWidth(), tileImg.getHeight(), null);
			}
		}
	}
	
	private void calculatePlayerMovement(){
		Vector2D charAcc = mainCharacter.getAcceleration();
		Vector2D charPos = mainCharacter.getPosition();
		Vector2D charVel = mainCharacter.getVelocity();
		
		Vector2D temp = charAcc.vectorMulti(maxSpeedX);
		charVel = charVel.vectorAdd(temp.vectorMulti(timePerFrame));
		
		if(charVel.x > 0){
			charVel.x -= resistanceX;
			if(charVel.x < 0) charVel.x = 0;
		} else if(charVel.x < 0){
			charVel.x += resistanceX;
			if(charVel.x > 0) charVel.x = 0;
		} else {
			// standing still
		}
		if(charVel.x > maxSpeedX) charVel.x = maxSpeedX;
		if(charVel.x < -maxSpeedX) charVel.x = -maxSpeedX;
		if(charVel.y > maxSpeedY) charVel.y = maxSpeedY;
		Vector2D PosToTest = charPos.vectorAdd((charVel.vectorMulti(timePerFrame)));
		
		// collision with map
		Vector2D currentTile = (PosToTest.vectorDivide(pixelPerTile));
		
		switch(map[(int)currentTile.x][(int)currentTile.y]){
			case 0:
				charPos = PosToTest;
				break;
			case 1:
				// don't go there
				
				break;
		}
		charVel = charVel.vectorAdd(gravity);
		mainCharacter.setPosition(charPos);
		mainCharacter.setVelocity(charVel);
		return;
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
				mainCharacter.setAcceleration(new Vector2D(-1.0, 0));
				mainCharacter.setLeft(true);
			}
			if(key == KeyEvent.VK_RIGHT) {
				mainCharacter.setAcceleration(new Vector2D(1.0, 0));
				mainCharacter.setRight(true);
			}
			
			if(key == KeyEvent.VK_SPACE) {
				System.out.println("Space Pressed");
				if(!mainCharacter.isJumping()){
					mainCharacter.setAcceleration(new Vector2D(0, -1));
					mainCharacter.jump(true);
				}
				
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
				mainCharacter.setAcceleration(new Vector2D(0, 0));
				mainCharacter.setLeft(false);
			}
			if(key == KeyEvent.VK_RIGHT) {
				mainCharacter.setAcceleration(new Vector2D(0, 0));
				mainCharacter.setRight(false);
			}
			if(key == KeyEvent.VK_SPACE) {
				mainCharacter.jump(false);
			}
		}
	}
}
