package view;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;

import model.*;
import controller.*;

/**
 * A NumberPane panel displays one number generator class
 * @author Uno Holmer 
 * @version 2012-01-24
 */
public class NumberPane extends JPanel implements Observer
{ 
    private TextField outputField;
    
    public NumberPane(String buttonText,
                      NextButtonController nextButtonController,
                      ResetButtonController resetButtonController)
    { 
        setLayout(new GridLayout(3,1,10,10));
        
        // The "Next" button
        JButton nextButton = new JButton(buttonText);
        nextButton.addActionListener(nextButtonController);
        add(nextButton);
        
        // The output text field
        outputField = new TextField();
        outputField.setEditable(false);
        outputField.setBackground(Color.WHITE);
        add(outputField);
        
        // The "Reset" button
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(resetButtonController);
        add(resetButton);                                
    }  
    
    // This method is called automatically by notifyObservers().
    // Display the argument in the output text field.
    public void update(Observable o,Object arg)
    {   
        if (o instanceof NumberGenerator && arg instanceof String)  
            outputField.setText((String)arg); 
    }
}