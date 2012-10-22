/**
 * Skansholm s.412
 * 
 * @author JSk (UH) 
 * @version 2006-02-07
 */
public class Skrivare implements Runnable {
	private Thread activity = new Thread(this);
    String text;
    private long interval;    // ms

	public Skrivare(String text,long interval) {
		this.text = text;
		this.interval = interval;
	}
	
    public void run() {
        while ( ! Thread.interrupted() ) {
            try {
                Thread.sleep(interval);
            }
            catch (InterruptedException e) {
                break;
            }
            System.out.println(text); 
        }
    }
    
    public void start() {
        activity.start();
    }
    
    public void stop() {
        activity.interrupt();
    }
}
