public class Main {
    private Skrivare s1 = new Skrivare("Java �r b�ttre...",738),
                     s2 = new Skrivare("C++ �r b�st!",1122);
	public Main() {
	    s1.start();
	    s2.start();
	}
	
    public void stop() {
	    s1.stop();
	    s2.stop();
	} 
}
