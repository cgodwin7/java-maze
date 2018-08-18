package maze;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//Richard Godwin Credited with LogFile Class

public class LogFile {

    public FileOutputStream out;
    public PrintWriter printr;

    public LogFile(String nam) {
        try {
            out = new FileOutputStream(nam);
            printr = new PrintWriter(out, true);
        } catch (IOException e) {
            System.err.println("Can't access file");
        }
    }

    public void log(String message) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Calendar.getInstance().getTime());   
        printr.println("Time: " +timeStamp+ " System.Log: " + message);
    }

    public void close() {
        printr.println("Closing");
        printr.close();
    }

}

    
