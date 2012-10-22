import java.util.*;
/**
 * run is called periodically by java.util.Timer
 * 
 * @author Uno Holmer 
 * @version 2006-02-07
 */
public class ClockTick extends TimerTask
{
    private long seconds = 0;
 
    public void run() {
        System.out.println(++seconds);
    }
}
