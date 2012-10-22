package controller;

import java.awt.event.*;
import model.NumberGenerator;

/**
 * Control object for the next button in the Number Pane
 * 
 * @author Uno Holmer 
 * @version 2008-09-25
 */
public class NextButtonController implements ActionListener
{
    private NumberGenerator generator;

    public NextButtonController(NumberGenerator generator)
    {
        this.generator = generator;
    }

    public void actionPerformed(ActionEvent e) {
         generator.computeNext();
    }
}
