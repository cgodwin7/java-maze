package maze;
import javax.swing.JFrame;
//Nick Wilson Credited With GUI Class
public class GUI{

	public GUI(Map m) {
		JFrame f = new JFrame();
		f.setTitle("Maze Game");
		f.add(new Board(m));
		
		f.setSize((84*12), (84*12));
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
