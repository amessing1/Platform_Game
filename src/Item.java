import java.awt.image.BufferedImage;


public class Item {

	private String name;
	private String type;
	public int attack;
	public int defence;
	public int magicAttack;
	public int magicDefence;
	private BufferedImage icon;
	
	
	public Item(String name, String type){
		this.name = name;
		this.type = type;
		icon = new BufferedImage();
	}



}
