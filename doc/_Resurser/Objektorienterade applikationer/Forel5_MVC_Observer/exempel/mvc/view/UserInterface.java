package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.Observer;
import java.util.Observable;

import model.*;
import controller.*;

/**
 * A GUI for the number generator classes
 * 
 * @author Uno Holmer 
 * @version 2012-01-24
 */

public class UserInterface 
{
    private JFrame frame;

    public UserInterface(PrimeGenerator primeGen,FibonacciGenerator fibGen)
    {
        frame = new JFrame("Number generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new GridLayout(1,2,10,10));
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        JPanel primePanel = 
            new NumberPane("Next prime number",
                            new NextButtonController(primeGen),
                            new ResetButtonController(primeGen));
        primeGen.addObserver((Observer)primePanel);
        contentPane.add(primePanel);
        
        JPanel fibPanel = 
            new NumberPane("Next Fibonacci number",
                            new NextButtonController(fibGen),
                            new ResetButtonController(fibGen));
        fibGen.addObserver((Observer)fibPanel);
        contentPane.add(fibPanel);
      
        frame.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
}
