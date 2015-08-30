import java.awt.image.*;
import java.io.File;

import javax.imageio.*;

public class Character {
	
	private Vector2D position; //pixels
	private Vector2D velocity;
	private Vector2D acceleration;
	private BufferedImage frameImage;
	private Animation[] animations;
	private int health;
	private int mana;
	private Item[] inventory;
	private Skill[] skills;
	private boolean left;
	private boolean right;
	private boolean jumping;
	
	public Character(Vector2D pos){ // pixels
		position = pos;
		velocity = new Vector2D(0, 0);
		acceleration = new Vector2D(0, 0);
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
	
	public Vector2D getPosition(){
		return position;
	}
	public Vector2D getVelocity(){
		return velocity;
	}
	public Vector2D getAcceleration(){
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
	
	public void setPosition(Vector2D newPos){
		position = newPos;

	}
	public void setVelocity(Vector2D newVel){
		velocity = newVel;
		
	}
	public void setAcceleration(Vector2D newAcc){
		acceleration = newAcc;
		
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
