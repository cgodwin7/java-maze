package maze;
import java.io.*;
import javax.sound.sampled.*;
//Nick Wilson Credited With Music Class
public class Music extends Thread {
	private Thread t;
	public Music() {
	}

	public void run() {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("ghz.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			while (true) {
				clip.start();
				clip.loop(clip.LOOP_CONTINUOUSLY);
			}
		}

		catch (Exception e) {
			e.printStackTrace();

			// To help me find the file path
			File here = new File(".");
			System.out.println(here.getAbsolutePath());
		}
	}

}
