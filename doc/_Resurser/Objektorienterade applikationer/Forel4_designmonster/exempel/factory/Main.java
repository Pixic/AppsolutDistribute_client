/**
 * Test the SimpleArrayList
 * 
 * @author Uno Holmer
 * @version 2008-10-13
 */
public class Main
{
    public static void main(String[] args) {
        
        SimpleArrayList l = new SimpleArrayList();
        
        // Test add
        for ( int i = 100; i > 0; i-- )
            l.add(i);
            
            
        // Test remove
        Iterator it = l.iterator();
        it.next();
        while ( it.hasNext() ) {
            it.next();
            if ( it.hasNext())
                it.remove();
        }
            
        it = l.iterator();   // Create a fresh iterator
        while ( it.hasNext() )
            System.out.println(it.next());
    }
}
