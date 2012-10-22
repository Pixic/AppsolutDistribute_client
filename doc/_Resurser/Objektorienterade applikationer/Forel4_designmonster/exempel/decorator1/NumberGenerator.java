/**
 * Abstract class of number generators
 * 
 * @author Uno Holmer
 * @version 2008-10-13
 */

public interface NumberGenerator
{
    public abstract void computeNext();
    public abstract long getValue();
    public abstract void reset();
}
