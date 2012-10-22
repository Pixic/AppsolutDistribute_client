import javax.swing.JTextField;
import javax.swing.event.*;
import java.util.Observer;
import java.util.Observable;

/**
 * A DateView displays a date as reported by an observed model.
 * 
 * @author Uno Holmer
 * @version 2010-09-21
 */
public abstract class DateView extends JTextField implements Observer {
    private boolean hasChanged = false;
    
    public DateView(int width) {
        super(width);
        setHorizontalAlignment(JTextField.CENTER);
        getDocument().addDocumentListener(
             new DocumentListener() {
                 @Override 
                 public void removeUpdate(DocumentEvent e) { hasChanged = true; }
                 public void insertUpdate(DocumentEvent e) { hasChanged = true; }
                 public void changedUpdate(DocumentEvent e) {}
            });
    }
    
    public String getText() {
        hasChanged = false;
        return super.getText();
    }
    
    public void update(Observable o,Object arg) {
        if (o instanceof Date && arg instanceof Date.DateDetails) {
            Date.DateDetails d = (Date.DateDetails)arg;
            setText(formatDate(d.getYear(),d.getMonth(),d.getDay()));
        }
    }
    
    public boolean hasChanged() { return hasChanged; }
    
    protected abstract String formatDate(int y,int m,int d);
}
