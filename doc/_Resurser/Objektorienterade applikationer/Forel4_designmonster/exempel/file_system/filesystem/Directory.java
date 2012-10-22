package filesystem; 

import java.util.List;
import java.util.ArrayList;

/**
 * A directory is a file that contains a list of files.
 * 
 * @author Uno Holmer
 * @version 2008-10-10
 */
public class Directory extends File
{
    // A directory usually needs at least a disc block, even if it is empty.
    public static final int emptyDirSize = 1024; // bytes
    
    private List<File> contents;

    public Directory(String name)
    {
        super(name);
        contents = new ArrayList<File>();
    }
    
    public void add(File f) throws IllegalArgumentException {
        if ( contents.contains(f) )
            // Don't destroy existing files
            throw new IllegalArgumentException(
                "Directory.add: " + f.getName() + 
                " already exists in " + getName());
        else 
            contents.add(f);
    }

    // The size of this directory is the size of the directory itself
    // plus the recursively commputed sizes of all files in this directory.
    public int getSize()         
    {
        int size = emptyDirSize;
        for ( File f : contents )
            size += f.getSize();
            
        return size;
    }
        
    // Redefine File.print(int) so that it traverses this directory (recursively)
    protected void print(int indentLevel,boolean recursive) {
        // print the name of this directory indented
        super.print(indentLevel,recursive); 
        if ( recursive ) {
            // Print recursively the names of all files in this directory
            for ( File f : contents )
                f.print(indentLevel+1,recursive);
        }
    }
}










