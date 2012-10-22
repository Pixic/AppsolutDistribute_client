import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Test of a list with scroll pane
 * 
 * @author Uno Holmer 
 * @version 2006-01-18
 */
public class JListTest extends JFrame 
{    
	public JListTest()
	{
		makeFrame();
	}
	
	public void makeFrame()
	{
	    setTitle("JList test");
	    JPanel contentPane = (JPanel)getContentPane();
	    
        // Create the scrolled list of strings
        String[] strings = new String[] {"This","is","a","test","of","a","list",
                                           "with","scroll pane"};
        final JList wordList = new JList(strings);
        JScrollPane scrollPane = new JScrollPane(wordList);
        scrollPane.setColumnHeaderView(new JLabel("Select a string"));
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        // Add a mouse listener for the list
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) 
                     System.out.println(wordList.getSelectedValue());
            }
        };
        wordList.addMouseListener(mouseListener);
        
        // Display the window in the middle of the screen
        pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
        setVisible(true);
	}
}
