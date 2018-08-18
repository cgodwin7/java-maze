package maze;

//Richard Godwin Credited With Mouse Class
public class Mouse {
	public String s = "";
	public String last;
        public LogFile l = new LogFile("MouseLog.txt");
	//public int startX = 0, startY = 0;
	public Mouse() {

	}

	public String move() {
		String dir;
                String temp = "p";
		String preTemp = s.substring(0, 4);
		String posTemp = s.substring(5);
		s = preTemp + temp + posTemp;

		if (last == null) {
			if (Character.toLowerCase(s.charAt(1)) == ('p'))
				dir = "North";
			else if (Character.toLowerCase(s.charAt(3)) == ('p'))
				dir = "West";
			else if (Character.toLowerCase(s.charAt(5)) == ('p'))
				dir = "East";
			else if (Character.toLowerCase(s.charAt(7)) == ('p'))
				dir = "South";
			else{
				l.log("Fail Because First Move Couldn't find path");
				return "rrrrrrrrr";
			}
		} else {
			dir = flipDir(last);
			int i;
			for (i = 0; i < 4; i++) {
				dir = dirFinder(dir);
				char c = Character.toLowerCase(s.charAt(intConverter(dir)));
				if (c == 'p' || c == 'o'){
					break;
				}
			}
			if (i == 4) {
				l.log("Fail Cus no path, blocked");
				return "rrrrrrrrr";
			}
		}
		l.log("Moving in dir: " +dir);
		switch (dir) {
		case "North":
			temp = s.substring(1, 2); // where it will be after, value where r
										// will be
			preTemp = s.substring(0, 1); // before where R will be
			posTemp = s.substring(2); // after R moves
			s = preTemp + "r" + posTemp;
			break;
		case "South":
			temp = s.substring(7, 8);
			preTemp = s.substring(0, 7);
			posTemp = s.substring(8);
			s = preTemp + "r" + posTemp;
			break;
		case "West":
			temp = s.substring(3, 4);
			preTemp = s.substring(0, 3);
			posTemp = s.substring(4);
			s = preTemp + "r" + posTemp;
			break;
		case "East":
			temp = s.substring(5, 6);
			preTemp = s.substring(0, 5);
			posTemp = s.substring(6);
			s = preTemp + "r" + posTemp;
			break;
		default:
			l.log("Attempted to move in invalid dir: " + dir);
			return "rrrrrrrrr";
		}
		last = dir;
		return s;
	}

	public String flipDir(String di){
		switch (di){
		case "North":
			return "South";
		case "South":
			return "North";
		case "East":
			return "West";
		case "West":
			return "East";
		}
		return null;
	}
	
	public String dirFinder(String di) {
		switch (di) {
		case "North":
			return "West";
		case "South":
			return "East";
		case "East":
			return "North";
		case "West":
			return "South";
		}
		return null;
	}

	public int intConverter(String d) {
		switch (d) {
		case "North":
			return 1;
		case "South":
			return 7;
		case "East":
			return 5;
		case "West":
			return 3;
		}
		l.log("Not a valid direction: " + d);
		return -1;
	}

	public void setMap(String d){
		s=d;
	}
}