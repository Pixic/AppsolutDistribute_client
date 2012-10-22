package model;

import java.util.Observable;

/**
 * Abstract class of number generators
 * 
 * @author Uno Holmer
 * @version 2008-01-24
 */

public abstract class NumberGenerator extends Observable
{
    public abstract void computeNext();
    public abstract long getValue();
    public abstract void reset();
}
