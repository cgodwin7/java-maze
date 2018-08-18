package maze;
import java.awt.Graphics;
import javax.swing.*;
//Nick Wilson Credited With Board Class
public class Board extends JPanel implements MazeUpdate {


	private Map m;

	public Board(Map d) {

		m=d;
		m.setUpdate(this);

	}

	
	public void mapChanged(){
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);

		for (int y = 0; y < m.getHeight(); y++) {
			for (int x = 0; x < m.getWidth(); x++) {
				if (m.getMap(x, y).equals("p")) {
					g.drawImage(m.getDirt(), x * 12, y * 12, null);
				}
				if (m.getMap(x, y).equals("r")) {
					g.drawImage(m.getSonic(), x * 12, y * 12, null);
				}
				if (m.getMap(x, y).equals("w")) {
					g.drawImage(m.getWall(), x * 12, y * 12, null);
				}
				if (m.getMap(x, y).equals("o")) {
					g.drawImage(m.getOutside(), x * 12, y * 12, null);
				}
				if (m.getMap(x, y).equals("d")) {
					g.drawImage(m.getDirt(), x * 12, y * 12, null);
				}
			}
		}
	}

}
