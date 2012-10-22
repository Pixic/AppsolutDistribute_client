import java.util.*;

/**
 * Skansholm 12.3   
 * 
 * @author Uno Holmer 
 * @version 2006-02-07
 */
public class Writer2 extends TimerTask {
    String text;
    int count = 0;

    public Writer2(String text) {
        this.text = text;
    }
    
    public void run() {
        System.out.println(text); 
        count++;
    }
    
    public int getCount() {
        return count;
    }
}
