package filesystem; 

/**
 * This example shows a simple example of the 
 * Composite design pattern, a file system.
 * A file is either an ordinary simple file,
 * or a directory which can contain a list of files.
 * 
 * @author Uno Holmer
 * @version 2008-10-10
 */
public abstract class File
{
    public static final int indentStep = 4;
    
    private String name;

    public File(String name)
    {
         this.name = name;
    }
    
    // Two files are equal if the share the same name
    public final boolean equals(Object other) {
        if ( this == other )
            return true;
        if ( other instanceof File ) {
            File tmp = (File)other;
            return name.equals(tmp.name);
        }
        return false;
    }
    
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }
           
    // Print the name of this file
    public void print(boolean recursive) {
        print(0,recursive);
    }
    
    //  Print the name of this file indented
    protected void print(int indentLevel,boolean recursive) {
        printSpaceChars(indentLevel);
        System.out.println(name);
    }
    
    private void printSpaceChars(int indentLevel) {
        for ( int i = 0; i < indentStep*indentLevel; i++ )
            System.out.print(' ');
    }
    
    // This has to be implemented by subclasses
    public abstract int getSize();
}
