import java.awt.image.*;
import javax.imageio.*;

public class Character {
	
	private int[] position;
	private int[] velVector; 
	private BufferedImage frameImage;
	private Animation[] animations;
	private int health;
	private int mana;
	private Item[] inventory;
	private Skills[] skills;
	
	public Character(int x, int y){
		position[0] = x;
		position[1] = y;
		velVector[0] = 0;	// x-vector
		velVector[1] = 0;	// y-vector
		health = 100;
		mana = 100;
	}
	
	
	
	
	
	// Getters!!!!!
	
	public int[] getPosition(){
		return position;
	}
	public int[] getVelocity(){
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
	
	public void setPosition(int X, int Y){
		position[0] = X;
		position[1] = Y;
	}
	public void setVelocity(int X, int Y){
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
