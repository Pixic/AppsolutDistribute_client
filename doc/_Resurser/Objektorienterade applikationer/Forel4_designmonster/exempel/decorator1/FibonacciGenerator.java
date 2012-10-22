 

/**
 * Generates consecutive Fibonacci numbers: 1, 1, 2, 3, 5, 8, 13,... 
 * 
 * @author Uno Holmer
 * @version 2008-01-23
 */
public class FibonacciGenerator implements NumberGenerator
{
    private long currentFib, nextFib;

    /**
     * Constructor for objects of class PrimeGenerator
     */
    public FibonacciGenerator()
    {
        reset();
    }
    
   /**
    * Compute the next Fibonacci number
    */
    public void computeNext()
    {
        nextFib += currentFib;
        currentFib = nextFib - currentFib;
    }
    
   /**
    * Reset the generator to its initial state
    */
    public void reset()
    {
        currentFib = 1;
        nextFib = 1; 
    }
    
    //Return the current Fibonacci number   
    public long getValue()
    {
        return currentFib;
    }
}
