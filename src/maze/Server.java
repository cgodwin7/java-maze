package maze;

import java.io.IOException;
import java.awt.*;
import java.net.*;

import javax.swing.*;

import java.io.*;
//Richard Godwin Credited With Server Class
public class Server {

    public static Map m = new Map();
    public Button b[][] = new Button[5][10];

    public Server() {
    }

    public static String getPos(int x, int y) {
        return mapCell(m.getMap(x - 1, y - 1)) + mapCell(m.getMap(x, y - 1))
                + mapCell(m.getMap(x + 1, y - 1)) + mapCell(m.getMap(x - 1, y))
                + mapCell(m.getMap(x, y)) + mapCell(m.getMap(x + 1, y))
                + mapCell(m.getMap(x - 1, y + 1)) + mapCell(m.getMap(x, y + 1))
                + mapCell(m.getMap(x + 1, y + 1));

    }

    public static String mapCell(String c) {
        if (c.equals("d")) {
            return "o";
        }
        return c;
    }

    public static void main(String[] args) throws IOException {
        Server ser = new Server();
        Music mu = new Music();
        mu.start();
        LogFile lo = new LogFile("ServerLog.txt");
        ServerSocket listener = new ServerSocket(1300);
        try {
            new GUI(m);
        } catch (Exception e) {
            lo.log("GUI Error");
        }
        int x = -1, y = -1;
        for (int i = 0; i < m.getWidth(); i++) {
            for (int j = 0; j < m.getHeight(); j++) {
                if (m.getMap(i, j).equals("r")) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        if (x == -1 || y == -1) {
            System.out.println("No Rat Found");
            return;
        }
        boolean victory = false;
        JPanel jp = new JPanel();
        JOptionPane.showMessageDialog(jp, (" GOTTA GO FAST! \n CSE1302J FINAL PROJECT \n By: Richard Godwin & Nick Wilson"), "WARNING", JOptionPane.WARNING_MESSAGE);
        Socket socket = listener.accept();
        listener.close();
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        
        int tries = 0;
        long startTime = System.currentTimeMillis();
        long time = 0;
        try {
            while (!victory) {

                String message = getPos(x, y);
                out.println(message);
                out.flush();
                lo.log("Message sent to client" + message);
                message = br.readLine();
                time = System.currentTimeMillis() - startTime;
                if (time>(5*60*1000)){
                    out.println("wwwwwwwww");
                    out.flush();
                    time = System.currentTimeMillis() - startTime;
                    time *= .001;
                    JPanel pane = new JPanel();
                    JOptionPane.showMessageDialog(pane, (" DEFEAT! Mouse Took Too Long. \n Tries: " + tries + "\n Time In Seconds: " + time), "WARNING", JOptionPane.WARNING_MESSAGE);
                    victory = true;
                    break;
                }
                tries++;
                lo.log("Message Recieved from the Client" + message);
                if (message.equals("rrrrrrrrr")) {
                    return;
                }
                int newX, newY;
                if (Character.toLowerCase(message.charAt(1)) == 'r') {
                    newY = y - 1;
                    newX = x;
                } else if (Character.toLowerCase(message.charAt(3)) == 'r') {
                    newY = y;
                    newX = x - 1;
                } else if (Character.toLowerCase(message.charAt(5)) == 'r') {
                    newY = y;
                    newX = x + 1;
                } else if (Character.toLowerCase(message.charAt(7)) == 'r') {
                    newY = y + 1;
                    newX = x;
                } else {
                    out.println("rrrrrrrrr");
                    out.flush();
                    continue;
                }
                if (tries > 3199) {
                    out.println("wwwwwwwww");
                    out.flush();
                    time = System.currentTimeMillis() - startTime;
                    time *= .001;
                    JPanel pane = new JPanel();
                    JOptionPane.showMessageDialog(pane, (" DEFEAT! Mouse Took Too Many Tries. \n Tries: " + tries + "\n Time In Seconds: " + time), "WARNING", JOptionPane.WARNING_MESSAGE);
                    victory = true;
                    break;
                }
                if (m.getMap(newX, newY).equals("d")) {
                    out.println("ooooooooo");
                    out.flush();
                    time = System.currentTimeMillis() - startTime;
                    time *= .001;
                    JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, (" VICTORY!! \n Tries: " + tries + "\n Time In Seconds: " + time), "WARNING", JOptionPane.WARNING_MESSAGE);
                    victory = true;
                    break;
                } else {
                   // System.out.println("X = " + x + " NewX = " + newX + " Y = "
                      //     + y + " newY = " + newY);
                    m.swapMap(x, y, newX, newY);
                    x = newX;
                    y = newY;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            socket.close();
        }

    }
}
