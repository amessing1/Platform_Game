import java.awt.image.BufferedImage;


public class Skill {
	
	private String name;
	private int damage;
	private String type;
	private BufferedImage skillIcon;
	private Animation animation;
	
	public Skill(){

		animation = new Animation("skill", name);
		
	}
	
	public int getDamage(){
		return damage;
	}
	
	public String getType(){
		return type;
	}

}
