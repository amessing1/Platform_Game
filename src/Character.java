import java.awt.image.*;
import java.io.File;

import javax.imageio.*;

public class Character {
	
	private double[] position;
	private double[] velVector; 
	private BufferedImage frameImage;
	private Animation[] animations;
	private int health;
	private int mana;
	private Item[] inventory;
	private Skill[] skills;
	
	public Character(double x, double y){
		position = new double[2];
		position[0] = x;
		position[1] = y;
		velVector = new double[2];
		velVector[0] = 0;	// x-vector
		velVector[1] = 0;	// y-vector
		health = 100;
		mana = 100;
		try{
		frameImage = ImageIO.read(new File("mainchar.png"));
		System.out.println("Succeeded to read image.");
		} catch (Exception e){
			System.out.println("Failed to read image.");
		}
		
	}
	
	
	
	
	
	// Getters!!!!!
	
	public double[] getPosition(){
		return position;
	}
	public double[] getVelocity(){
		return velVector;
	}
	public BufferedImage getFrameImage(){
		return frameImage;
	}
	public int getHealth(){
		return health;
	}
	public int getMana(){
		return mana;
	}
	
	// Setters!!!!!
	
	public void setPosition(double X, double Y){
		position[0] = X;
		position[1] = Y;
	}
	public void setVelocity(double X, double Y){
		velVector[0] = X;
		velVector[1] = Y;
	}
	public void setFrameImage(BufferedImage newFrameImg){
		frameImage = newFrameImg;
	}
	public void setHealth(int newHealth){
		health = newHealth;
	}
	public void setMana(int newMana){
		mana = newMana;
	}
}
