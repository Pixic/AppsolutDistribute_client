import java.util.NoSuchElementException;

/**
 * Simple iterator interface used to demonstrate the factory design pattern.
 * 
 * @author Uno Holmer
 * @version 2008-10-13
 */

public interface Iterator
{
	boolean hasNext();
	int next() throws NoSuchElementException ;
	void remove();
}
