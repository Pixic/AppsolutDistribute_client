/**
 * Extends the FibonacciGenerator class with a method for computing
 * an array of n fibonacci numbers
 * 
 * @author Uno Holmer
 * @version 2008-10-13
 */
public class BufferedFibonacciGenerator extends FibonacciGenerator
{
    /**
     * Compute an array of fibonacci numbers.
     * 
     * @param  n   the number of numbers to compute
     * @return     an arry containing the next n numbers in the sequence
     */
    public long[] nextSequence(int n)
    {
        long[] numArray = new long[n];
        for ( int i = 0; i < n; i++ ) {
            computeNext();
            numArray[i] = getValue();
          }
        return numArray;
    }
}
