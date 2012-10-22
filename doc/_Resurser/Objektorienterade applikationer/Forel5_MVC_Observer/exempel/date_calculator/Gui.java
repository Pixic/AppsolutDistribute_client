import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
/**
 * The GUI for the date calculator application.
 * 
 * @author Uno Holmer
 * @version 2010-09-24
 */
public class Gui {
    private JFrame frame;
    
    public Gui() {
        makeFrame();
    }
    
    private void makeFrame() {
        frame = new JFrame("Date calculator");
        frame.setLayout(new GridLayout(3,1,20,20));
        frame.add(new AdditionPanel("add"));
        frame.add(new SubtractionPanel("sub"));
        frame.add(new DifferencePanel());
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // place this frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);   
    }
    
    // A utility method for date parsing.
    public static int[] parseDate(String dateString) 
    throws NumberFormatException, DateFormatError
    {
        if ( dateString == null )
            dateString = "";
        Scanner sc = new Scanner(dateString.trim());
        sc.useDelimiter("-");
        final int n = 3;
        final String[] ymd = {"year","month","day"};
        int[] result = new int[n];
        for ( int i = 0; i < n; i++ ) {
            if ( ! sc.hasNextInt() )
                throw new DateFormatError("parseDate: Missing " + ymd[i]);
            result[i] = sc.nextInt();
        }
        return result;
    }
}
