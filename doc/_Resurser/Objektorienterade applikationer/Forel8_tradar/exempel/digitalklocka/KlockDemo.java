import java.awt.*;
import javax.swing.*;
import java.util.*;
/**
 * Skansholm s.407
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KlockDemo extends JFrame {
    
    public KlockDemo()
    {
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2,2,10,10));
        
        contentPane.add(new Digitalklocka());
        contentPane.add(new Digitalklocka("Australia/Melbourne")); 
                
        JLabel label = new JLabel("Lokal tid",JLabel.CENTER);
        label.setFont(new Font("Serif",Font.BOLD,18));
        contentPane.add(label);
        
        label = new JLabel("Melbourne",JLabel.CENTER);
        label.setFont(new Font("Serif",Font.BOLD,18));
        contentPane.add(label);

        setSize(250,125);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
