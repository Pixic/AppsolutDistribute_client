import javax.swing.*;
import java.awt.event.*;
/**
 * A clock that ticks seconds using javax.swing.Timer
 */
public class SwingClock implements ActionListener
{
    private long seconds = 0;
    private final int period = 1000;
    javax.swing.Timer timer = new javax.swing.Timer(period,this);
 
    public void start() {
        timer.start();
    }
    
    public void stop() {
        timer.stop();
    }
    
    public void actionPerformed(ActionEvent e) {
        System.out.println(++seconds);
    }
}

