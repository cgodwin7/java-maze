package maze;
import java.io.IOException;
import java.net.*;
import java.io.*;
//Richard Godwin Credited With Client Class
public class Client{
	public static void main(String [] args) throws IOException{
		Socket s = new Socket("127.0.0.1", 1300);
                try{
                Thread.sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                LogFile lo = new LogFile("ClientLog.txt");
		Mouse mou = new Mouse();
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		OutputStream os = s.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		String answer =input.readLine();
		String sendMessage = "";
				
		while ((answer != null) && (answer.length() == 9)){
			
			lo.log("Message recieved from Server: " +answer);
			
			if (answer.equalsIgnoreCase("rrrrrrrrr")){
				lo.log("Move Failure. Mouse cannot move.");
				break;
			}
			if (answer.equalsIgnoreCase("wwwwwwwww")){
				lo.log("Mouse Failure.");
				break;
			}
			if (answer.equalsIgnoreCase("ooooooooo")){
				lo.log("VICTORY!!");
				break;
			}
			mou.setMap(answer);
			sendMessage = mou.move();
			bw.write(sendMessage);
			bw.newLine();
			bw.flush();
			lo.log("Message sent to server: " + sendMessage);
			answer = input.readLine();
		}
		s.close();
		lo.log("connection closed");
	}
}
