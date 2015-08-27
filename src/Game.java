import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame{

	public Game(){
		add(new GameLogic());
		setTitle("My Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 900);
		setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game();
	}

}
