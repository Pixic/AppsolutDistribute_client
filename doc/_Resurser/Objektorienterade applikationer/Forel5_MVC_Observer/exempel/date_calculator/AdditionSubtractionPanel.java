import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * AdditionSubtractionPanel defines common behaviour for 
 * arithmetic panels where days can be added to or subtracted from a date.
 * The two subclasses AdditionPanel and SubtractionPanel implements the
 * actual addition/subtraction behaviour in the underlaying date model.
 * When the model's state changes, the associated dateView is updated 
 * via the Observer pattern.
 * 
 * +-----------+-----------+-------------+
 * | datePanel | daysPanel | buttonPanel |
 * +-----------+-----------+-------------+
 * 
 * @author Uno Holmer
 * @version 2010-09-24
 */
public abstract class AdditionSubtractionPanel extends JPanel {
    protected JTextField daysField = null;
    protected DateView dateView = null;
    protected Date dateModel = null;
    private long days;
    
    public AdditionSubtractionPanel(String buttonText) {
        makePanel(buttonText);
    }
    
    public void makePanel(String buttonText) {
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new GridLayout(2,1));
        datePanel.add(new JLabel("date",JLabel.CENTER));       
        dateView = new IsoDateView(); 
        datePanel.add(dateView);
        add(datePanel);
                       
        JPanel daysPanel = new JPanel();
        daysPanel.setLayout(new GridLayout(2,1));
        daysPanel.add(new JLabel("days",JLabel.CENTER));
        daysField = new JTextField(6);
        daysField.setHorizontalAlignment(JTextField.CENTER);
        daysPanel.add(daysField);
        add(daysPanel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));     
        buttonPanel.add(new JLabel(""));       
        JButton button = new JButton(buttonText);
        button.setBorder(new BevelBorder(BevelBorder.RAISED));
        button.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ( handleButtonEvent() )
                        handleModelUpdate(days);
                }
            });    
        buttonPanel.add(button);
        add(buttonPanel);
    }
    
    private boolean handleButtonEvent() {
        if ( dateView.hasChanged() ) {
            try {
                int[] ymd = Gui.parseDate(dateView.getText());
                dateModel = new Date(ymd[0],ymd[1],ymd[2]);
                dateModel.addObserver(dateView);
            }
            catch (Exception e) {
                dateView.setText("???");
                dateModel = null;
                return false;
            }
        }
        try {
            days = Long.parseLong(daysField.getText());
            return true;
        }
        catch (Exception e) {
            daysField.setText("???");
            return false;
        }
    }
    
    private void handleModelUpdate(long days) {
        try {
            updateModel(days);
        }
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                null,
                e.getMessage(),
                "Date computation error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    protected abstract void updateModel(long days) throws IllegalArgumentException;
}
