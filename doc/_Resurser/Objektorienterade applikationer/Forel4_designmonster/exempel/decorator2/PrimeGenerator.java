/**
 * Generates consecutive prime numbers: 2, 3, 5, 7, 11, 13, 17, 19, ...
 * 
 * @author Uno Holmer
 * @version 2008-10-13
 */
public class PrimeGenerator implements NumberGenerator
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
    }
    
   
   /**
    * Reset the generator to its initial state
    */
    public void reset()
    {
        currentPrime = 2; 
    }
    
   /**
    * Return the current prime number
    */
    public long getValue()
    {
        return currentPrime;
    }
}
