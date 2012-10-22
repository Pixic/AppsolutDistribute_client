/**
 * A DECORATOR class that implements the NumberGenerator interface 
 * extended with a method for computing an array of n numbers.
 * 
 * @author Uno Holmer
 * @version 2008-10-13
 */
public class BufferedNumberGenerator implements NumberGenerator
{
    private NumberGenerator generator;
    
    public BufferedNumberGenerator(NumberGenerator generator) {
        this.generator = generator;
    }
    
    // Delegate NumberGenerator method calls to the decorated object.
    
    /**
    * Compute the next number
    */
    public void computeNext()
    {
        generator.computeNext();
    }
    
   /**
    * Reset the generator to its initial state
    */
    public void reset()
    {
        generator.reset();
    }
    
    //Return the current number   
    public long getValue()
    {
        return generator.getValue();
    }
    
    // Ziz iz new!
    /**
     * Compute an array of numbers.
     * 
     * @param  n   the number of numbers to compute
     * @return     an arry containing the next n numbers in the sequence
     */
    public long[] getNextSequence(int n)
    {
        long[] numArray = new long[n];
        for ( int i = 0; i < n; i++ ) {
            generator.computeNext();
            numArray[i] = generator.getValue();
        }
        return numArray;
    }
}
