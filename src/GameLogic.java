import javax.swing.JFrame;


public class GameLogic {

	private static JFrame window;
	private static Character mainCharacter;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		window = new JFrame("Game name");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		mainCharacter = new Character();
		playTheGame();
	}
	
	private static void playTheGame(){
		boolean playing = true;
		while(playing){
			// GAME RUNNING
			readInput();
			// Do stuff
			printToScreen();
		}
	}
	private static void printToScreen(){
		
	}
	private static void readInput(){
		
	}
	
}
