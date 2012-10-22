
public class TwoWriterThreads {
    private Writer s1 = new Writer("Java is better...",738),
                   s2 = new Writer("C++ is best!",1122);
	
    public TwoWriterThreads() {
	    //start();
	}
	
    public void start() {
	    s1.start();
	    s2.start();    
	} 
	
    public void stop() {
	    s1.interrupt();
	    s2.interrupt();    
	} 
}
