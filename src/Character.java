import java.awt.image.*;
import java.io.File;

import javax.imageio.*;

public class Character {
	
	private double[] position;
	private double[] velocity;
	private double[] acceleration;
	private BufferedImage frameImage;
	private Animation[] animations;
	private int health;
	private int mana;
	private Item[] inventory;
	private Skill[] skills;
	private boolean left;
	private boolean right;
	private boolean jumping;
	
	public Character(double x, double y){
		position = new double[2];
		position[0] = x;
		position[1] = y;
		velocity = new double[2];
		velocity[0] = 0;	// x-vector
		velocity[1] = 0;	// y-vector
		acceleration = new double[2];
		acceleration[0] = 0;	// x-vector
		acceleration[1] = 0;	// y-vector
		health = 100;
		mana = 100;
		try{
		frameImage = ImageIO.read(new File("mainchar.png"));
		System.out.println("Succeeded to read image.");
		} catch (Exception e){
			System.out.println("Failed to read image.");
		}
		
	}
	
	
	void setLeft(boolean facingdirection){
		left = facingdirection;
		return;
	}
	void setRight(boolean facingdirection){
		right = facingdirection;
		return;
	}
	void jump(boolean j){
		jumping = j;
		return;
	}
	boolean isJumping(){
		return jumping;
	}
	
	// Getters!!!!!
	
	public double[] getPosition(){
		return position;
	}
	public double[] getVelocity(){
		return velocity;
	}
	public double[] getAcceleration(){
		return acceleration;
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
		velocity[0] = X;
		velocity[1] = Y;
	}
	public void setAcceleration(double X, double Y){
		acceleration[0] = X;
		acceleration[1] = Y;
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
