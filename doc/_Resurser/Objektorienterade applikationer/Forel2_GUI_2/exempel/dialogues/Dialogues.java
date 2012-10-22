import javax.swing.JOptionPane;

/**
 * Tests some dialogue windows in Java
 * 
 * @author Uno Holmer 
 * @ version 2006-01-18
 */
public class Dialogues
{
  
    public Dialogues()
    {
        // Simple messsage dialog
        JOptionPane.showMessageDialog(null,
                                      "An information message",
                                      "A message dialog",
                                      JOptionPane.INFORMATION_MESSAGE
        );
            
        int i;
        // Yes/No dialog
        do {
            i = 
            JOptionPane.showConfirmDialog(null,
                                          "Choose one", 
                                          "A dialog with Yes/No option", 
                                          JOptionPane.YES_NO_OPTION);
                                          
            System.out.println("ConfirmDialog returned: " + i );
        } while ( i != JOptionPane.NO_OPTION );
        
        // Yes/No/Cancel dialog
        do {
            i = 
            JOptionPane.showConfirmDialog(null,"Choose one");
                                          
            System.out.println("ConfirmDialog returned: " + i );
        } while ( i != JOptionPane.CANCEL_OPTION );
        
String s;
        // Input dialog
        do {
            s = 
            JOptionPane.showInputDialog(null,"Choose one");
                                          
            System.out.println("InputDialog returned: " + s );
        } while ( s != null );
    }
}
