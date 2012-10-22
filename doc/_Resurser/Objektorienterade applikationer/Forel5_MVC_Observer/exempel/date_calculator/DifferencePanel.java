import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * class DifferencePanel 
 * Compute the number of days between "from date" and "to date".
 * 
 * +---------------+-------------+-------------+-----------+
 * | fromDatePanel | toDatePanel | buttonPanel | daysPanel |
 * +---------------+-------------+-------------+-----------+
 * 
 * @author Uno Holmer
 * @version 2010-09-24
 */
public final class DifferencePanel extends JPanel {
    private DateView fromDateView = new IsoDateView(), toDateView = new IsoDateView();
    private Date fromDateModel = null, toDateModel = null;
    private JTextField daysField = null;
    
    public DifferencePanel() {
        makePanel();
    }
    
    private void makePanel() {
        makeDatePanel("from date",fromDateView);
        makeDatePanel("to date",toDateView);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.add(new JLabel("")); 
        JButton button = new JButton("diff");
        button.setBorder(new BevelBorder(BevelBorder.RAISED));
        button.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    computeTimeDifference();
                }
            });    
        buttonPanel.add(button);
        add(buttonPanel);
        
        JPanel daysPanel = new JPanel();
        daysPanel.setLayout(new GridLayout(2,1));
        daysPanel.add(new JLabel("days",JLabel.CENTER)); 
        daysField = new JTextField( 6);
        daysField.setHorizontalAlignment(JTextField.CENTER);
        daysField.setEditable(false);
        daysPanel.add(daysField);
        add(daysPanel);
    }
    
    private void makeDatePanel(String heading,final DateView view) {
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new GridLayout(2,1));
        datePanel.add(new JLabel(heading,JLabel.CENTER));             
        datePanel.add(view);
        add(datePanel);
    }
    
    private void computeTimeDifference() {
        if ( fromDateView.hasChanged() ) {
            try {
                int[] ymd = Gui.parseDate(fromDateView.getText());
                fromDateModel = new Date(ymd[0],ymd[1],ymd[2]);
            }
            catch (Exception e) {
                fromDateView.setText("???");
                fromDateModel = null;
            }
        }
        if ( toDateView.hasChanged() ) {
            try {
                int[] ymd = Gui.parseDate(toDateView.getText());
                toDateModel = new Date(ymd[0],ymd[1],ymd[2]);
            }
            catch (Exception e) {
                toDateView.setText("???");
                toDateModel = null;
            }
        }
        if ( fromDateModel != null && toDateModel != null )
            daysField.setText(Long.toString(toDateModel.diff(fromDateModel)));
        else
            daysField.setText(null);
    }
}

