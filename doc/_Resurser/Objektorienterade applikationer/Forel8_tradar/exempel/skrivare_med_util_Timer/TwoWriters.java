import java.util.*;
/**
 * Skansholm 12.3   
 * 
 * @author JSk (UH) 
 * @version 2006-02-07
 */
public class TwoWriters
{
    private Timer t = new Timer();
    private Writer2 s1 = new Writer2("Java is better..."),
                    s2 = new Writer2("C++ is best!");
                    
    public TwoWriters() throws InterruptedException
    {
        // schemalägg de två  objekten
        t.schedule(s1,0,738);
        t.schedule(s2,0,1122);
    }
    
   public void stop() {
	    s1.cancel();
        s2.cancel();
        t.cancel();
	    System.out.println("s1: " + s1.getCount() + ", s2: " + s2.getCount() );
	    System.exit(0);
	}
}
