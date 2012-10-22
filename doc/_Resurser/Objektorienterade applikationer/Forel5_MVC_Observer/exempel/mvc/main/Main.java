package main;

import view.*;
import model.*;

/**
 * The main program ties the GUI and the model objects together.
 * 
 * @author Uno Holmer 
 * @version 2008-09-25
 */
public class Main
{
	public static void main(String[] args) {
        PrimeGenerator primeGen = new PrimeGenerator();
	    FibonacciGenerator fiboGen = new FibonacciGenerator();
        new UserInterface(primeGen,fiboGen);
        primeGen.reset();
        fiboGen.reset();
	}
}
