/**
 * Test the DECORATOR design style for number generators
 * 
 * @author Uno Holmer
 * @version 2008-10-13
 */
public class Main
{
	public static void main(String[] args)
	{
		BufferedNumberGenerator bp = 
		    new BufferedNumberGenerator(new PrimeGenerator());
		    
		BufferedNumberGenerator bf = 
		    new BufferedNumberGenerator(new FibonacciGenerator());
		  
		bp.computeNext();
		bp.computeNext();
		bp.computeNext();
		System.out.println(bp.getValue());
		printArray( bp.getNextSequence(5) );
		
		System.out.println();
		
	    bf.computeNext();
		bf.computeNext();
		bf.computeNext();
		System.out.println(bf.getValue());
		printArray( bf.getNextSequence(10) );
	    bf.computeNext();
		System.out.println(bf.getValue());
	}
	
	private static void printArray(long[] a) {
	    System.out.print('[');
	    if ( a.length > 0 )
	        System.out.print(a[0]);
	    for ( int i = 1; i < a.length; i++ )
	        System.out.print("," + a[i]);
	    System.out.println(']');
	}
}
