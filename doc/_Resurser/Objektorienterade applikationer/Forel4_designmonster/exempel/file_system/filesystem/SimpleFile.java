package filesystem; 

/**
 * A simple file has a name and a size
 * 
 * @author Uno Holmer
 * @version 2008-10-10
 */
public class SimpleFile extends File
{
    private int size;
    
    public SimpleFile(String name,int size)
    {
        super(name);
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
