import java.util.NoSuchElementException;

/**
 * Simple array list class used to demonstrate the factory design pattern.
 * 
 * @author Uno Holmer
 * @version 2008-10-13
 */
public class SimpleArrayList implements SimpleAbstractList
{
    public static int DEFAULT_CAPACITY = 10;
    
	private int[] array;
	private int size;      // Number of elements

	public SimpleArrayList()
	{
		array = new int[DEFAULT_CAPACITY];
		size = 0;
	}
	
	public int getSize() {
	    return size;
    }

	public int get(int i) throws IllegalArgumentException 
	{
	    if ( i < 0 || i >= size )
	        throw new IllegalArgumentException("SimpleArrayList.get: index out of bounds");
	    return array[i];
	}
	
	public void set(int i,int x) throws IllegalArgumentException 
	{
	    if ( i < 0 || i >= size )
	        throw new IllegalArgumentException("SimpleArrayList.set: index out of bounds");
	    array[i] = x;
	}
	
    public void add(int x) 
    {
	    if ( size == array.length ) { 
	        int[] old = array;
	        array = new int[2*old.length];
	        for ( int i = 0; i < old.length; i++ )
	            array[i] = old[i];
	    }
	    array[size++] = x;
	}
	
	
	// Factory method
	public Iterator iterator() {
	    return new SimpleArrayListIterator(this);
	}
	
	// Internal Iterator class
	private class SimpleArrayListIterator implements Iterator {
	    private SimpleArrayList theList = null;	     
	    private int currentPosition = 0;
	    	    
	    public SimpleArrayListIterator(SimpleArrayList someList) {
	        theList = someList;
        }
	       
	    public boolean hasNext() 
	    {
	        return currentPosition < size;
	    }
	       
	    public int next() throws NoSuchElementException 
	    {
	        if ( ! hasNext() )
	            throw new NoSuchElementException("SimpleArrayListIterator: iterator out of bounds");
	            
	        return array[currentPosition++];
	    }
	       
	    // Remove the current element by shifting elements at higher index down one step.
	    public void remove()
	    {
	        size--;
	        for ( int i = currentPosition; i < size; i++ )
	            array[i] = array[i+1];
        }
	}
}



















