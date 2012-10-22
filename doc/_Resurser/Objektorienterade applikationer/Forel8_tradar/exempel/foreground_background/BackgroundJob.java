/**
 * Example: Use of the join method in Threads
 * 
 * @author Uno Holmer
 * @version 2006-02-06
 */
public class BackgroundJob extends Thread {
    private int maxSteps = 0;
    private long sum = 0;    // Calculate sum = 1+2+3+...
    private long interval;

	public BackgroundJob(long interval,int maxSteps) {
		this.maxSteps = maxSteps;
		this.interval = interval;
	}
	
    public void run() {
        int i = 1;
        while ( i <= maxSteps && ! Thread.interrupted() ) {
            try {
                Thread.sleep(interval);
            }
            catch (InterruptedException e) {
                break;
            }
            System.out.println("Background work... " + i++); 
            sum += i;
        }
        System.out.println("Background work finished");
    }
    
    public long getResult() {
        return sum;
    }
}
