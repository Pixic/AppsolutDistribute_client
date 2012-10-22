package model;

/**
 * Generates consecutive Fibonacci numbers: 1, 1, 2, 3, 5, 8, 13,... 
 * 
 * @author Uno Holmer
 * @version 2009-09-28
 */
public class FibonacciGenerator extends NumberGenerator
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
        
        // These two methods are inherited from class Observable.
        // The loop above definitely changed my state,
        // so tell every interested observing object about it.
        setChanged();       
        notifyObservers(Long.toString(currentFib));   
    }
    
   /**
    * Reset the generator to its initial state
    */
    public void reset()
    {
        currentFib = 1;
        nextFib = 1; 
  
        // These two methods are inherited from class Observable.
        // The loop above definitely changed my state,
        // so tell every interested observing object about it.
        setChanged();         
        notifyObservers(Long.toString(currentFib));    
    }
    
    //Return the current Fibonacci number   
    public long getValue()
    {
        return currentFib;
    }
}
