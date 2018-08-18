package maze;
import java.awt.*;
import java.io.File;
import java.util.*;
import javax.swing.ImageIcon;
//Nick Wilson Credited With Map Class
public class Map {

	private Scanner m;
	private String Map[][] = new String[82][82];
	private Image dirt, wall, sonic, grass;
	private MazeUpdate update;
	
	
	public int getWidth(){
		return Map[0].length;
	}
	
	public int getHeight(){
		return Map.length;
	}

	public Map() {

		ImageIcon image = new ImageIcon("Sonic.png");
		sonic = image.getImage();
		image = new ImageIcon("wall.png");
		wall = image.getImage();
		image = new ImageIcon("dirt.gif");
		dirt = image.getImage();
		image = new ImageIcon("grass.png");
		grass = image.getImage();

		openFile();
		readFile();
		closeFile();
	}

	public Image getDirt() {
		return dirt;
	}

	public Image getSonic() {
		return sonic;
	}

	public Image getWall() {
		return wall;
	}

	public Image getOutside() {
		return grass;
	}

	public void swapMap(int x1, int y1, int x2, int y2){
		String temp = getMap(x1, y1);
		setMap(x1, y1, getMap(x2, y2));
		setMap(x2, y2, temp);
	}
	public String getMap(int x, int y) {
		String index = Map[x][y];
		return index;
	}
	
	public void setMap(int x, int y, String value){
		Map [x][y] = value;
		if(update != null){
			update.mapChanged();
		}
	}

	public void openFile() {
		// Opens the file
		try {
			m = new Scanner(new File("maze.txt"));
		}

		catch (Exception e) {
			System.out.println("Error loading map");
		}
	}

	public void readFile() {
		// Reads the contents of said file
		while (m.hasNext()) {
			for (int i = 0; i < 82; i++) {
				for (int j = 0; j < 82; j++) {
					if (i == 0 || j == 0 || i == 81 || j == 81) {
						Map[j][i] = "o";
					} else {
						Map[j][i] = m.next();
					}
				}
			}
		}
	}

	public void closeFile() {
		// Closes the file
		m.close();
	}
	
	public void setUpdate(MazeUpdate update){
		this.update = update;
	}

}
