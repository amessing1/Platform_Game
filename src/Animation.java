import java.awt.image.BufferedImage;


public class Animation {

	private int currentFrameImage;
	private BufferedImage[] animationSet;
	
	public Animation(String character, String type){
		currentFrameImage = 0;
		loadAnimations(character, type);
	}
	public BufferedImage getFrameImage(){
		BufferedImage img = animationSet[currentFrameImage];
		currentFrameImage += 1;
		if(currentFrameImage >= animationSet.length){
			currentFrameImage = 0;
		}
		
		return img;
	}
	private void loadAnimations(String ch, String type){
		
	}
}
