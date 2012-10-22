// This class implements the Singleton design pattern
public class SharedNumberServer {
    private int value = 0;
    private static SharedNumberServer instance = null;  
    
    private SharedNumberServer() {}
    
    public static SharedNumberServer getInstance() {
        if ( instance == null )
            instance = new SharedNumberServer();
            
        return instance;
    }
    
    public int getNext() {        
        return value++;
    } 
}
