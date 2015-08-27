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
	private int[] screenSize;
	private Character mainCharacter;
	private double timePerFrame = 0.033;
	private double maxSpeedX = 50;
	private double maxSpeedY = 50;
	private double resistanceX = maxSpeedX / 30;
	private double gravity = 9.82;
	private String mapName = "testmap.txt";
	private int[] mapSizeInTiles;
	private int[][] map;
	private int pixelPerTile;
	private BufferedImage tileImg0;
	private BufferedImage tileImg1;
	
	
	public GameLogic() {

		screenSize = new int[2];
		screenSize[0] = 1280;
		screenSize[1] = 720;
		loadMap(mapName);
		mainCharacter = new Character(pixelPerTile * 3, pixelPerTile * 7);
		addKeyListener(new TAdapter());
		setFocusable(true);
		//playTheGame();
	}
	
	
	
	public void paint(Graphics g){
		long startTime = System.currentTimeMillis();
		Graphics2D gb = (Graphics2D)g;
		
		
		calculatePlayerMovement();
		drawMap(gb);
		double[] charPos = mainCharacter.getPosition();
		BufferedImage charImg = mainCharacter.getFrameImage();
		gb.drawImage(charImg, (int)(charPos[0] - (charImg.getWidth() / 2)) , (int)(charPos[1] - charImg.getHeight()), charImg.getWidth(), charImg.getHeight(), null);
		
		long endTime;
		while(((endTime = System.currentTimeMillis()) - startTime) < 33){
			// wait
		}
		System.out.println("" + (endTime - startTime));
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
		mapSizeInTiles = new int[2];
		File file = new File(name);
		try {
			Scanner s = new Scanner(file);
			String tiles = s.nextLine();
			String[] mapTiles = tiles.split("[\\s*]");
			mapSizeInTiles[0] = Integer.parseInt(mapTiles[0]);
			mapSizeInTiles[1] = Integer.parseInt(mapTiles[1]);
			map = new int[mapSizeInTiles[0]][mapSizeInTiles[1]];
			pixelPerTile = Integer.parseInt(s.nextLine());
			for (int y = 0; y < mapSizeInTiles[1]; y++) {
				String line = s.nextLine();
				System.out.println(line);
				String[] a = line.split("[\\s*]");
				for (int x = 0; x < mapSizeInTiles[0]; x++) {
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
		for(int x = 0; x < mapSizeInTiles[0]; x++){
			for(int y = 0; y < mapSizeInTiles[1]; y++){
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
		double[] charAcc = mainCharacter.getAcceleration();
		double[] charPos = mainCharacter.getPosition();
		double[] charVel = mainCharacter.getVelocity();
		
		charVel[0] += (charAcc[0] * maxSpeedX) * timePerFrame;
		charVel[1] += (charAcc[1] * maxSpeedY) * timePerFrame;
		
		if(charVel[0] > 0){
			charVel[0] -= resistanceX;
			if(charVel[0] < 0) charVel[0] = 0;
		} else if(charVel[0] < 0){
			charVel[0] += resistanceX;
			if(charVel[0] > 0) charVel[0] = 0;
		} else {
			// standing still
		}
		if(charVel[0] > maxSpeedX) charVel[0] = maxSpeedX;
		if(charVel[0] < -maxSpeedX) charVel[0] = -maxSpeedX;
		if(charVel[1] > maxSpeedY) charVel[1] = maxSpeedY;
		double xPosToTest = charPos[0] + (charVel[0] * timePerFrame);
		double yPosToTest = charPos[1] + (charVel[1] * timePerFrame);
		
		// collision with map
		int currentTileX = (int)(xPosToTest / pixelPerTile);
		int currentTileY = (int)(yPosToTest / pixelPerTile);
		switch(map[currentTileX][currentTileY]){
			case 0:
				charPos[0] = xPosToTest;
				charPos[1] = yPosToTest;
				break;
			case 1:
				// don't go there
				break;
		}
		charVel[1] += gravity; // Use when we have a map and real movement.
		mainCharacter.setPosition(charPos[0], charPos[1]);
		mainCharacter.setVelocity(charVel[0], charVel[1]);
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
				mainCharacter.setAcceleration(-10.0, 0);
				mainCharacter.setLeft(true);
			}
			if(key == KeyEvent.VK_RIGHT) {
				mainCharacter.setAcceleration(10.0, 0);
				mainCharacter.setRight(true);
			}
			
			if(key == KeyEvent.VK_SPACE) {
				System.out.println("Space Pressed");
				if(!mainCharacter.isJumping()){
					mainCharacter.setAcceleration(0, -10);
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
				mainCharacter.setAcceleration(0, 0);
				mainCharacter.setLeft(false);
			}
			if(key == KeyEvent.VK_RIGHT) {
				mainCharacter.setAcceleration(0, 0);
				mainCharacter.setRight(false);
			}
			if(key == KeyEvent.VK_SPACE) {
				mainCharacter.jump(false);
			}
		}
	}
}
