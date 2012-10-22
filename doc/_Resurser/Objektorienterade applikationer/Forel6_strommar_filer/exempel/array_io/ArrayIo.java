import java.io.*;

/**
 * Binary file io for arrays of long integers
 * 
 * @author Uno Holmer
 * @version 2008-09-29
 */
public class ArrayIo
{
    public static void save(long[] array,String fileName) throws IOException {
        DataOutputStream out =
            new DataOutputStream(new FileOutputStream(fileName));
        out.writeInt(array.length);
        for ( long element : array )
            out.writeLong(element);

        out.close();
    }
    
    public static long[] load(String fileName) throws IOException {
        DataInputStream in =
            new DataInputStream(new FileInputStream(fileName));
        int size = in.readInt();
        long[] array = new long[size];
        for ( int i = 0; i < array.length; i++ )
            array[i] = in.readLong();
            
        in.close();
        return array;
     }
}
