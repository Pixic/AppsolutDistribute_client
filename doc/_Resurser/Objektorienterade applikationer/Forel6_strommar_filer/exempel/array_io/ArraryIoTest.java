import java.io.IOException;
/**
 * test the ArrayIo class by writing a file of long ints
 * and then read it back again.
 * 
 * @author Uno Holmer
 * @version 2009-09-29
 */
public class ArraryIoTest
{
    public static void main(String[] args) {
        long[] arr = {1255353466,346456878,23234523,976454544,232462323};
        String fileName = args[0];
        try {
            ArrayIo.save(arr,fileName);
            arr = ArrayIo.load(fileName);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        for ( long x : arr )
            System.out.println(x);
    }
}
