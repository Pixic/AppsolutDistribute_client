package filesystem; 

/**
 * A simple file has a name and a size
 * 
 * @author Uno Holmer
 * @version 2008-10-10
 */
public class TextFile extends File
{    
    private String contents = null;
    
    public TextFile(String name)
    {
        super(name);
    }

    public void write(String str) {
        contents = str;
    }
    
    public void append(String str) {
        contents += str;
    }
    
    public String read() {
        return contents;
    }
    
    public int getSize() {
        return (contents == null ? 0 : contents.length());
    }
}
