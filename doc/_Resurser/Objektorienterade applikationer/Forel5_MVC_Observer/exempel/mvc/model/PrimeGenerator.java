package model;

/**
 * Generates consecutive prime numbers: 2, 3, 5, 7, 11, 13, 17, 19, ...
 * 
 * @author Uno Holmer
 * @version 2009-09-28
 */
public class PrimeGenerator extends NumberGenerator
{
    private long currentPrime;

    /**
     * Constructor for objects of class PrimeGenerator
     */
    public PrimeGenerator()
    {
        reset();  
    }
    
    private boolean isPrime(long x)
    {
        if ( x == 2 )
            return true;
            
        for (long i = 2; i <= (long)Math.ceil(Math.sqrt(x)); i++)
            if (x % i == 0)
                return false;
                
        return true;
    }
    
   /**
    * Compute the next prime number
    */
    public void computeNext()
    {
        do {
            currentPrime++;
        } while (! isPrime(currentPrime) );
        
        // These two methods are inherited from class Observable.
        // The loop above definitely changed my state,
        // so tell every interested observing object about it.
        setChanged();        
        notifyObservers(Long.toString(currentPrime));   
    }
    
   
   /**
    * Reset the generator to its initial state
    */
    public void reset()
    {
        currentPrime = 2; 
        
        // These two methods are inherited from class Observable.
        // The loop above definitely changed my state,
        // so tell every interested observing object about it.
        setChanged();         
        notifyObservers(Long.toString(currentPrime));    
    }
    
   /**
    * Return the current prime number
    */
    public long getValue()
    {
        return currentPrime;
    }
}
