import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

/**
 * Skansholm s.406
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Digitalklocka extends JLabel 
                           implements ActionListener
{
    private DateFormat df = DateFormat.getTimeInstance();

    public Digitalklocka() {
        setHorizontalAlignment(JLabel.CENTER);
        setOpaque(true);
        setBackground(Color.white);
        setFont(new Font("SansSerif",Font.BOLD,24));
        javax.swing.Timer timer = new javax.swing.Timer(1000,this);
        timer.start();
    }
    
     public Digitalklocka(String timeZone) {
        this();
        df.setTimeZone(TimeZone.getTimeZone(timeZone));
    }

    public void actionPerformed(ActionEvent e) {
        setText(df.format(new Date()));
    }
}
