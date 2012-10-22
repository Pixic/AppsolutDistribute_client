import java.io.*;
import javax.swing.JOptionPane;
import java.math.BigInteger;

/**
 * Reads a binary ile of numbers in two complements format and writes their string representations to a text file
 * 
 * @author Uno Holmer
 * @version 2008-09-28
 */
public class BinaryToTextConverter
{
    private String inputFileName;
    private String outputFileName;
    private BufferedInputStream in;
    private PrintWriter out;
    
    public BinaryToTextConverter()
    {
        openFiles();
        convert();
    }
    
    private void getFileNames() {
        inputFileName = JOptionPane.showInputDialog(null,"Input file name","Binary to Text Converter",JOptionPane.PLAIN_MESSAGE);
        if ( inputFileName == null ) {
            System.out.println("Ok, quitting");
            System.exit(0);
        }
        outputFileName = JOptionPane.showInputDialog(null,"Output file name","Binary to Text Converter",JOptionPane.PLAIN_MESSAGE);
        if ( outputFileName == null ) {
            System.out.println("Ok, quitting");
            System.exit(0);
        }
    }
    
    private void openFiles() {
        getFileNames();
        
        // Open input file
        try {
            in = new BufferedInputStream(new FileInputStream(inputFileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot open: " + inputFileName);
            System.exit(0);
        }
        
        // Create output file
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName)));
        }
        catch (IOException e) {
            System.out.println("Cannot create: " + outputFileName);
            System.exit(0);
        }
    }
    
    private void convert() {  
        while ( true ) {
            try {
                 int noOfBytes = in.read();             // first read the number of bytes in the number that follows
                 if ( noOfBytes == -1 )
                     break;
                 byte[] buf = new byte[noOfBytes];      // allocate a buffer of appropriate size
                 if ( in.read(buf,0,noOfBytes) == -1 )  // read the bytes
                     break;
    
                 BigInteger i = new BigInteger(buf);    // convert the bytes to a bigint
                 out.println(i);                        // print the digits in the text file
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
















