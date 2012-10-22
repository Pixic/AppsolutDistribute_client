import java.io.*;

/**
 * Echo keyboard input to the screen. 
 * 
 * @author Uno Holmer
 * @version 2008-09-29
 */

// "UTF8"      UTF-8 (multi byte)
// "8859_1"    IsoLatin 1
// "Cp850"     MS-DOS
// "Cp1252"

public class Main
{
    public static void main(String[] args) throws IOException {
        
        String encoding = System.getProperty("file.encoding");
        System.out.println("File encoding: " + encoding);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in,encoding));
        
        System.out.println("Type some text:");
        String line = in.readLine();
        while ( line != null ) {
            System.out.println(">>>" + line + "<<<<");
            line = in.readLine();
        }
    }
}
