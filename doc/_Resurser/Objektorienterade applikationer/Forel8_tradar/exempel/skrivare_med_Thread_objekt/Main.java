public class Main {
    private Skrivare s1 = new Skrivare("Java är bättre...",738),
                     s2 = new Skrivare("C++ är bäst!",1122);
	public Main() {
	    s1.start();
	    s2.start();
	}
	
    public void stop() {
	    s1.stop();
	    s2.stop();
	} 
}
