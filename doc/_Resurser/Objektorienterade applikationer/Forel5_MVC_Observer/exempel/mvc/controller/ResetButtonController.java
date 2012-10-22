package controller;

import java.awt.event.*;
import model.NumberGenerator;

/**
 * Control object for the reset button in the Number Pane
 * 
 * @author Uno Holmer 
 * @version 2008-09-25
 */
public class ResetButtonController implements ActionListener
{
    private NumberGenerator generator;
 
    public ResetButtonController(NumberGenerator generator)
    {
        this.generator = generator;
    }

    public void actionPerformed(ActionEvent e) {
         generator.reset();
    }
}
