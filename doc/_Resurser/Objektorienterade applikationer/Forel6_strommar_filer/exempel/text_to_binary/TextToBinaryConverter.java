import java.io.*;
import javax.swing.JOptionPane;
import java.math.BigInteger;

/**
 * Reads a file of digit string and writes the corresponding numbers to a binary file
 * 
 * @author Uno Holmer
 * @version 2008-09-28
 */
public class TextToBinaryConverter
{
    private String inputFileName;
    private String outputFileName;
    private BufferedReader in;
    private BufferedOutputStream out;
    
    public TextToBinaryConverter()
    {
        openFiles();
        convert();
    }
    
    private void getFileNames() {
        inputFileName = JOptionPane.showInputDialog(null,"Input file name","Text to Binary Converter",JOptionPane.PLAIN_MESSAGE);
        if ( inputFileName == null ) {
            System.out.println("Ok, quitting");
            System.exit(0);
        }
        outputFileName = JOptionPane.showInputDialog(null,"Output file name","Text to Binary Converter",JOptionPane.PLAIN_MESSAGE);
        if ( outputFileName == null ) {
            System.out.println("Ok, quitting");
            System.exit(0);
        }
    }
    
    private void openFiles() {
        getFileNames();
        
        // Open input file
        try {
            in = new BufferedReader(new FileReader(inputFileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot open: " + inputFileName);
            System.exit(0);
        }
        
        // Create output file
        try {
            out = new BufferedOutputStream(new FileOutputStream(outputFileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot create: " + outputFileName);
            System.exit(0);
        }
    }
    
    private void convert() {
        // Conversion loop
        // read string, convert to number, write number
        // close files
        String inputLine = null;
        while ( true ) {
            try {
                 inputLine = in.readLine();
                 if ( inputLine == null )
                     break;
                 BigInteger i = new BigInteger(inputLine);
                 byte[] bytes = i.toByteArray();   // Two complements binary representation
                 out.write(bytes.length);          // first write the number of bytes that follows
                 out.write(bytes,0,bytes.length);  // and the the actual bytes representing the number
            }
            catch (NumberFormatException e) {
                System.out.println("Malformed integer found: " + inputLine);
                System.exit(0);
            }
            catch (IOException e)  {
                System.exit(0);
            }
        }
        try {
            in.close();
            out.close();
        }
        catch (IOException e) {}
    }
}
















