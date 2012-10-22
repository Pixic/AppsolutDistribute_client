import java.util.*;
/**
 * A simple clock that ticks seconds using java.util.Timer
 * 
 * @author Uno Holmer 
 * @version 2006-02-07
 */
public class Clock {
    private final int delay = 1000;    
    private final int period = 1000;
    java.util.Timer timer  = new java.util.Timer();
    ClockTick tick = new ClockTick();
 
    public Clock() {
        timer.schedule(tick,delay,period);
    }
    
    public void stop() {
        timer.cancel();
        tick.cancel();
    }
}
